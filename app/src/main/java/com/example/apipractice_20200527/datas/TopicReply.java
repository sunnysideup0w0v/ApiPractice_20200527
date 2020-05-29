package com.example.apipractice_20200527.datas;

import org.json.JSONException;
import org.json.JSONObject;

public class TopicReply {
    private int id;
    private String content;
    private String side;


    public static TopicReply getTopicReplyFromJson(JSONObject jsonObject){
        TopicReply tp = new TopicReply();
        try {
            tp.id = jsonObject.getInt("id");
            tp.content = jsonObject.getString("content");
            tp.content = jsonObject.getString("side");
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return tp;
    }

    public TopicReply() {
    }

    public TopicReply(int id, String content, String side) {
        this.id = id;
        this.content = content;
        this.side = side;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }
}
