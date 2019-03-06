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
import com.example.think.fruitlog.activtiy.FruitData_Activity;
import com.example.think.fruitlog.model.Fruit;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    Context mcontext;
    List<Fruit> mfruitList;

    public MyAdapter(List<Fruit> objects) {
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
                Intent intent= new Intent(mcontext,FruitData_Activity.class);
                intent.putExtra(FruitData_Activity.FRUITNAME,fruit.getName());
                intent.putExtra(FruitData_Activity.FRUITIMGEID,fruit.getImageId());
                intent.putExtra(FruitData_Activity.FRUITDETAIL,fruit.getDetail());
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
