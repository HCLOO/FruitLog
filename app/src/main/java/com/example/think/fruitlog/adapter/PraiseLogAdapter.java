package com.example.think.fruitlog.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.think.fruitlog.R;
import com.example.think.fruitlog.model.PraiseLog;
import java.util.List;

public class PraiseLogAdapter extends RecyclerView.Adapter<PraiseLogAdapter.ViewHolder> {

    Context mContext;
    List<PraiseLog> praiseLogList;

    public PraiseLogAdapter(Context mContext, List<PraiseLog> praiseLogList) {
        this.mContext=mContext;
        this.praiseLogList=praiseLogList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.layout_praise_log_item,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PraiseLog praiseLog=praiseLogList.get(position);
        holder.starText.setText(String.valueOf(praiseLog.getStarNum()));
        holder.timeText.setText(praiseLog.getTime());
    }

    @Override
    public int getItemCount() {
        if(praiseLogList!=null)
            return praiseLogList.size();
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView starText;
        TextView timeText;

        public ViewHolder(View itemView) {
            super(itemView);
            starText=(TextView)itemView.findViewById(R.id.text_star_num);
            timeText=(TextView)itemView.findViewById(R.id.text_time);
        }
    }
}
