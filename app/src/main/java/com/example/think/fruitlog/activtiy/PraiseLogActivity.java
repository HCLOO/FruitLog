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
import com.example.think.fruitlog.adapter.PraiseLogAdapter;
import com.example.think.fruitlog.bmobmodel.BuyLog;
import com.example.think.fruitlog.callback.PraiseLogCallback;
import com.example.think.fruitlog.model.PraiseLog;
import com.example.think.fruitlog.util.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import dao.DatabaseManager;

public class PraiseLogActivity extends AppCompatActivity {

    public static List<PraiseLog> praiseLogList=new ArrayList<>();
    RecyclerView praiseRecyclerView;
    public static PraiseLogAdapter praiseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_praise_log);

        Toolbar toolbar=(Toolbar)findViewById(R.id.tool_praise_log);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null)
            actionBar.setDisplayHomeAsUpEnabled(true);

        praiseRecyclerView=(RecyclerView)findViewById(R.id.recycler_praise_log);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(PraiseLogActivity.this,LinearLayoutManager.VERTICAL,false);
        praiseRecyclerView.setLayoutManager(linearLayoutManager);
        praiseAdapter=new PraiseLogAdapter(this,praiseLogList);
        praiseRecyclerView.setAdapter(praiseAdapter);

        ItemTouchHelper.Callback mCallback=new PraiseLogCallback(ItemTouchHelper.UP|ItemTouchHelper.DOWN,ItemTouchHelper.LEFT);
        ItemTouchHelper itemTouchHelper=new ItemTouchHelper(mCallback);
        itemTouchHelper.attachToRecyclerView(praiseRecyclerView);

        final SwipeRefreshLayout swipeRefreshLayout=(SwipeRefreshLayout)findViewById(R.id.refresh_praise_log);
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
        if(praiseLogList!=null)
            praiseLogList.clear();
        /*List<com.example.think.fruitlog.daotable.PraiseLog> praiseLogs= DatabaseManager.getInstance().getDaoSession().loadAll(com.example.think.fruitlog.daotable.PraiseLog.class);
        for(com.example.think.fruitlog.daotable.PraiseLog praiseLog : praiseLogs) {
            praiseLogList.add(new PraiseLog(praiseLog.getStarNum(),praiseLog.getTime()));
        }
        praiseAdapter.notifyDataSetChanged();*/
        BmobQuery<com.example.think.fruitlog.bmobmodel.PraiseLog> bmobQuery=new BmobQuery<com.example.think.fruitlog.bmobmodel.PraiseLog>();
        bmobQuery.addWhereEqualTo("userName", SharedPreferencesUtil.getPreferenceUsername());
        bmobQuery.findObjects(new FindListener<com.example.think.fruitlog.bmobmodel.PraiseLog>() {
            @Override
            public void done(List<com.example.think.fruitlog.bmobmodel.PraiseLog> list, BmobException e) {
                if(e==null) {
                    Log.e("size",list.size()+"");
                    if(list!=null&&!list.isEmpty()) {
                        for(com.example.think.fruitlog.bmobmodel.PraiseLog praiseLog : list) {
                            praiseLogList.add(new com.example.think.fruitlog.model.PraiseLog(praiseLog.getStarNum(),praiseLog.getTime(),praiseLog.getObjectId()));
                        }
                    }
                    praiseAdapter.notifyDataSetChanged();
                }else {
                    Log.e("error: ", e.getMessage());
                }
            }
        });
    }
}
