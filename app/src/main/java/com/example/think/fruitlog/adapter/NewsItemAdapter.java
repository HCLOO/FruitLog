package com.example.think.fruitlog.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.think.fruitlog.R;
import com.example.think.fruitlog.activtiy.GoToBuyActivity;
import com.example.think.fruitlog.activtiy.WebShowActivity;
import com.example.think.fruitlog.model.Fruit;
import com.example.think.fruitlog.model.NewsItem;

import java.util.List;

public class NewsItemAdapter extends RecyclerView.Adapter<NewsItemAdapter.ViewHolder> {

    Context mContext;
    List<NewsItem> mNewsList;

    public NewsItemAdapter(List<NewsItem> objects) {
        mNewsList=objects;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView imageView;
        TextView timeTextView,descriptionTextView,titleTextView;
        public ViewHolder(View itemView) {
            super(itemView);
            cardView=(CardView)itemView;
            imageView=(ImageView)itemView.findViewById(R.id.image_view);
            timeTextView=(TextView)itemView.findViewById(R.id.tv_time);
            descriptionTextView=(TextView)itemView.findViewById(R.id.tv_description);
            titleTextView=(TextView)itemView.findViewById(R.id.tv_title);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext=parent.getContext();
        View view= LayoutInflater.from(mContext).inflate(R.layout.layout_news_item,parent,false);
        final ViewHolder holder=new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position=holder.getAdapterPosition();
                NewsItem item=mNewsList.get(position);
                Intent intent= new Intent(mContext,WebShowActivity.class);
                intent.putExtra(WebShowActivity.TAG_NEW_TITLE,item.title);
                intent.putExtra(WebShowActivity.TAG_NEW_URL,item.url);
                mContext.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        NewsItem item=mNewsList.get(position);
        holder.titleTextView.setText(item.title);
        holder.timeTextView.setText(item.time);
        holder.descriptionTextView.setText(item.description);
        Glide.with(mContext).load(item.picUrl).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        if (mNewsList==null)
            return 0;
        else
            return mNewsList.size();
    }
}
