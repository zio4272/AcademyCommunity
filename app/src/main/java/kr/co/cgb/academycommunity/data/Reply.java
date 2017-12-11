package kr.co.cgb.academycommunity.data;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by PC on 2017-11-24.
 */

public class Reply implements Serializable {

    private int replyId;
    //    private int replyType;  // 0 - 댓글 , 1 - 댓글의 댓글
    private int parentId = -1; // -1은 게시물에 달린 댓글 , 그외는 replyId 0~ 댓글에 달린 댓글
    //    private User replyWriteName;
//    private String replyWriteName;
    private String replyContent;
    private Calendar replyDate = Calendar.getInstance(); // 작성시간은 무조건 현재시간으로
    private User writeUser;

    public Post post; // 어떤 게시물의 댓글인지 파악.
    public List<Reply> replies = new ArrayList<>();

    public User tagUserName = null;

    public static Reply getReplyFromJson(JSONObject jsonObject) {
        Reply r = new Reply();

        try {
            r.replyId = jsonObject.getInt("replyId");
            r.parentId = jsonObject.getInt("parentId");
//            r.replyWriteName = User.getUserFromJson(jsonObject);
//            r.replyWriteName = jsonObject.getString("replyWriteName");
            User u = User.getUserFromJson(jsonObject.getJSONObject("name"));
            r.writeUser = u;
            User tagU = User.getUserFromJson(jsonObject.getJSONObject("parent_user"));
            r.tagUserName = tagU;
            r.replyContent = jsonObject.getString("replyContent");
            long time = jsonObject.getLong("replyDate");
            r.replyDate.setTimeInMillis(time);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return r;
    }

    public Reply() {
    }

    public Reply(int replyId, int parentId, String replyContent, Calendar replyDate, User writeUser) {
        this.replyId = replyId;
        this.parentId = parentId;
        this.replyContent = replyContent;
        this.replyDate = replyDate;
        this.writeUser = writeUser;
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

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public Calendar getReplyDate() {
        return replyDate;
    }

    public void setReplyDate(Calendar replyDate) {
        this.replyDate = replyDate;
    }

    public User getWriteUser() {
        return writeUser;
    }

    public void setWriteUser(User writeUser) {
        this.writeUser = writeUser;
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

    public User getTagUserName() {
        return tagUserName;
    }

    public void setTagUserName(User tagUserName) {
        this.tagUserName = tagUserName;
    }
}
