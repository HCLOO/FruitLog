package com.example.think.fruitlog.activtiy;

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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.baidu.mapapi.search.sug.OnGetSuggestionResultListener;
import com.baidu.mapapi.search.sug.SuggestionResult;
import com.baidu.mapapi.search.sug.SuggestionSearch;
import com.baidu.mapapi.search.sug.SuggestionSearchOption;
import com.example.think.fruitlog.R;
import com.example.think.fruitlog.adapter.PoiAdrShowAdapter;
import com.example.think.fruitlog.adapter.SugSearchShowAdapter;
import com.example.think.fruitlog.callback.OnSelectSugListener;
import com.example.think.fruitlog.model.CityKey;
import com.example.think.fruitlog.util.SharedPreferencesUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class CommentActivity extends AppCompatActivity implements View.OnClickListener {

    private SuggestionSearch suggestionSearch;
    private List<CityKey> keyList=new ArrayList<>();
    private RecyclerView sugShowRV;
    private SugSearchShowAdapter sugSearchShowAdapter;
    private Toolbar toolbar;
    private ImageView searchIV;
    private Button cancelBTN;
    private EditText locationET,cityET,commentET;
    private LinearLayout linearLayout1,linearLayout2,linearLayout3;
    private Button submitBTN;
    private TextView locDetailTV;
    private String city,store;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        toolbar=(Toolbar)findViewById(R.id.tool_log_tag);
        sugShowRV=(RecyclerView)findViewById(R.id.rc_sug_show);
        searchIV=(ImageView)findViewById(R.id.iv_search);
        cancelBTN=(Button)findViewById(R.id.btn_cancel);
        locationET=(EditText)findViewById(R.id.et_location);
        cityET=(EditText)findViewById(R.id.et_ctiy);
        commentET=(EditText)findViewById(R.id.et_comment);
        linearLayout1=(LinearLayout)findViewById(R.id.ll_part3);
        linearLayout2=(LinearLayout)findViewById(R.id.ll_part4);
        linearLayout3=(LinearLayout)findViewById(R.id.ll_part5);
        submitBTN=(Button)findViewById(R.id.btn_submit);
        locDetailTV=(TextView)findViewById(R.id.tv_location_detail);
        searchIV.setOnClickListener(this);
        cancelBTN.setOnClickListener(this);
        submitBTN.setOnClickListener(this);

        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null)
            actionBar.setDisplayHomeAsUpEnabled(true);

        initView();

        sugSearchShowAdapter=new SugSearchShowAdapter(CommentActivity.this, keyList, new OnSelectSugListener() {
            @Override
            public void onSelectSug(String cityName, String keyName) {
                initView();
                locDetailTV.setText(cityName+"."+keyName);
                locationET.setText(keyName);
                city=cityName;
                store=keyName;
            }
        });
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(CommentActivity.this,LinearLayoutManager.VERTICAL,false);
        sugShowRV.setLayoutManager(linearLayoutManager);
        sugShowRV.setAdapter(sugSearchShowAdapter);

        suggestionSearch=SuggestionSearch.newInstance();
        suggestionSearch.setOnGetSuggestionResultListener(new OnGetSuggestionResultListener() {
            @Override
            public void onGetSuggestionResult(SuggestionResult suggestionResult) {
                if(keyList!=null)
                    keyList.clear();
                if (suggestionResult == null || suggestionResult.getAllSuggestions() == null) {
                    Toast.makeText(CommentActivity.this, "未检索到当前地址",Toast.LENGTH_SHORT).show();
                    return;
                }

                for (SuggestionResult.SuggestionInfo info : suggestionResult.getAllSuggestions()) {
                    keyList.add(new CityKey(info.city,info.key));
                }
                sugSearchShowAdapter.notifyDataSetChanged();
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_search:
                if(cityET.getText().toString().isEmpty()||locationET.getText().toString().isEmpty()) {
                    Toast.makeText(CommentActivity.this, "请输入完整信息",Toast.LENGTH_SHORT).show();
                } else {
                    changeView();
                    suggestionSearch.requestSuggestion(new SuggestionSearchOption()
                            .city(cityET.getText().toString())
                            .keyword(locationET.getText().toString())
                            .citylimit(true));
                }
                break;
            case R.id.btn_cancel:
                locationET.setText("");
                initView();
                break;
            case R.id.btn_submit:
                if(city.isEmpty()||store.isEmpty()||commentET.getText().toString().isEmpty()) {
                    Toast.makeText(CommentActivity.this,"请输入完整信息",Toast.LENGTH_SHORT).show();
                } else {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                    Date date=new Date(System.currentTimeMillis());
                    String dateAndTime=simpleDateFormat.format(date);
                    com.example.think.fruitlog.bmobmodel.LocationAndComment locationAndComment=new com.example.think.fruitlog.bmobmodel.LocationAndComment();
                    locationAndComment.setUserName(SharedPreferencesUtil.getPreferenceUsername());
                    locationAndComment.setCity(city);
                    locationAndComment.setStoreName(store);
                    locationAndComment.setComment(commentET.getText().toString());
                    locationAndComment.setTime(dateAndTime);
                    locationAndComment.save(new SaveListener<String>() {
                        @Override
                        public void done(String s, BmobException e) {
                            if(e==null) {
                                Toast.makeText(CommentActivity.this,"评论成功",Toast.LENGTH_SHORT).show();
                                Log.e("save: ", s+" 保存成功");
                                finish();
                            } else {
                                Toast.makeText(CommentActivity.this,"评论失败",Toast.LENGTH_SHORT).show();
                                Log.e("saveError: ", e.getMessage());
                            }
                        }
                    });
                }
                break;
        }
    }

    private void initView() {
        linearLayout1.setVisibility(View.VISIBLE);
        linearLayout2.setVisibility(View.VISIBLE);
        linearLayout3.setVisibility(View.VISIBLE);
        submitBTN.setVisibility(View.VISIBLE);
        sugShowRV.setVisibility(View.GONE);
    }

    private void changeView() {
        linearLayout1.setVisibility(View.GONE);
        linearLayout2.setVisibility(View.GONE);
        linearLayout3.setVisibility(View.GONE);
        submitBTN.setVisibility(View.GONE);
        sugShowRV.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        suggestionSearch.destroy();
    }
}
