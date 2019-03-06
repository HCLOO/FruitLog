package com.example.think.fruitlog.activtiy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.think.fruitlog.R;
import com.example.think.fruitlog.fragment.LoginFragment;

public class LoginAndSignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_and_sign_up);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_loginorsignup,new LoginFragment())
                .commit();
    }
}
