package com.example.think.fruitlog.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.think.fruitlog.R;
import com.example.think.fruitlog.activtiy.GoToBuyActivity;
import com.example.think.fruitlog.model.Fruit;
import java.util.List;

public class BuyFruitAdapter extends RecyclerView.Adapter<BuyFruitAdapter.ViewHolder> {

    Context mcontext;
    List<Fruit> mfruitList;

    public BuyFruitAdapter(List<Fruit> objects) {
        mfruitList=objects;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView imageView;
        TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            cardView=(CardView)itemView;
            imageView=(ImageView)itemView.findViewById(R.id.image_view);
            textView=(TextView)itemView.findViewById(R.id.text_view);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mcontext=parent.getContext();
        View view= LayoutInflater.from(mcontext).inflate(R.layout.item_layout,parent,false);
        final ViewHolder holder=new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position=holder.getAdapterPosition();
                Fruit fruit=mfruitList.get(position);
                Intent intent= new Intent(mcontext,GoToBuyActivity.class);
                intent.putExtra(GoToBuyActivity.TAG_FRUITNAME,fruit.getName());
                intent.putExtra(GoToBuyActivity.TAG_FRUITIMGEID,fruit.getImageId());
                intent.putExtra(GoToBuyActivity.TAG_FRUITDETAIL,fruit.getDetail());
                intent.putExtra(GoToBuyActivity.TAG_USER_OBJECTID,fruit.getObjectId());
                mcontext.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Fruit fruit=mfruitList.get(position);
        holder.textView.setText(fruit.getName());
        Glide.with(mcontext).load(fruit.getImageId()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mfruitList.size();
    }
}
