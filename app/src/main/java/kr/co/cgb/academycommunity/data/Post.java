package kr.co.cgb.academycommunity.data;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by the on 2017-11-22.
 */

public class Post implements Serializable {

    private int id;
    private String postContent;
    private int postDate;

    public User userWriterData;

    public List<Reply> replyList = new ArrayList<>();

    public static Post getPostFromJson(JSONObject jsonObject) {
        Post p = new Post();


        try {
            p.id = jsonObject.getInt("lectureId");
            p.postContent = jsonObject.getString("postContent");
            p.postDate = jsonObject.getInt("postDate");
            p.userWriterData = User.getUserFromJson(jsonObject);


        } catch (JSONException e) {
            e.printStackTrace();
        }


        return p;
    }




    public Post() {
    }

    public Post(int id, String postContent, int postDate, User userWriterData) {
        this.id = id;
        this.postContent = postContent;
        this.postDate = postDate;
        this.userWriterData = userWriterData;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public int getPostDate() {
        return postDate;
    }

    public void setPostDate(int postDate) {
        this.postDate = postDate;
    }

    public User getUserWriterData() {
        return userWriterData;
    }

    public void setUserWriterData(User userWriterData) {
        this.userWriterData = userWriterData;
    }
    public List<Reply> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<Reply> replyList) {
        this.replyList = replyList;
    }
}
