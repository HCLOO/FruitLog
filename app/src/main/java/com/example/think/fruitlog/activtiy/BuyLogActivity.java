package com.example.think.fruitlog.activtiy;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import com.example.think.fruitlog.R;
import com.example.think.fruitlog.adapter.BuyLogAdapter;
import com.example.think.fruitlog.model.BuyLog;
import com.example.think.fruitlog.model.Fruit;
import com.example.think.fruitlog.util.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import dao.DatabaseManager;

public class BuyLogActivity extends AppCompatActivity {

    public List<BuyLog> buyLogList=new ArrayList<>();
    RecyclerView BLRecyclerView;
    public BuyLogAdapter BLadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_log);

        Toolbar toolbar=(Toolbar)findViewById(R.id.tool_log_tag);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null)
            actionBar.setDisplayHomeAsUpEnabled(true);

        BLRecyclerView=(RecyclerView)findViewById(R.id.recycler_log_tag);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(BuyLogActivity.this,LinearLayoutManager.VERTICAL,false);
        BLRecyclerView.setLayoutManager(linearLayoutManager);
        BLadapter=new BuyLogAdapter(buyLogList);
        BLRecyclerView.setAdapter(BLadapter);

        final SwipeRefreshLayout swipeRefreshLayout=(SwipeRefreshLayout)findViewById(R.id.refresh_log_tag);
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
        if(buyLogList!=null)
            buyLogList.clear();
        /*List<com.example.think.fruitlog.daotable.BuyLog> buyLogs= DatabaseManager.getInstance().getDaoSession().loadAll(com.example.think.fruitlog.daotable.BuyLog.class);
        for(com.example.think.fruitlog.daotable.BuyLog buyLog : buyLogs) {
            buyLogList.add(new BuyLog(buyLog.getFruitImageId(),buyLog.getFruitName(),buyLog.getFruitBuyTime()));
        }
        BLadapter.notifyDataSetChanged();*/
        BmobQuery<com.example.think.fruitlog.bmobmodel.BuyLog> bmobQuery=new BmobQuery<com.example.think.fruitlog.bmobmodel.BuyLog>();
        bmobQuery.addWhereEqualTo("userName", SharedPreferencesUtil.getPreferenceUsername());
        bmobQuery.findObjects(new FindListener<com.example.think.fruitlog.bmobmodel.BuyLog>() {
            @Override
            public void done(List<com.example.think.fruitlog.bmobmodel.BuyLog> list, BmobException e) {
                if(e==null) {
                    Log.e("size",list.size()+"");
                    if(list!=null&&!list.isEmpty()) {
                        for(com.example.think.fruitlog.bmobmodel.BuyLog buyLog : list) {
                            buyLogList.add(new BuyLog(buyLog.getImageId(),buyLog.getName(),buyLog.getTime()));
                        }
                    }
                    BLadapter.notifyDataSetChanged();
                }else {
                    Log.e("error: ", e.getMessage());
                }
            }
        });
    }
}
