package com.example.think.fruitlog.callback;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

import com.example.think.fruitlog.model.LogTag;
import org.greenrobot.greendao.query.DeleteQuery;
import java.util.Collections;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;
import dao.DatabaseManager;
import dao.LogTagDao;
import static com.example.think.fruitlog.fragment.LogTagFragment.adapter;
import static com.example.think.fruitlog.fragment.LogTagFragment.tagList;

public class LogTagCallback extends ItemTouchHelper.SimpleCallback {

    public LogTagCallback(int dragDirs, int swipeDirs) {
        super(dragDirs, swipeDirs);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        int fromPosition=viewHolder.getAdapterPosition();
        int toPosition=target.getAdapterPosition();
        if(fromPosition<toPosition)
            for(int i=0;i<toPosition;++i)
                Collections.swap(tagList,i,i+1);
        else
            for(int i=fromPosition;i>toPosition;--i)
                Collections.swap(tagList,i,i-1);
        adapter.notifyItemMoved(fromPosition,toPosition);
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        int position=viewHolder.getAdapterPosition();
        LogTag logTag=tagList.get(position);
        /*DeleteQuery<com.example.think.fruitlog.daotable.LogTag> deleteQuery= DatabaseManager.getInstance().getDaoSession().queryBuilder(com.example.think.fruitlog.daotable.LogTag.class)
                .where(LogTagDao.Properties.LogTime.eq(logTag.getTime()))
                .buildDelete();
        deleteQuery.executeDeleteWithoutDetachingEntities();*/
        com.example.think.fruitlog.bmobmodel.LogTag deleteItem=new com.example.think.fruitlog.bmobmodel.LogTag();
        deleteItem.setObjectId(logTag.getObjectId());
        deleteItem.delete(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if(e==null) {
                    Log.e("delete","删除成功");
                } else {
                    Log.e("error: ", e.getMessage());
                }
            }
        });
        tagList.remove(position);
        adapter.notifyItemRemoved(position);
    }
}
