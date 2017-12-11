package kr.co.cgb.academycommunity.util;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONObject;

import kr.co.cgb.academycommunity.data.Post;
import kr.co.cgb.academycommunity.data.User;

/**
 * Created by PC on 2017-11-21.
 */

public class ContextUtil {

    private static User loginUser = null;

    private static final String prefName = "AcademyCommunity";

    private static final String USER_ID = "USER_ID";
    private static final String USER_LOGIN_ID = "USER_LOGIN_ID";
    private static final String USER_NAME = "USER_NAME";
    private static final String USER_GENDER = "USER_GENDER";
    private static final String USER_PHONE_NUM = "USER_PHONE_NUM";
    private static final String USER_PROFILE_IMG = "USER_PROFILE_IMG";
    private static final String USER_MY_INFO = "USER_MY_INFO";
    private static final String USER_ISTEACHER = "USER_ISTEACHER";


    public static void login(Context context, User user) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);


        pref.edit().putInt(USER_ID, user.getId()).apply();
        pref.edit().putString(USER_LOGIN_ID, user.getLoginId());
        pref.edit().putString(USER_NAME, user.getUserName()).apply();
        pref.edit().putInt(USER_GENDER, user.getUserGender()).apply();
        pref.edit().putString(USER_PHONE_NUM, user.getUserPhoneNum()).apply();
        pref.edit().putString(USER_PROFILE_IMG, user.getUserProfileImg()).apply();
        pref.edit().putString(USER_MY_INFO, user.getUserMyInfo()).apply();
    }

    public static User getLoginUserInfo(Context context) {
        User loginUser = new User();

        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);


        if (pref.getInt(USER_ID, 0) >= 1) {
//            사용자의 숫자 아이디가 1이상이므로, 로그인이 되어있다고 판단.

            loginUser.setId(pref.getInt(USER_ID, 0));
            loginUser.setLoginId(pref.getString(USER_LOGIN_ID, ""));
            loginUser.setUserName(pref.getString(USER_NAME, ""));


////            가져올수있는데이터:String -> Calendar : SimpleDateFormat
//
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
//            Calendar birthDay = Calendar.getInstance();
//
//            try {
//                Date birthDayDate = sdf.parse(pref.getString(USER_BIRTHDAY, ""));
//                birthDay.setTime(birthDayDate);
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//
//            loginUser.setBirthDay(birthDay);

            loginUser.setUserGender(pref.getInt(USER_GENDER, 0));
            loginUser.setUserPhoneNum(pref.getString(USER_PHONE_NUM, ""));
            loginUser.setUserProfileImg(pref.getString(USER_PROFILE_IMG, ""));
            loginUser.setUserMyInfo(pref.getString(USER_MY_INFO, ""));


        } else {
//            사용자 숫자 아이디가 0이거나 그보다 작으므로, 로그아웃 상태라고 판단.
//            로그아웃일 경우 : 사용자 정보에 null
            loginUser = null;


        }


        return loginUser;
    }

    public static void logout(Context context) {
        loginUser = null;
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);

        pref.edit().putInt(USER_ID, -1).commit();
        pref.edit().putString(USER_LOGIN_ID, "").commit();
        pref.edit().putString(USER_NAME, "").commit();
        pref.edit().putInt(USER_GENDER, -1).commit();
        pref.edit().putString(USER_PHONE_NUM, "").commit();
        pref.edit().putString(USER_PROFILE_IMG, "").commit();
        pref.edit().putString(USER_MY_INFO, "").commit();
    }




}
