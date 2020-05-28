package com.example.apipractice_20200527;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apipractice_20200527.databinding.ActivitySignUpBinding;
import com.example.apipractice_20200527.utils.ServerUtil;

import org.json.JSONException;
import org.json.JSONObject;

public class SignUpActivity extends BaseActivity {
    ActivitySignUpBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up);
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {
        binding.idCheckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputEmail = binding.emailEdt.getText().toString();
                ServerUtil.getRequestDuplicatedCheck(mContext, inputEmail, "EMAIL", new ServerUtil.JsonResponseHandler() {
                    @Override
                    public void onResponse(JSONObject json) {
                        Log.d("중복처리 응답확인",json.toString());
                        boolean idCheckOk = false;
                        try {
                            int code = json.getInt("code");
                            if(code == 200){
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(mContext, "사용해도 좋은 아이디 입니다", Toast.LENGTH_SHORT).show();
                                        binding.idCheckResultTxt.setText("사용해도 좋은 아이디 입니다");
                                        idCheckOk = true;
                                    }
                                });
                            } else {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(mContext, "중복되는 아이디 입니다", Toast.LENGTH_SHORT).show();
                                        binding.idCheckResultTxt.setText("중복되는 아이디 입니다");
                                    }
                                });
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
        binding.pwEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkSignUpEnable();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        binding.pwRepeatEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkSignUpEnable();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }
//   응용문제 => 비번/ 비번확인 중 어느것을 타이핑해도 매번 둘 다 검사하게끔.
    boolean checkPasswords() {
        boolean isPwOk = false;
        String pw = binding.pwEdt.getText().toString();
        if(pw.length() == 0){
            binding.pwCheckResultTxt.setText("비밀번호를 입력해주세요");
        } else if(pw.length()<8){
            binding.pwCheckResultTxt.setText("비밀번호가 너무 짧습니다");
            isPwOk = true;
        } else {
            binding.pwCheckResultTxt.setText("사용해도 좋은 비밀번호입니다");
        }
        boolean isPwRepeatOk = false;
        String pwRepeat = binding.pwRepeatEdt.getText().toString();
        if(pwRepeat.length()==0){
            binding.pwRepeatCheckResultTxt.setText("비밀번호를 입력해주세요");
        } else if(pwRepeat.equals(pw)){
            binding.pwRepeatCheckResultTxt.setText("비밀번호가 서로 일치합니다");
            isPwRepeatOk = true;
        } else {
            binding.pwRepeatCheckResultTxt.setText("비밀번호가 서로 일치하지 않습니다");
        }

        return isPwOk && isPwRepeatOk;

    }
//    아이디 중복/ 비번확인/ 닉네임 중복이 모두 통과되어야 회원가입 버튼 활성화.
//    하나라도 틀리면 회원가입 버튼 비활성화.
    void checkSignUpEnable() {
        boolean isAllPwOk = checkPasswords();
        boolean isIdDuplCheckOk = idcheck;
        binding.signUpBtn.setEnabled(isAllPwOk && isIdDuplCheckOk);
    }

    @Override
    public void setValues() {

    }
}
