package com.example.apipractice_20200527;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.apipractice_20200527.utils.ContextUtil;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {

    }

    @Override
    public void setValues() {
//        저장 토큰? 자동 로그인 true? => mainActivity로 이동
//        둘 중 하나라도 x => loginActivity]
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(!ContextUtil.getLoginUserToken(mContext).equals("") && ContextUtil.isAutoLogin(mContext)){
                    Intent myIntent = new Intent(mContext, MainActivity.class);
                    startActivity(myIntent);
                } else {
                    Intent myIntent = new Intent(mContext, LoginActivity.class);
                    startActivity(myIntent);
                }
                finish();
            }
        },2000);
    }
}

