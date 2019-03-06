package com.example.think.fruitlog.callback;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

import com.example.think.fruitlog.model.Fruit;
import org.greenrobot.greendao.query.DeleteQuery;
import java.util.Collections;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;
import dao.DatabaseManager;
import dao.FruitDao;
import static com.example.think.fruitlog.activtiy.BuyActivity.badapter;
import static com.example.think.fruitlog.activtiy.BuyActivity.buyList;

public class MySimpleCallback extends ItemTouchHelper.SimpleCallback {

    public MySimpleCallback(int dragDirs, int swipeDirs) {
        super(dragDirs, swipeDirs);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        int fromPosition=viewHolder.getAdapterPosition();
        int toPosition=target.getAdapterPosition();
        if(fromPosition<toPosition)
            for(int i=0;i<toPosition;++i)
                Collections.swap(buyList,i,i+1);
        else
            for(int i=fromPosition;i>toPosition;--i)
                Collections.swap(buyList,i,i-1);
        badapter.notifyItemMoved(fromPosition,toPosition);
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        int position=viewHolder.getAdapterPosition();
        Fruit fruit=buyList.get(position);
        /*DeleteQuery<com.example.think.fruitlog.daotable.Fruit> deleteQuery=DatabaseManager.getInstance().getDaoSession().queryBuilder(com.example.think.fruitlog.daotable.Fruit.class)
                .where(FruitDao.Properties.FruitName.eq(fruit.getName()))
                .buildDelete();
        deleteQuery.executeDeleteWithoutDetachingEntities();*/
        com.example.think.fruitlog.bmobmodel.Fruit deleteItem=new com.example.think.fruitlog.bmobmodel.Fruit();
        deleteItem.setObjectId(fruit.getObjectId());
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
        buyList.remove(position);
        badapter.notifyItemRemoved(position);
    }
}
