package com.example.think.fruitlog.activtiy;

import android.support.design.widget.TabLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import com.example.think.fruitlog.R;
import com.example.think.fruitlog.fragment.NewsFragment;
import com.example.think.fruitlog.util.SharedPreferencesUtil;

public class NewsActivity extends AppCompatActivity {

    private NewsFragment newsFragment;
    private TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        Toolbar toolbar=(Toolbar)findViewById(R.id.tool_news);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null)
            actionBar.setDisplayHomeAsUpEnabled(true);

        tabLayout=(TabLayout)findViewById(R.id.tab_layout);
        newsFragment=(NewsFragment)getSupportFragmentManager().findFragmentById(R.id.fragment_news);
        initTabTitle();
        initFunction();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initTabTitle() {
        tabLayout.addTab(tabLayout.newTab().setText("社会"));
        tabLayout.addTab(tabLayout.newTab().setText("国内"));
        tabLayout.addTab(tabLayout.newTab().setText("国际"));
        tabLayout.addTab(tabLayout.newTab().setText("娱乐"));
        tabLayout.addTab(tabLayout.newTab().setText("体育"));
        tabLayout.addTab(tabLayout.newTab().setText("NBA"));
        tabLayout.addTab(tabLayout.newTab().setText("足球"));
        tabLayout.addTab(tabLayout.newTab().setText("科技"));
        tabLayout.addTab(tabLayout.newTab().setText("创业"));
        tabLayout.addTab(tabLayout.newTab().setText("苹果"));
        tabLayout.addTab(tabLayout.newTab().setText("军事"));
        tabLayout.addTab(tabLayout.newTab().setText("互联"));
        tabLayout.addTab(tabLayout.newTab().setText("旅游"));
        tabLayout.addTab(tabLayout.newTab().setText("健康"));
        tabLayout.addTab(tabLayout.newTab().setText("奇闻"));
        tabLayout.addTab(tabLayout.newTab().setText("VR"));
        tabLayout.addTab(tabLayout.newTab().setText("IT"));
        tabLayout.addTab(tabLayout.newTab().setText("AI"));
    }

    private void initFunction() {
        tabLayout.getTabAt(SharedPreferencesUtil.getTagPreferenceLatestTabPosition()).select();
        String initContent=null;
        switch (SharedPreferencesUtil.getTagPreferenceLatestTabPosition()) {
            case 0:
                initContent="social";
                break;
            case 1:
                initContent="guonei";
                break;
            case 2:
                initContent="world";
                break;
            case 3:
                initContent="huabian";
                break;
            case 4:
                initContent="tiyu";
                break;
            case 5:
                initContent="nba";
                break;
            case 6:
                initContent="football";
                break;
            case 7:
                initContent="keji";
                break;
            case 8:
                initContent="startup";
                break;
            case 9:
                initContent="apple";
                break;
            case 10:
                initContent="military";
                break;
            case 11:
                initContent="mobile";
                break;
            case 12:
                initContent="travel";
                break;
            case 13:
                initContent="health";
                break;
            case 14:
                initContent="qiwen";
                break;
            case 15:
                initContent="vr";
                break;
            case 16:
                initContent="it";
                break;
            case 17:
                initContent="ai";
                break;
        }
        newsFragment.setContent(initContent);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                SharedPreferencesUtil.setTagPreferenceLatestTabPosition(tab.getPosition());
                String content=null;
                switch (tab.getPosition()) {
                    case 0:
                        content="social";
                        break;
                    case 1:
                        content="guonei";
                        break;
                    case 2:
                        content="world";
                        break;
                    case 3:
                        content="huabian";
                        break;
                    case 4:
                        content="tiyu";
                        break;
                    case 5:
                        content="nba";
                        break;
                    case 6:
                        content="football";
                        break;
                    case 7:
                        content="keji";
                        break;
                    case 8:
                        content="startup";
                        break;
                    case 9:
                        content="apple";
                        break;
                    case 10:
                        content="military";
                        break;
                    case 11:
                        content="mobile";
                        break;
                    case 12:
                        content="travel";
                        break;
                    case 13:
                        content="health";
                        break;
                    case 14:
                        content="qiwen";
                        break;
                    case 15:
                        content="vr";
                        break;
                    case 16:
                        content="it";
                        break;
                    case 17:
                        content="ai";
                        break;
                }
                newsFragment.setContent(content);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
