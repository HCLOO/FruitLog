package com.example.think.fruitlog.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.think.fruitlog.R;
import com.example.think.fruitlog.daotable.LogTag;
import com.example.think.fruitlog.util.SharedPreferencesUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import dao.DatabaseManager;

import static com.example.think.fruitlog.activtiy.LogTagActivity.logTagActivity;

public class EditLogFragment extends Fragment implements View.OnClickListener {

    EditText titleText,contentText;
    Button submit;
    ImageView back;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_edit_log,container,false);

        titleText=(EditText)view.findViewById(R.id.et_title);
        contentText=(EditText)view.findViewById(R.id.et_content);
        submit=(Button)view.findViewById(R.id.btn_submit);
        back=(ImageView)view.findViewById(R.id.image_back);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        back.setOnClickListener(this);
        submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_back:
                getActivity().onBackPressed();
                break;
            case R.id.btn_submit:
                String title=titleText.getText().toString();
                String content=contentText.getText().toString();
                if(title!=null&&!title.trim().isEmpty()
                        &&content!=null&&!content.trim().isEmpty()) {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                    Date date=new Date(System.currentTimeMillis());
                    String dateAndTime=simpleDateFormat.format(date);
                    /*DatabaseManager.getInstance().getDaoSession().insert(new LogTag(null,title,dateAndTime,content));
                    getActivity().onBackPressed();*/
                    com.example.think.fruitlog.bmobmodel.LogTag logTag=new com.example.think.fruitlog.bmobmodel.LogTag();
                    logTag.setUserName(SharedPreferencesUtil.getPreferenceUsername());
                    logTag.setTitle(title);
                    logTag.setTime(dateAndTime);
                    logTag.setContent(content);
                    logTag.save(new SaveListener<String>() {
                        @Override
                        public void done(String s, BmobException e) {
                            if(e==null) {
                                Log.e("save: ", s+" 保存成功");
                                getActivity().onBackPressed();
                            } else {
                                Log.e("saveError: ", e.getMessage());
                            }
                        }
                    });
                } else if(title==null||title.isEmpty()) {
                    Toast.makeText(logTagActivity,"标题不能为空",Toast.LENGTH_SHORT).show();
                } else if(content==null||content.isEmpty()) {
                    Toast.makeText(logTagActivity,"内容不能为空",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
