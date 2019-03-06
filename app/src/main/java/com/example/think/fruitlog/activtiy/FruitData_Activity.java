package com.example.think.fruitlog.activtiy;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.think.fruitlog.R;
import com.example.think.fruitlog.bmobmodel.User;
import com.example.think.fruitlog.daotable.Fruit;
import com.example.think.fruitlog.util.SharedPreferencesUtil;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import dao.DatabaseManager;

public class FruitData_Activity extends AppCompatActivity {
    public static final String FRUITNAME ="fruit_name";
    public static final String FRUITIMGEID="fruit_image_id";
    public static final String FRUITDETAIL ="fruit_detail";
    String fruitName;
    int fruitImageId;
    String fruitDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruitdata);

        Intent intent=getIntent();
        fruitName=intent.getStringExtra(FRUITNAME);
        fruitImageId=intent.getIntExtra(FRUITIMGEID,0);
        fruitDetail=intent.getStringExtra(FRUITDETAIL);

        CollapsingToolbarLayout collapsingToolbarLayout=(CollapsingToolbarLayout)findViewById(R.id.tool_layout);
        ImageView imageView=(ImageView)findViewById(R.id.image_data);
        TextView textView=(TextView)findViewById(R.id.text_data);
        FloatingActionButton floatingActionButton=(FloatingActionButton)findViewById(R.id.float_data);
        Toolbar toolbar=(Toolbar)findViewById(R.id.tool_data);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null)
            actionBar.setDisplayHomeAsUpEnabled(true);

        collapsingToolbarLayout.setTitle(fruitName);
        Glide.with(FruitData_Activity.this).load(fruitImageId).into(imageView);
        textView.setText(fruitDetail);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*boolean flag=true;
                List<Fruit> fruitList=DatabaseManager.getInstance().getDaoSession().loadAll(Fruit.class);
                if(fruitList!=null&&!fruitList.isEmpty()) {
                    for(Fruit sample : fruitList) {
                        if(sample.getFruitName().equals(fruitName)) {
                            flag=false;
                            break;
                        }
                    }
                }
                if(fruitList!=null&&!fruitList.isEmpty()) {
                    for(Fruit sample : fruitList) {
                        if(sample.getFruitName().equals(fruitName)) {
                            flag=false;
                            break;
                        }
                    }
                }
                if(flag) {
                    Fruit fruit=new Fruit(null,fruitName,fruitImageId,fruitDetail);
                    DatabaseManager.getInstance().getDaoSession().insert(fruit);
                }
                Intent intent1=new Intent(FruitData_Activity.this,BuyActivity.class);
                startActivity(intent1);
                finish();*/
                BmobQuery<com.example.think.fruitlog.bmobmodel.Fruit> bmobQuery=new BmobQuery<com.example.think.fruitlog.bmobmodel.Fruit>();
                bmobQuery.addWhereEqualTo("userName",SharedPreferencesUtil.getPreferenceUsername());
                bmobQuery.addWhereEqualTo("fruitName",fruitName);
                bmobQuery.findObjects(new FindListener<com.example.think.fruitlog.bmobmodel.Fruit>() {
                    @Override
                    public void done(List<com.example.think.fruitlog.bmobmodel.Fruit> list, BmobException e) {
                        if(e==null) {
                            if(list!=null&&!list.isEmpty()) {
                                Intent intent1=new Intent(FruitData_Activity.this,BuyActivity.class);
                                startActivity(intent1);
                                finish();
                            } else {
                                com.example.think.fruitlog.bmobmodel.Fruit fruit=new com.example.think.fruitlog.bmobmodel.Fruit();
                                fruit.setUserName(SharedPreferencesUtil.getPreferenceUsername());
                                fruit.setFruitName(fruitName);
                                fruit.setFruitImageId(fruitImageId);
                                fruit.setFruitDetail(fruitDetail);
                                fruit.save(new SaveListener<String>() {
                                    @Override
                                    public void done(String s, BmobException e) {
                                        if(e==null) {
                                            Log.e("save: ", s+" 保存成功");
                                            Intent intent1=new Intent(FruitData_Activity.this,BuyActivity.class);
                                            startActivity(intent1);
                                            finish();
                                        } else {
                                            Log.e("saveError: ", e.getMessage());
                                        }
                                    }
                                });
                            }
                        }else {
                            Log.e("error: ", e.getMessage());
                        }
                    }
                });
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
