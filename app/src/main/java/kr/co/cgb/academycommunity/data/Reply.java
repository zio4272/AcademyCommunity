package kr.co.cgb.academycommunity.data;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

/**
 * Created by PC on 2017-11-24.
 */

public class Reply implements Serializable {

    private int replyId;
//    private int replyType;  // 0 - 댓글 , 1 - 댓글의 댓글
    private int parentId = -1; // -1은 게시물에 달린 댓글 , 그외는 replyId 0~ 댓글에 달린 댓글
    private User userWriterData;
    private String replyContent;
    private Calendar createdAt;

    public Post post; // 어떤 게시물의 댓글인지 파악.

    public List<Reply> replies;

    public Reply() {
    }

    public Reply(int replyId, int parentId, User userWriterData, String replyContent, Calendar createdAt, Post post, List<Reply> replies) {
        this.replyId = replyId;
        this.parentId = parentId;
        this.userWriterData = userWriterData;
        this.replyContent = replyContent;
        this.createdAt = createdAt;
        this.post = post;
        this.replies = replies;
    }

    public int getReplyId() {
        return replyId;
    }

    public void setReplyId(int replyId) {
        this.replyId = replyId;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public User getUserWriterData() {
        return userWriterData;
    }

    public void setUserWriterData(User userWriterData) {
        this.userWriterData = userWriterData;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public Calendar getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Calendar createdAt) {
        this.createdAt = createdAt;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public List<Reply> getReplies() {
        return replies;
    }

    public void setReplies(List<Reply> replies) {
        this.replies = replies;
    }
}
