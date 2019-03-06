package com.example.think.fruitlog.activtiy;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.think.fruitlog.R;
import com.example.think.fruitlog.adapter.BuyLogAdapter;
import com.example.think.fruitlog.adapter.FruitMomentsAdapter;
import com.example.think.fruitlog.model.BuyLog;
import com.example.think.fruitlog.model.LocationAndComment;
import com.example.think.fruitlog.util.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class FruitMomentsActivity extends AppCompatActivity {

    private List<LocationAndComment> locationAndComments=new ArrayList<>();
    private RecyclerView momentsRV;
    private FruitMomentsAdapter fruitMomentsAdapter;
    private Button searchBTN;
    private EditText cityET;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit_moments);

        cityET=(EditText)findViewById(R.id.et_ctiy);
        Toolbar toolbar=(Toolbar)findViewById(R.id.tool_fruitmoments);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null)
            actionBar.setDisplayHomeAsUpEnabled(true);

        momentsRV=(RecyclerView)findViewById(R.id.recycler_fruitmoments);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(FruitMomentsActivity.this,LinearLayoutManager.VERTICAL,false);
        momentsRV.setLayoutManager(linearLayoutManager);
        fruitMomentsAdapter=new FruitMomentsAdapter(locationAndComments);
        momentsRV.setAdapter(fruitMomentsAdapter);

        searchBTN=(Button)findViewById(R.id.btn_search);
        searchBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cityET.getText().toString().isEmpty()) {
                    Toast.makeText(FruitMomentsActivity.this,"请输入城市",Toast.LENGTH_SHORT).show();
                } else {
                    if(locationAndComments!=null)
                        locationAndComments.clear();
                    BmobQuery<com.example.think.fruitlog.bmobmodel.LocationAndComment> bmobQuery=new BmobQuery<com.example.think.fruitlog.bmobmodel.LocationAndComment>();
                    bmobQuery.addWhereEqualTo("city",cityET.getText().toString());
                    bmobQuery.findObjects(new FindListener<com.example.think.fruitlog.bmobmodel.LocationAndComment>() {
                        @Override
                        public void done(List<com.example.think.fruitlog.bmobmodel.LocationAndComment> list, BmobException e) {
                            if(e==null) {
                                Log.e("size",list.size()+"");
                                if(list!=null&&!list.isEmpty()) {
                                    for(com.example.think.fruitlog.bmobmodel.LocationAndComment locationAndComment : list) {
                                        locationAndComments.add(new LocationAndComment(locationAndComment.getUserName(),locationAndComment.getCity(),
                                                locationAndComment.getStoreName(),locationAndComment.getComment(),locationAndComment.getTime()));
                                    }
                                }
                                fruitMomentsAdapter.notifyDataSetChanged();
                            } else {
                                Toast.makeText(FruitMomentsActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                                Log.e("error: ", e.getMessage());
                            }
                        }
                    });
                }
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
