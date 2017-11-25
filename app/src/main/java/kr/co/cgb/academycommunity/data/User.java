package kr.co.cgb.academycommunity.data;

import java.io.Serializable;

/**
 * Created by PC on 2017-11-23.
 */

public class User implements Serializable {

    private int id;
    private String userLoginId;
    private String userName;
    private String userPhoneNum;
    private int userGender; // 0 남자 , 1 여자
    private String userProfileUrl;
    private String userMyInfo;

    public User() {
    }

    public User(int id, String userLoginId, String userName, String userPhoneNum, int userGender, String userProfileUrl, String userMyInfo) {
        this.id = id;
        this.userLoginId = userLoginId;
        this.userName = userName;
        this.userPhoneNum = userPhoneNum;
        this.userGender = userGender;
        this.userProfileUrl = userProfileUrl;
        this.userMyInfo = userMyInfo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserLoginId() {
        return userLoginId;
    }

    public void setUserLoginId(String userLoginId) {
        this.userLoginId = userLoginId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhoneNum() {
        return userPhoneNum;
    }

    public void setUserPhoneNum(String userPhoneNum) {
        this.userPhoneNum = userPhoneNum;
    }

    public int getUserGender() {
        return userGender;
    }

    public void setUserGender(int userGender) {
        this.userGender = userGender;
    }

    public String getUserProfileUrl() {
        return userProfileUrl;
    }

    public void setUserProfileUrl(String userProfileUrl) {
        this.userProfileUrl = userProfileUrl;
    }

    public String getUserMyInfo() {
        return userMyInfo;
    }

    public void setUserMyInfo(String userMyInfo) {
        this.userMyInfo = userMyInfo;
    }
}
