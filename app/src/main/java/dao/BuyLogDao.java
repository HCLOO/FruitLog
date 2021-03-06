package dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.example.think.fruitlog.daotable.BuyLog;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "BUY_LOG".
*/
public class BuyLogDao extends AbstractDao<BuyLog, Long> {

    public static final String TABLENAME = "BUY_LOG";

    /**
     * Properties of entity BuyLog.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property FruitName = new Property(1, String.class, "fruitName", false, "FRUIT_NAME");
        public final static Property FruitImageId = new Property(2, int.class, "fruitImageId", false, "FRUIT_IMAGE_ID");
        public final static Property FruitBuyTime = new Property(3, String.class, "fruitBuyTime", false, "FRUIT_BUY_TIME");
    }


    public BuyLogDao(DaoConfig config) {
        super(config);
    }
    
    public BuyLogDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"BUY_LOG\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"FRUIT_NAME\" TEXT," + // 1: fruitName
                "\"FRUIT_IMAGE_ID\" INTEGER NOT NULL ," + // 2: fruitImageId
                "\"FRUIT_BUY_TIME\" TEXT);"); // 3: fruitBuyTime
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"BUY_LOG\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, BuyLog entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String fruitName = entity.getFruitName();
        if (fruitName != null) {
            stmt.bindString(2, fruitName);
        }
        stmt.bindLong(3, entity.getFruitImageId());
 
        String fruitBuyTime = entity.getFruitBuyTime();
        if (fruitBuyTime != null) {
            stmt.bindString(4, fruitBuyTime);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, BuyLog entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String fruitName = entity.getFruitName();
        if (fruitName != null) {
            stmt.bindString(2, fruitName);
        }
        stmt.bindLong(3, entity.getFruitImageId());
 
        String fruitBuyTime = entity.getFruitBuyTime();
        if (fruitBuyTime != null) {
            stmt.bindString(4, fruitBuyTime);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public BuyLog readEntity(Cursor cursor, int offset) {
        BuyLog entity = new BuyLog( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // fruitName
            cursor.getInt(offset + 2), // fruitImageId
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3) // fruitBuyTime
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, BuyLog entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setFruitName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setFruitImageId(cursor.getInt(offset + 2));
        entity.setFruitBuyTime(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(BuyLog entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(BuyLog entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(BuyLog entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
