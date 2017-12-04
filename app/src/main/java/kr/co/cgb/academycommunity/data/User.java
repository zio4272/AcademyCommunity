package kr.co.cgb.academycommunity.data;

import com.bumptech.glide.Glide;

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
    private String loginId;
    private String loginPw;
    private String userName;
    private String userPhoneNum;
    private int userGender; // 0 남자 , 1 여자
    private String userProfileImg;
    private String userMyInfo;
    private Calendar birthDay;
    private boolean UserIsTeacher;  // 강사인지 아닌지 여부 (추후 출결 관리)
    public Lecture listenLecture;

    public User() {
    }

    public User(int id, String loginId, String loginPw, String userName, String userPhoneNum, int userGender, String userProfileImg, String userMyInfo, Calendar birthDay, boolean userIsTeacher, Lecture listenLecture) {
        this.id = id;
        this.loginId = loginId;
        this.loginPw = loginPw;
        this.userName = userName;
        this.userPhoneNum = userPhoneNum;
        this.userGender = userGender;
        this.userProfileImg = userProfileImg;
        this.userMyInfo = userMyInfo;
        this.birthDay = birthDay;
        UserIsTeacher = userIsTeacher;
        this.listenLecture = listenLecture;
    }

    public static User getUserFromJson(JSONObject jsonObject) {
        User u = new User();

        try {
            u.id = jsonObject.getInt("id");
            u.loginId = jsonObject.getString("loginId");
            u.loginPw = jsonObject.getString("loginPw");
            u.userName = jsonObject.getString("userName");
            u.userPhoneNum = jsonObject.getString("userPhoneNum");
            u.userGender = jsonObject.getInt("userGender");
            if (!jsonObject.isNull("userProfileImg")) {
                u.userProfileImg = jsonObject.getString("userProfileImg");
            }
            else {
                u.userProfileImg = "noImage";
            }
            u.userMyInfo = jsonObject.getString("userMyInfo");
//            u.listenLecture = new Lecture();
//            u.listenLecture.setLectureName(jsonObject.getString("lectureName"));
            u.listenLecture = Lecture.getLectureFromJson(jsonObject);

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

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getLoginPw() {
        return loginPw;
    }

    public void setLoginPw(String loginPw) {
        this.loginPw = loginPw;
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

    public String getUserProfileImg() {
        return userProfileImg;
    }

    public void setUserProfileImg(String userProfileImg) {
        this.userProfileImg = userProfileImg;
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

    public boolean isUserIsTeacher() {
        return UserIsTeacher;
    }

    public void setUserIsTeacher(boolean userIsTeacher) {
        UserIsTeacher = userIsTeacher;
    }

    public Lecture getListenLecture() {
        return listenLecture;
    }

    public void setListenLecture(Lecture listenLecture) {
        this.listenLecture = listenLecture;
    }
}
