package com.example.think.fruitlog.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.think.fruitlog.R;

public class LogDisplayFragment extends Fragment {

    CollapsingToolbarLayout collapsingToolbarLayout;
    TextView textTitle,textTime,textContent;
    Toolbar toolbar;
    public static final String TAG_DISPLAY_TITLE="display_title";
    public static final String TAG_DISPLAY_TIME="display_time";
    public static final String TAG_DISPLAY_CONTENT="display_content";
    String title,time,content;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_log_display,container,false);
        collapsingToolbarLayout=(CollapsingToolbarLayout)view.findViewById(R.id.tool_layout);
        textTitle=(TextView)view.findViewById(R.id.text_title);
        textTime=(TextView)view.findViewById(R.id.text_time);
        textContent=(TextView)view.findViewById(R.id.text_content);
        toolbar=(Toolbar)view.findViewById(R.id.tool_display);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ActionBar actionBar=((AppCompatActivity)getActivity()).getSupportActionBar();
        if(actionBar!=null)
            actionBar.setDisplayHomeAsUpEnabled(true);

        if(getArguments()!=null) {
            title=getArguments().getString(TAG_DISPLAY_TITLE);
            time=getArguments().getString(TAG_DISPLAY_TIME);
            content=getArguments().getString(TAG_DISPLAY_CONTENT);
        }

        collapsingToolbarLayout.setTitle("日志查看");
        textTitle.setText(title);
        textTime.setText(time);
        textContent.setText(content);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home) {
            getActivity().onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
