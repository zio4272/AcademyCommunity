package kr.co.cgb.academycommunity.data;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

/**
 * Created by the on 2017-11-22.
 */

public class Post implements Serializable {

    private int id;
    private String postContent;
    private int createdAt;

    private User userWriterData;

    public List<Post> postList;


    public Post() {
    }

    public Post(int id, String postContent, int createdAt, User userWriterData) {
        this.id = id;
        this.postContent = postContent;
        this.createdAt = createdAt;
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

    public int getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(int createdAt) {
        this.createdAt = createdAt;
    }

    public User getUserWriterData() {
        return userWriterData;
    }

    public void setUserWriterData(User userWriterData) {
        this.userWriterData = userWriterData;
    }
}
