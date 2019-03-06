package com.example.think.fruitlog.activtiy;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;
import com.example.think.fruitlog.R;
import com.example.think.fruitlog.daotable.PraiseLog;
import com.example.think.fruitlog.util.SharedPreferencesUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import dao.DatabaseManager;

public class PraiseActivity extends AppCompatActivity {

    RatingBar ratingBar;
    Button praise;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_praise);

        ratingBar=(RatingBar)findViewById(R.id.rating_bar);
        praise=(Button)findViewById(R.id.praise_btn);
        toolbar=(Toolbar)findViewById(R.id.tool_praise);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null)
            actionBar.setDisplayHomeAsUpEnabled(true);

        praise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float numStart=ratingBar.getRating();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                Date date=new Date(System.currentTimeMillis());
                String dateAndTime=simpleDateFormat.format(date);
                Toast.makeText(PraiseActivity.this,"评分为"+numStart+"星，感谢您的点评！",Toast.LENGTH_SHORT).show();
                /*DatabaseManager.getInstance().getDaoSession().insert(new PraiseLog(null,numStart,dateAndTime));
                finish();*/
                com.example.think.fruitlog.bmobmodel.PraiseLog praiseLog=new com.example.think.fruitlog.bmobmodel.PraiseLog();
                praiseLog.setUserName(SharedPreferencesUtil.getPreferenceUsername());
                praiseLog.setStarNum(numStart);
                praiseLog.setTime(dateAndTime);
                praiseLog.save(new SaveListener<String>() {
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
