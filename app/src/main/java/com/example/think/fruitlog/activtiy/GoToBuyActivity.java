package com.example.think.fruitlog.activtiy;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.think.fruitlog.R;
import com.example.think.fruitlog.daotable.BuyLog;
import com.example.think.fruitlog.daotable.Fruit;
import com.example.think.fruitlog.util.SharedPreferencesUtil;

import org.greenrobot.greendao.query.DeleteQuery;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import dao.DatabaseManager;
import dao.FruitDao;

public class GoToBuyActivity extends AppCompatActivity {

    public static final String TAG_FRUITNAME ="tag_fruit_name";
    public static final String TAG_FRUITIMGEID="tag_fruit_image_id";
    public static final String TAG_FRUITDETAIL ="tag_fruit_detail";
    public static final String TAG_USER_OBJECTID ="tag_user_objectId";
    String fruitName;
    int fruitImageId;
    String fruitDetail;
    String userObjectId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go_to_buy);

        Intent intent=getIntent();
        fruitName=intent.getStringExtra(TAG_FRUITNAME);
        fruitImageId=intent.getIntExtra(TAG_FRUITIMGEID,0);
        fruitDetail=intent.getStringExtra(TAG_FRUITDETAIL);
        userObjectId=intent.getStringExtra(TAG_USER_OBJECTID);

        CollapsingToolbarLayout collapsingToolbarLayout=(CollapsingToolbarLayout)findViewById(R.id.tool_layout);
        ImageView imageView=(ImageView)findViewById(R.id.image_data);
        TextView textView=(TextView)findViewById(R.id.text_data);
        Button mButton=(Button)findViewById(R.id.btn_go_buy);
        Toolbar toolbar=(Toolbar)findViewById(R.id.tool_data);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null)
            actionBar.setDisplayHomeAsUpEnabled(true);

        collapsingToolbarLayout.setTitle(fruitName);
        Glide.with(GoToBuyActivity.this).load(fruitImageId).into(imageView);
        textView.setText(fruitDetail);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*DeleteQuery<Fruit> deleteQuery=DatabaseManager.getInstance().getDaoSession().queryBuilder(com.example.think.fruitlog.daotable.Fruit.class)
                        .where(FruitDao.Properties.FruitName.eq(fruitName))
                        .buildDelete();
                deleteQuery.executeDeleteWithoutDetachingEntities();*/
                com.example.think.fruitlog.bmobmodel.Fruit deleteItem=new com.example.think.fruitlog.bmobmodel.Fruit();
                deleteItem.setObjectId(userObjectId);
                deleteItem.delete(new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if(e==null) {
                            Log.e("delete","删除成功");
                        }else {
                            Log.e("error: ", e.getMessage());
                        }
                    }
                });

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                Date date=new Date(System.currentTimeMillis());
                String dateAndTime=simpleDateFormat.format(date);
                /*DatabaseManager.getInstance().getDaoSession().insert(new BuyLog(null,fruitName,fruitImageId,dateAndTime));
                finish();*/
                com.example.think.fruitlog.bmobmodel.BuyLog buyLog=new com.example.think.fruitlog.bmobmodel.BuyLog();
                buyLog.setUserName(SharedPreferencesUtil.getPreferenceUsername());
                buyLog.setName(fruitName);
                buyLog.setImageId(fruitImageId);
                buyLog.setTime(dateAndTime);
                buyLog.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                        if(e==null) {
                            Log.e("save: ", s+" 保存成功");
                            finish();
                        } else {
                            Log.e("saveError: ", e.getMessage());
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
