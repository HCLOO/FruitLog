package com.example.think.fruitlog.callback;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

import com.example.think.fruitlog.model.PraiseLog;
import org.greenrobot.greendao.query.DeleteQuery;
import java.util.Collections;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;
import dao.DatabaseManager;
import dao.PraiseLogDao;
import static com.example.think.fruitlog.activtiy.PraiseLogActivity.praiseAdapter;
import static com.example.think.fruitlog.activtiy.PraiseLogActivity.praiseLogList;

public class PraiseLogCallback extends ItemTouchHelper.SimpleCallback {

    public PraiseLogCallback(int dragDirs, int swipeDirs) {
        super(dragDirs, swipeDirs);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        int fromPosition=viewHolder.getAdapterPosition();
        int toPosition=target.getAdapterPosition();
        if(fromPosition<toPosition)
            for(int i=0;i<toPosition;++i)
                Collections.swap(praiseLogList,i,i+1);
        else
            for(int i=fromPosition;i>toPosition;--i)
                Collections.swap(praiseLogList,i,i-1);
        praiseAdapter.notifyItemMoved(fromPosition,toPosition);
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        int position=viewHolder.getAdapterPosition();
        PraiseLog praiseLog=praiseLogList.get(position);
        /*DeleteQuery<com.example.think.fruitlog.daotable.PraiseLog> deleteQuery= DatabaseManager.getInstance().getDaoSession().queryBuilder(com.example.think.fruitlog.daotable.PraiseLog.class)
                .where(PraiseLogDao.Properties.Time.eq(praiseLog.getTime()))
                .buildDelete();
        deleteQuery.executeDeleteWithoutDetachingEntities();*/
        com.example.think.fruitlog.bmobmodel.PraiseLog deleteItem=new com.example.think.fruitlog.bmobmodel.PraiseLog();
        deleteItem.setObjectId(praiseLog.getObjectId());
        deleteItem.delete(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if(e==null) {
                    Log.e("delete","删除成功");
                }else {
                    Log.e("error: ", e.getMessage());
                }
            }
        });
        praiseLogList.remove(position);
        praiseAdapter.notifyItemRemoved(position);
    }
}
