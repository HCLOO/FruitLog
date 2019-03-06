package com.example.think.fruitlog.activtiy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.think.fruitlog.R;
import com.example.think.fruitlog.fragment.LogTagFragment;

public class LogTagActivity extends AppCompatActivity {

    public static LogTagActivity logTagActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_tag);

        logTagActivity=this;
        getFragmentManager().beginTransaction().replace(R.id.frame_log_tag,new LogTagFragment()).commit();
    }
}
