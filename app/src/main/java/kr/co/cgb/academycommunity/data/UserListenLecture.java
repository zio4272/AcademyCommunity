package kr.co.cgb.academycommunity.data;

import java.io.Serializable;

/**
 * Created by PC on 2017-11-27.
 */

public class UserListenLecture implements Serializable {

    private int id;
    private User listenUser;   // 누가
    private Lecture listenLecture; // 어떤강의를

    public UserListenLecture() {
    }

    public UserListenLecture(int id, User listenUser, Lecture listenLecture) {
        this.id = id;
        this.listenUser = listenUser;
        this.listenLecture = listenLecture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getListenUser() {
        return listenUser;
    }

    public void setListenUser(User listenUser) {
        this.listenUser = listenUser;
    }

    public Lecture getListenLecture() {
        return listenLecture;
    }

    public void setListenLecture(Lecture listenLecture) {
        this.listenLecture = listenLecture;
    }
}
