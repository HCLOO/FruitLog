package dao;

import android.util.Log;
import com.example.think.fruitlog.MyApplication;
import org.greenrobot.greendao.database.Database;

public class DatabaseManager {

    private static DatabaseManager databaseManager;
    private static DaoMaster.DevOpenHelper mHelper;
    private static Database mDatabase;
    private static DaoMaster daoMaster;
    private static DaoSession daoSession;

    public static DatabaseManager getInstance() {
        if(databaseManager==null) {
            synchronized (DatabaseManager.class) {
                if(databaseManager==null) {
                    Log.e( "getInstance: ","yes" );
                    databaseManager=new DatabaseManager();
                }
            }
        }
        return databaseManager;
    }

    public void setOpenHelper() {
        if(mHelper==null) {
            synchronized (DaoMaster.DevOpenHelper.class) {
                if(mHelper==null) {
                    Log.e( "setOpenHelper: ","yes" );
                    mHelper=new DaoMaster.DevOpenHelper(MyApplication.getContext(),"fruit.db");
                }
            }
        }
    }

    public void setDatabase() {
        if(mDatabase==null) {
            synchronized (Database.class) {
                if(mDatabase==null) {
                    Log.e( "setDatabase: ","yes" );
                    mDatabase=mHelper.getWritableDb();
                }
            }
        }
    }

    public void setDaoMaster() {
        if(daoMaster==null) {
            synchronized (DaoMaster.class) {
                if(daoMaster==null) {
                    Log.e( "setDaoMaster: ","yes" );
                    daoMaster=new DaoMaster(mDatabase);
                }
            }
        }
    }

    public DaoSession getDaoSession() {
        if(daoSession==null) {
            synchronized (DaoSession.class) {
                if(daoSession==null) {
                    setOpenHelper();
                    setDatabase();
                    setDaoMaster();
                    daoSession=daoMaster.newSession();
                    Log.e( "getDaoSession: ","yes" );
                }
            }
        }
        return daoSession;
    }
}
