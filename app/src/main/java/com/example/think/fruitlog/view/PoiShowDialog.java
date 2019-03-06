package com.example.think.fruitlog.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.baidu.mapapi.search.core.PoiInfo;
import com.example.think.fruitlog.R;
import com.example.think.fruitlog.adapter.PoiAdrShowAdapter;
import com.example.think.fruitlog.callback.OnSelectPoiListener;

import java.util.List;

public class PoiShowDialog extends Dialog {

    private Context mContext;
    private PoiAdrShowAdapter poiAdrShowAdapter;
    private RecyclerView recyclerView;
    private List<PoiInfo> poiList;
    private OnSelectPoiListener onSelectPoiListener;

    public PoiShowDialog(@NonNull Context context, List<PoiInfo> poiList, OnSelectPoiListener onSelectPoiListener) {
        super(context);
        mContext=context;
        this.poiList=poiList;
        this.onSelectPoiListener=onSelectPoiListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_poishowdialog);
        initView();
    }

    private void initView() {
        recyclerView=(RecyclerView)findViewById(R.id.rc_poi_show);
        poiAdrShowAdapter=new PoiAdrShowAdapter(getContext(),poiList,onSelectPoiListener);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(poiAdrShowAdapter);
    }
}
