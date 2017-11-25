package kr.co.cgb.academycommunity.data;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by PC on 2017-11-24.
 */

public class Reply implements Serializable {

    int replyId;
    int parentReplyId;
    // 0이면 그냥 댓글. 1~그 외 : 대댓글.

    String writerName;

    String replyContent;
    Calendar createdAt;

//    정석적인 방법.
//    UserData writerData;
    // 댓글 작성자도 UserData형태로 저장해 두는것이 올바른 방법.

    public Reply() {
    }

    public Reply(int replyId, int parentReplyId, String writerName, String replyContent, Calendar createdAt) {
        this.replyId = replyId;
        this.parentReplyId = parentReplyId;
        this.writerName = writerName;
        this.replyContent = replyContent;
        this.createdAt = createdAt;
    }

    public int getReplyId() {
        return replyId;
    }

    public void setReplyId(int replyId) {
        this.replyId = replyId;
    }

    public int getParentReplyId() {
        return parentReplyId;
    }

    public void setParentReplyId(int parentReplyId) {
        this.parentReplyId = parentReplyId;
    }

    public String getWriterName() {
        return writerName;
    }

    public void setWriterName(String writerName) {
        this.writerName = writerName;
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

}
