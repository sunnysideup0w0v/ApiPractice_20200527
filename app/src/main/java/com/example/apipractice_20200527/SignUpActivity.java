package com.example.apipractice_20200527;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

import com.example.apipractice_20200527.databinding.ActivitySignUpBinding;

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
        binding.pwEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkPasswords();
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
                checkPasswords();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }
//   응용문제 => 비번/ 비번확인 중 어느것을 타이핑해도 매번 둘 다 검사하게끔.
    void checkPasswords() {
        String pw = binding.pwEdt.getText().toString();
        if(pw.length() == 0){
            binding.pwCheckResultTxt.setText("비밀번호를 입력해주세요");
        } else if(pw.length()<8){
            binding.pwCheckResultTxt.setText("비밀번호가 너무 짧습니다");
        } else {
            binding.pwCheckResultTxt.setText("사용해도 좋은 비밀번호입니다");
        }
        String pwRepeat = binding.pwRepeatEdt.getText().toString();
        if(pwRepeat.length()==0){
            binding.pwRepeatCheckResultTxt.setText("비밀번호를 입력해주세요");
        } else if(pwRepeat.equals(pw)){
            binding.pwRepeatCheckResultTxt.setText("비밀번호가 서로 일치합니다");
        } else {
            binding.pwRepeatCheckResultTxt.setText("비밀번호가 서로 일치하지 않습니다");
        }
    }
    @Override
    public void setValues() {

    }
}
