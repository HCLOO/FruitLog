package com.example.think.fruitlog.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.think.fruitlog.R;
import com.example.think.fruitlog.model.BuyLog;
import java.util.List;

public class BuyLogAdapter extends RecyclerView.Adapter<BuyLogAdapter.ViewHolder> {

    Context mContext;
    List<BuyLog> mLogList;

    public BuyLogAdapter(List<BuyLog> objects) {
        mLogList=objects;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView imageView;
        TextView textView;
        TextView timeTextView;
        public ViewHolder(View itemView) {
            super(itemView);
            cardView=(CardView)itemView;
            imageView=(ImageView)itemView.findViewById(R.id.image_view);
            textView=(TextView)itemView.findViewById(R.id.text_view);
            timeTextView=(TextView)itemView.findViewById(R.id.time_text_view);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext=parent.getContext();
        View view= LayoutInflater.from(mContext).inflate(R.layout.layout_buy_log_list_item,parent,false);
        final ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        BuyLog buyLog=mLogList.get(position);
        holder.textView.setText(buyLog.getName());
        holder.timeTextView.setText(buyLog.getTime());
        Glide.with(mContext).load(buyLog.getImageId()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mLogList.size();
    }
}
