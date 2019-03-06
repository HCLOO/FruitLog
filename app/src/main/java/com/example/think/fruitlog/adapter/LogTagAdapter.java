package com.example.think.fruitlog.adapter;

import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.think.fruitlog.R;
import com.example.think.fruitlog.fragment.LogDisplayFragment;
import com.example.think.fruitlog.model.LogTag;
import java.util.List;
import static com.example.think.fruitlog.fragment.LogDisplayFragment.TAG_DISPLAY_CONTENT;
import static com.example.think.fruitlog.fragment.LogDisplayFragment.TAG_DISPLAY_TIME;
import static com.example.think.fruitlog.fragment.LogDisplayFragment.TAG_DISPLAY_TITLE;

public class LogTagAdapter extends RecyclerView.Adapter<LogTagAdapter.ViewHolder> {

    Context mContext;
    List<LogTag> tagList;
    FragmentManager fragmentManager;

    public LogTagAdapter(Context mContext, List<LogTag> tagList, FragmentManager fragmentManager) {
        this.mContext=mContext;
        this.tagList=tagList;
        this.fragmentManager=fragmentManager;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.layout_log_tag_item,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final LogTag logTag=tagList.get(position);
        holder.titleText.setText(logTag.getTitle());
        holder.timeText.setText(logTag.getTime());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogDisplayFragment fragment=new LogDisplayFragment();
                Bundle bundle=new Bundle();
                bundle.putString(TAG_DISPLAY_TITLE,logTag.getTitle());
                bundle.putString(TAG_DISPLAY_TIME,logTag.getTime());
                bundle.putString(TAG_DISPLAY_CONTENT,logTag.getContent());
                fragment.setArguments(bundle);
                fragmentManager.beginTransaction().replace(R.id.frame_log_tag,fragment).addToBackStack("LogTagFragmentToDisplay").commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        if(tagList!=null)
            return tagList.size();
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView titleText;
        TextView timeText;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView=(CardView)itemView;
            titleText=(TextView)itemView.findViewById(R.id.text_title);
            timeText=(TextView)itemView.findViewById(R.id.text_time);
        }
    }
}
