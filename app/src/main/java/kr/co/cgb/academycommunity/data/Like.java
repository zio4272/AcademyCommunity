package kr.co.cgb.academycommunity.data;

import java.io.Serializable;

/**
 * Created by PC on 2017-11-27.
 */

public class Like implements Serializable {

    private int id;
    private User likeUser;
    private Post likePost;

    public Like() {
    }

    public Like(int id, User likeUser, Post likePost) {
        this.id = id;
        this.likeUser = likeUser;
        this.likePost = likePost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getLikeUser() {
        return likeUser;
    }

    public void setLikeUser(User likeUser) {
        this.likeUser = likeUser;
    }

    public Post getLikePost() {
        return likePost;
    }

    public void setLikePost(Post likePost) {
        this.likePost = likePost;
    }
}
