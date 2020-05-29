package com.example.apipractice_20200527.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.apipractice_20200527.R;
import com.example.apipractice_20200527.datas.Topic;
import com.example.apipractice_20200527.datas.TopicReply;

import java.util.List;

public class TopicReplyAdapter extends ArrayAdapter<TopicReply> {
    Context mContext;
    List<TopicReply> mList;
    LayoutInflater inf;
    public TopicReplyAdapter(@NonNull Context context, int resource, @NonNull List<TopicReply> objects) {
        super(context, resource, objects);
        mContext = context;
        mList = objects;
        inf = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        if(row==null){
            row = inf.inflate(R.layout.topic_reply_list_item, null);
        }
        return row;
    }
}
