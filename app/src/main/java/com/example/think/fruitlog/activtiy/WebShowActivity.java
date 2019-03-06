package com.example.think.fruitlog.activtiy;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.think.fruitlog.R;

public class WebShowActivity extends AppCompatActivity {

    private WebView webView;
    public static final String TAG_NEW_TITLE ="tag_new_title";
    public static final String TAG_NEW_URL="tag_new_url";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_show);

        Toolbar toolbar=(Toolbar)findViewById(R.id.tool_web);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null)
            actionBar.setDisplayHomeAsUpEnabled(true);

        webView = (WebView)findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());

        String title = getIntent().getStringExtra(TAG_NEW_TITLE);
        String uri = getIntent().getStringExtra(TAG_NEW_URL);
        if(actionBar!=null) {
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setTitle(title);
        }
        webView.loadUrl(uri);
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
