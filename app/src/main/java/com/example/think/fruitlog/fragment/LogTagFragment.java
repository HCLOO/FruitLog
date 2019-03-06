package com.example.think.fruitlog.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.think.fruitlog.bmobmodel.PraiseLog;
import com.example.think.fruitlog.model.LogTag;
import com.example.think.fruitlog.adapter.LogTagAdapter;
import com.example.think.fruitlog.callback.LogTagCallback;
import com.example.think.fruitlog.R;
import com.example.think.fruitlog.util.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import dao.DatabaseManager;

public class LogTagFragment extends Fragment {

    Toolbar toolbar;
    RecyclerView logTagRecyclerView;
    FloatingActionButton floatingActionButton;
    SwipeRefreshLayout swipeRefreshLayout;
    public static LogTagAdapter adapter;
    public static List<LogTag> tagList=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_log_tag,container,false);

        toolbar=(Toolbar)view.findViewById(R.id.tool_log_tag);
        logTagRecyclerView=(RecyclerView)view.findViewById(R.id.recycler_log_tag);
        floatingActionButton=(FloatingActionButton)view.findViewById(R.id.float_btn);
        swipeRefreshLayout=(SwipeRefreshLayout)view.findViewById(R.id.refresh_log_tag);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ActionBar actionBar=((AppCompatActivity)getActivity()).getSupportActionBar();
        if(actionBar!=null)
            actionBar.setDisplayHomeAsUpEnabled(true);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.frame_log_tag,new EditLogFragment()).addToBackStack("LogTagFragmentToEdit").commit();
            }
        });

        adapter=new LogTagAdapter(getActivity(),tagList,getFragmentManager());
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        logTagRecyclerView.setLayoutManager(linearLayoutManager);
        logTagRecyclerView.setAdapter(adapter);

        ItemTouchHelper.Callback mCallback=new LogTagCallback(ItemTouchHelper.UP|ItemTouchHelper.DOWN,ItemTouchHelper.LEFT);
        ItemTouchHelper itemTouchHelper=new ItemTouchHelper(mCallback);
        itemTouchHelper.attachToRecyclerView(logTagRecyclerView);

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
                        getActivity().runOnUiThread(new Runnable() {
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
    public void onStart() {
        super.onStart();
        if(tagList!=null)
            tagList.clear();
        /*List<com.example.think.fruitlog.daotable.LogTag> logTagList= DatabaseManager.getInstance().getDaoSession().loadAll(com.example.think.fruitlog.daotable.LogTag.class);
        for(com.example.think.fruitlog.daotable.LogTag logTag : logTagList) {
            tagList.add(new LogTag(logTag.getLogTitle(),logTag.getLogTime(),logTag.getLogContent()));
        }
        adapter.notifyDataSetChanged();*/
        BmobQuery<com.example.think.fruitlog.bmobmodel.LogTag> bmobQuery=new BmobQuery<com.example.think.fruitlog.bmobmodel.LogTag>();
        bmobQuery.addWhereEqualTo("userName", SharedPreferencesUtil.getPreferenceUsername());
        bmobQuery.findObjects(new FindListener<com.example.think.fruitlog.bmobmodel.LogTag>() {
            @Override
            public void done(List<com.example.think.fruitlog.bmobmodel.LogTag> list, BmobException e) {
                if(e==null) {
                    Log.e("size",list.size()+"");
                    if(list!=null&&!list.isEmpty()) {
                        for(com.example.think.fruitlog.bmobmodel.LogTag logTag : list) {
                            tagList.add(new com.example.think.fruitlog.model.LogTag(logTag.getTitle(),logTag.getTime(),logTag.getContent(),logTag.getObjectId()));
                        }
                    }
                    adapter.notifyDataSetChanged();
                }else {
                    Log.e("error: ", e.getMessage());
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home) {
            getActivity().finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
