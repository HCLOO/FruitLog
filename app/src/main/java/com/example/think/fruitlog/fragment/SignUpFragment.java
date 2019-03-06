package com.example.think.fruitlog.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.example.think.fruitlog.R;
import com.example.think.fruitlog.bmobmodel.User;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class SignUpFragment extends Fragment {

    private EditText ETUsername,ETPassword,ETEmail;
    private TextView TVLogin,TVSignUp;
    private RadioGroup RGSexSelect;
    private String sexText="";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_signup,container,false);
        ETUsername=(EditText) view.findViewById(R.id.et_username);
        ETPassword=(EditText) view.findViewById(R.id.et_password);
        ETEmail=(EditText) view.findViewById(R.id.et_email);
        TVLogin=(TextView) view.findViewById(R.id.btn_goto_login);
        TVSignUp=(TextView) view.findViewById(R.id.btn_signup);
        RGSexSelect=(RadioGroup) view.findViewById(R.id.rg_sex_select);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        TVLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStack();
            }
        });
        TVSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!ETUsername.getText().toString().isEmpty()&&!ETPassword.getText().toString().isEmpty()
                        &&!ETEmail.getText().toString().isEmpty()&&!sexText.isEmpty()) {
                    User signUpUser=new User();
                    signUpUser.setUsername(ETUsername.getText().toString());
                    signUpUser.setPassword(ETPassword.getText().toString());
                    signUpUser.setSex(sexText);
                    signUpUser.setEmail(ETEmail.getText().toString());
                    signUpUser.signUp(new SaveListener<Object>() {
                        @Override
                        public void done(Object o, BmobException e) {
                            if (e == null) {
                                Toast.makeText(getContext(), "注册成功", Toast.LENGTH_SHORT).show();
                                getFragmentManager().popBackStack();
                            } else {
                                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                Log.e("error: ", e.getMessage());
                            }
                        }
                    });
                } else {
                    Toast.makeText(getContext(),"注册信息不全",Toast.LENGTH_SHORT).show();
                }
            }
        });
        RGSexSelect.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.sex_man:
                        sexText="男";
                        break;
                    case R.id.sex_woman:
                        sexText="女";
                        break;
                }
            }
        });
    }
}
