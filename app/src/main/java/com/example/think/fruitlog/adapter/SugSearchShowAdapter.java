package com.example.think.fruitlog.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.think.fruitlog.R;
import com.example.think.fruitlog.callback.OnSelectSugListener;
import com.example.think.fruitlog.model.CityKey;

import java.util.List;

public class SugSearchShowAdapter extends RecyclerView.Adapter<SugSearchShowAdapter.ViewHolder> {

    Context mContext;
    List<CityKey> keyList;
    OnSelectSugListener onSelectSugListener;

    public SugSearchShowAdapter(Context mContext, List<CityKey> keyList, OnSelectSugListener onSelectSugListener) {
        this.mContext=mContext;
        this.keyList=keyList;
        this.onSelectSugListener=onSelectSugListener;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.layout_sug_item,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,int position) {
        final String cityItem=keyList.get(position).getCity();
        final String keyItem=keyList.get(position).getKey();
        holder.contentText.setText(keyItem);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSelectSugListener.onSelectSug(cityItem,keyItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(keyList!=null)
            return keyList.size();
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout linearLayout;
        TextView contentText;

        public ViewHolder(View itemView) {
            super(itemView);
            linearLayout=(LinearLayout) itemView;
            contentText=(TextView)itemView.findViewById(R.id.tv_keyname);
        }
    }
}
