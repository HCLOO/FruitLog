package com.example.think.fruitlog.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.example.think.fruitlog.R;
import com.example.think.fruitlog.callback.OnSelectPoiListener;

import java.util.List;

public class PoiAdrShowAdapter extends RecyclerView.Adapter<PoiAdrShowAdapter.ViewHolder> {

    Context mContext;
    List<PoiInfo> poiList;
    OnSelectPoiListener onSelectPoiListener;

    public PoiAdrShowAdapter(Context mContext, List<PoiInfo> poiList,OnSelectPoiListener onSelectPoiListener) {
        this.mContext=mContext;
        this.poiList=poiList;
        this.onSelectPoiListener=onSelectPoiListener;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.layout_poi_show_item,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,int position) {
        String poiItem=poiList.get(position).getName();
        final LatLng enLatLang=poiList.get(position).getLocation();
        holder.contentText.setText(poiItem);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSelectPoiListener.onSelect(enLatLang);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(poiList!=null)
            return poiList.size();
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView contentText;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView=(CardView)itemView;
            contentText=(TextView)itemView.findViewById(R.id.tv_item);
        }
    }
}
