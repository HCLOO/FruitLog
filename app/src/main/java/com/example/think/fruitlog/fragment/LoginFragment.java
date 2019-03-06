package com.example.think.fruitlog.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.think.fruitlog.R;
import com.example.think.fruitlog.activtiy.MainActivity;
import com.example.think.fruitlog.bmobmodel.User;
import com.example.think.fruitlog.util.SharedPreferencesUtil;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

public class LoginFragment extends Fragment {

    private EditText ETUsername,ETPassword;
    private TextView TVLogin,TVSignUp;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_login,container,false);
        ETUsername=(EditText) view.findViewById(R.id.et_username);
        ETPassword=(EditText) view.findViewById(R.id.et_password);
        TVLogin=(TextView) view.findViewById(R.id.btn_login);
        TVSignUp=(TextView) view.findViewById(R.id.btn_goto_signup);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ETUsername.setText("HCLOO");
        ETPassword.setText("iq12345678");
        TVLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!ETUsername.getText().toString().isEmpty() && !ETPassword.getText().toString().isEmpty()) {
                    login(ETUsername.getText().toString(),ETPassword.getText().toString());
                } else {
                    Toast.makeText(getContext(),"用户名或密码不能为空",Toast.LENGTH_SHORT).show();
                }
            }
        });
        TVSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fl_loginorsignup,new SignUpFragment())
                        .addToBackStack("LoginFragment")
                        .commit();
            }
        });
    }

    private void login(final String username, String password) {
        User loginUser=new User();
        loginUser.setUsername(username);
        loginUser.setPassword(password);
        loginUser.login(new SaveListener<BmobUser>() {
            @Override
            public void done(BmobUser bmobUser, BmobException e) {
                if(e==null){
                    Toast.makeText(getContext(),bmobUser.getUsername()+"登录成功",Toast.LENGTH_SHORT).show();
                    getUserInfo(username);
                }else {
                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.e("error: ", e.getMessage());
                }
            }
        });
    }

    private void getUserInfo(String username) {
        BmobQuery<User> bmobQuery=new BmobQuery<User>();
        bmobQuery.addWhereEqualTo("username",username);
        bmobQuery.findObjects(new FindListener<User>() {
            @Override
            public void done(List<User> list, BmobException e) {
                if(e==null){
                    User user=list.get(0);
                    SharedPreferencesUtil.setPreferenceUsername(user.getUsername());
                    SharedPreferencesUtil.setPreferenceEmail(user.getEmail());
                    SharedPreferencesUtil.setPreferenceSex(user.getSex());
                    Log.e("UserInfo: ", list.size()+" "+user.getUsername()+" "+
                    user.getEmail()+" "+user.getSex());
                    Intent intent = new Intent(getContext(), MainActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }else {
                    Log.e("error: ", e.getMessage());
                }
            }
        });
    }

    /*private  void save() {
        BuyLog buyLog=new BuyLog();
        buyLog.setUsername("HCLOO");
        buyLog.setFruitname("mangguo");
        buyLog.setTime("20181121");
        buyLog.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if(e==null){
                    Toast.makeText(getContext(),s+" 保存成功",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getContext(), MainActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                } else {
                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.e("error: ", e.getMessage());
                }
            }
        });
    }*/
}
