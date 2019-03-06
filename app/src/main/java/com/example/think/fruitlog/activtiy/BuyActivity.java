package com.example.think.fruitlog.activtiy;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.MenuItem;
import com.example.think.fruitlog.R;
import com.example.think.fruitlog.adapter.BuyFruitAdapter;
import com.example.think.fruitlog.callback.MySimpleCallback;
import com.example.think.fruitlog.model.Fruit;
import com.example.think.fruitlog.util.SharedPreferencesUtil;
import java.util.ArrayList;
import java.util.List;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import dao.DatabaseManager;

public class BuyActivity extends AppCompatActivity {

    public static List<Fruit> buyList=new ArrayList<>();
    RecyclerView recyclerView;
    public static BuyFruitAdapter badapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);

        Toolbar toolbar=(Toolbar)findViewById(R.id.tool_buy);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null)
            actionBar.setDisplayHomeAsUpEnabled(true);

        recyclerView=(RecyclerView)findViewById(R.id.recycler_buy);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(BuyActivity.this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        badapter=new BuyFruitAdapter(buyList);
        recyclerView.setAdapter(badapter);

        ItemTouchHelper.Callback mCallback=new MySimpleCallback(ItemTouchHelper.UP|ItemTouchHelper.DOWN,ItemTouchHelper.LEFT);
        ItemTouchHelper itemTouchHelper=new ItemTouchHelper(mCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        final SwipeRefreshLayout swipeRefreshLayout=(SwipeRefreshLayout)findViewById(R.id.refresh_buy);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                swipeRefreshLayout.setRefreshing(false);
                            }
                        });
                    }
                }).start();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(buyList!=null)
            buyList.clear();
        /*List<com.example.think.fruitlog.daotable.Fruit> fruitList= DatabaseManager.getInstance().getDaoSession().loadAll(com.example.think.fruitlog.daotable.Fruit.class);
        for(com.example.think.fruitlog.daotable.Fruit fruit : fruitList) {
            buyList.add(new Fruit(fruit.getFruitImageId(),fruit.getFruitName(),fruit.getFruitDetail()));
        }
        badapter.notifyDataSetChanged();*/
        BmobQuery<com.example.think.fruitlog.bmobmodel.Fruit> bmobQuery=new BmobQuery<com.example.think.fruitlog.bmobmodel.Fruit>();
        bmobQuery.addWhereEqualTo("userName", SharedPreferencesUtil.getPreferenceUsername());
        bmobQuery.findObjects(new FindListener<com.example.think.fruitlog.bmobmodel.Fruit>() {
            @Override
            public void done(List<com.example.think.fruitlog.bmobmodel.Fruit> list, BmobException e) {
                if(e==null) {
                    Log.e("size",list.size()+"");
                    if(list!=null&&!list.isEmpty()) {
                        for(com.example.think.fruitlog.bmobmodel.Fruit fruit : list) {
                            buyList.add(new Fruit(fruit.getFruitImageId(),fruit.getFruitName(),fruit.getFruitDetail(),fruit.getObjectId()));
                        }
                    }
                    badapter.notifyDataSetChanged();
                }else {
                    Log.e("error: ", e.getMessage());
                }
            }
        });
    }
}
