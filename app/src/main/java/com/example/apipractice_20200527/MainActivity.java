package com.example.apipractice_20200527;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.example.apipractice_20200527.databinding.ActivityMainBinding;
import com.example.apipractice_20200527.datas.Topic;
import com.example.apipractice_20200527.datas.User;
import com.example.apipractice_20200527.utils.ServerUtil;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends BaseActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {

    }

    @Override
    public void setValues() {
        ServerUtil.getRequestMainInfo(mContext, new ServerUtil.JsonResponseHandler() {
            @Override
            public void onResponse(JSONObject json) {
                try {
                    int code = json.getInt("code");
                    if(code==200){
                        JSONObject data = json.getJSONObject("data");
                        JSONObject user = data.getJSONObject("user");
                        JSONObject topic = data.getJSONObject("topic");
                        final Topic thisWeekTopic = Topic.getTopicFromJson(topic);

                        final User me = User.getUserFromJson(user);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                binding.nickName.setText(me.getNickName());
                                binding.emailTxt.setText(me.getEmail());
                                Glide.with(mContext).load(thisWeekTopic.getImageUrl()).into(binding.topicImg);
                            }
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
