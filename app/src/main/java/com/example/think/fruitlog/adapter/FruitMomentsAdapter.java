package com.example.think.fruitlog.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.think.fruitlog.R;
import com.example.think.fruitlog.model.LocationAndComment;
import java.util.List;

public class FruitMomentsAdapter extends RecyclerView.Adapter<FruitMomentsAdapter.ViewHolder> {

    Context mContext;
    List<LocationAndComment> locationAndComments;

    public FruitMomentsAdapter(List<LocationAndComment> objects) {
        locationAndComments=objects;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView userName,comment,location,time;
        public ViewHolder(View itemView) {
            super(itemView);
            userName=(TextView)itemView.findViewById(R.id.moments_user);
            comment=(TextView)itemView.findViewById(R.id.moments_content);
            location=(TextView)itemView.findViewById(R.id.tv_location);
            time=(TextView)itemView.findViewById(R.id.tv_time);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext=parent.getContext();
        View view= LayoutInflater.from(mContext).inflate(R.layout.layout_fruitmoments_item,parent,false);
        final ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        LocationAndComment item=locationAndComments.get(position);
        holder.userName.setText(item.getUserName());
        holder.comment.setText(item.getComment());
        holder.location.setText(item.getCity()+"."+item.getStoreName());
        holder.time.setText(item.getTime());
    }

    @Override
    public int getItemCount() {
        return locationAndComments.size();
    }
}
