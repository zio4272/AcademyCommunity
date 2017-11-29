package kr.co.cgb.academycommunity.data;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by PC on 2017-11-23.
 */

public class User implements Serializable {

    private int id;
    private String userLoginId;
    private String userLoginPw;
    private String userName;
    private String userPhoneNum;
    private int userGender; // 0 남자 , 1 여자
    private String userProfileUrl;
    private String userMyInfo;
    private Calendar birthDay;
    private boolean isTeacher;  // 강사인지 아닌지 여부 (추후 출결 관리)
    public List<Lecture> listenLecture = new ArrayList<>();

    public User() {
    }

    public User(int id, String userLoginId, String userLoginPw, String userName, String userPhoneNum, int userGender, String userProfileUrl, String userMyInfo, Calendar birthDay, boolean isTeacher) {
        this.id = id;
        this.userLoginId = userLoginId;
        this.userLoginPw = userLoginPw;
        this.userName = userName;
        this.userPhoneNum = userPhoneNum;
        this.userGender = userGender;
        this.userProfileUrl = userProfileUrl;
        this.userMyInfo = userMyInfo;
        this.birthDay = birthDay;
        this.isTeacher = isTeacher;
        this.listenLecture = listenLecture;


    }

    public static User getUserFromJson(JSONObject jsonObject) {
        User u = new User();

        try {
            u.id = jsonObject.getInt("id");
            u.userLoginId = jsonObject.getString("userId");
            u.userLoginPw = jsonObject.getString("userPw");
            u.userName = jsonObject.getString("username");
            u.userPhoneNum = jsonObject.getString("userphonenum");
            u.userGender = jsonObject.getInt("usergender");
            u.userProfileUrl = jsonObject.getString("userprofileimg");
            u.userMyInfo = jsonObject.getString("usermyinfo");


        } catch (JSONException e) {
            e.printStackTrace();
        }


        return u;
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

    public String getUserLoginPw() {
        return userLoginPw;
    }

    public void setUserLoginPw(String userLoginPw) {
        this.userLoginPw = userLoginPw;
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

    public Calendar getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Calendar birthDay) {
        this.birthDay = birthDay;
    }

    public boolean isTeacher() {
        return isTeacher;
    }

    public void setTeacher(boolean teacher) {
        isTeacher = teacher;
    }

    public List<Lecture> getListenLecture() {
        return listenLecture;
    }

    public void setListenLecture(List<Lecture> listenLecture) {
        this.listenLecture = listenLecture;
    }
}
