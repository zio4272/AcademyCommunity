package kr.co.cgb.academycommunity.data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by PC on 2017-11-27.
 */

public class Notify implements Serializable {

    private int id;
    private int ownerId; // 누가
    private int actorId; // 누구에게
    private int postId; // 어떤글을
    private int replyId; // 어떤댓글을
    private String postReply;
    private String replyReply;
    private String postLike;
    private String replyLike;

    public List<User> users;
    public List<Post> posts;
    public List<Reply> replies;
    public List<Like> likes;

    public Notify() {
    }

    public Notify(int id, int ownerId, int actorId, int postId, int replyId, String postReply, String replyReply, String postLike, String replyLike, List<User> users, List<Post> posts, List<Reply> replies, List<Like> likes) {
        this.id = id;
        this.ownerId = ownerId;
        this.actorId = actorId;
        this.postId = postId;
        this.replyId = replyId;
        this.postReply = postReply;
        this.replyReply = replyReply;
        this.postLike = postLike;
        this.replyLike = replyLike;
        this.users = users;
        this.posts = posts;
        this.replies = replies;
        this.likes = likes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public int getActorId() {
        return actorId;
    }

    public void setActorId(int actorId) {
        this.actorId = actorId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getReplyId() {
        return replyId;
    }

    public void setReplyId(int replyId) {
        this.replyId = replyId;
    }

    public String getPostReply() {
        return postReply;
    }

    public void setPostReply(String postReply) {
        this.postReply = postReply;
    }

    public String getReplyReply() {
        return replyReply;
    }

    public void setReplyReply(String replyReply) {
        this.replyReply = replyReply;
    }

    public String getPostLike() {
        return postLike;
    }

    public void setPostLike(String postLike) {
        this.postLike = postLike;
    }

    public String getReplyLike() {
        return replyLike;
    }

    public void setReplyLike(String replyLike) {
        this.replyLike = replyLike;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Reply> getReplies() {
        return replies;
    }

    public void setReplies(List<Reply> replies) {
        this.replies = replies;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }
}
