package kr.co.cgb.academycommunity.util;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by KJ_Studio on 2015-12-24.
 */
public class ServerUtil {

    private static final String TAG = ServerUtil.class.getSimpleName();

    private final static String BASE_URL = "http://192.168.20.119:8080/"; // 라이브서버
//    private final static String BASE_URL = "http://share-tdd.com/"; // 개발서버

    //    JSON 처리 부분 인터페이스.
    public interface JsonResponseHandler {
        void onResponse(JSONObject json);
    }



    public static void getPost(final Context context, final JsonResponseHandler handler) {
//        기능에 따라 매번 주소를 다르게 적어줘야함.
        String url = BASE_URL + "cgb/get_post";

//        기능을 사용하기 위해 필요한 데이터를 담는 부분.

        Map<String, String> data = new HashMap<String, String>();


        AsyncHttpRequest.get(context, url, data, true, new AsyncHttpRequest.HttpResponseHandler() {

            @Override
            public boolean onPrepare() {
                return true;
            }

            @Override
            public void onResponse(String response) {
                System.out.println(response);
                try {
                    JSONObject json = new JSONObject(response);

                    if (handler != null)
                        handler.onResponse(json);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onCancelled() {

            }

        });
    }



    public static void idCheck(final Context context, final String loginId, final JsonResponseHandler handler) {
//        기능에 따라 매번 주소를 다르게 적어줘야함.
        String url = BASE_URL + "cgb/user_id_check";

//        기능을 사용하기 위해 필요한 데이터를 담는 부분.

        Map<String, String> data = new HashMap<String, String>();
        data.put("login_id", loginId);


        AsyncHttpRequest.get(context, url, data, true, new AsyncHttpRequest.HttpResponseHandler() {

            @Override
            public boolean onPrepare() {
                return true;
            }

            @Override
            public void onResponse(String response) {
                System.out.println(response);
                try {
                    JSONObject json = new JSONObject(response);

                    if (handler != null)
                        handler.onResponse(json);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onCancelled() {

            }

        });
    }


    public static void login(final Context context, final String loginId, final String loginPw, final JsonResponseHandler handler) {
//        기능에 따라 매번 주소를 다르게 적어줘야함.
        String url = BASE_URL + "cgb/login_user";

//        기능을 사용하기 위해 필요한 데이터를 담는 부분.

        Map<String, String> data = new HashMap<String, String>();
        data.put("login_id", loginId);
        data.put("login_pw", loginPw);


        AsyncHttpRequest.get(context, url, data, true, new AsyncHttpRequest.HttpResponseHandler() {

            @Override
            public boolean onPrepare() {
                return true;
            }

            @Override
            public void onResponse(String response) {
                System.out.println(response);
                try {
                    JSONObject json = new JSONObject(response);

                    if (handler != null)
                        handler.onResponse(json);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onCancelled() {

            }

        });
    }


    public static void signup(final Context context, final String loginId, final String loginPw, final String username, final int gender, final String phonenum, final String profileurl, final String myinfo, final JsonResponseHandler handler) {
//        기능에 따라 매번 주소를 다르게 적어줘야함.
        String url = BASE_URL + "cgb/get_signup_user";

//        기능을 사용하기 위해 필요한 데이터를 담는 부분.

        Map<String, String> data = new HashMap<String, String>();
        data.put("login_id", loginId);
        data.put("login_pw", loginPw);
        data.put("user_name", username);
        data.put("user_gender", gender + "");
        data.put("user_phonenum", phonenum);
        data.put("user_profileimg", null);
        data.put("user_myinfo", myinfo);
//        data.put("user_isteacher", isteacher + "");


        AsyncHttpRequest.get(context, url, data, true, new AsyncHttpRequest.HttpResponseHandler() {

            @Override
            public boolean onPrepare() {
                return true;
            }

            @Override
            public void onResponse(String response) {
                System.out.println(response);
                try {
                    JSONObject json = new JSONObject(response);

                    if (handler != null)
                        handler.onResponse(json);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onCancelled() {

            }

        });
    }


    public static void FacebookAccessToken(final Context context, final JsonResponseHandler handler) {
//        기능에 따라 매번 주소를 다르게 적어줘야함.
        String url = "https://graph.facebook.com/oauth/access_token?client_id=1648925831797110&client_secret=af55a6b5cb9fb9d5bfe354c9f0489957&grant_type=client_credentials";

//        기능을 사용하기 위해 필요한 데이터를 담는 부분.

//        Map<String, String> data = new HashMap<String, String>();
////        data.put("version", "1");
////        data.put("lat", "37.610465");
//        data.put("lon", "126.928954");

        AsyncHttpRequest.get(context, url, null, true, new AsyncHttpRequest.HttpResponseHandler() {

            @Override
            public boolean onPrepare() {
                return true;
            }

            @Override
            public void onResponse(String response) {
                System.out.println(response);
                try {
                    JSONObject json = new JSONObject(response);

                    if (handler != null)
                        handler.onResponse(json);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onCancelled() {

            }

        });
    }


    // 사용자 관련 함수 모음


//    1. 원하는 기능을 제공하는 API 주소 알아내자.
//    2. 해당 기능을 사용하기위해 우리가 제공해야하는 데이터도 알아내자.
//    3. 해당 주소/데이터를 기반으로 메쏘드 생성.

    public static void getCurrentWeatherFromServer(final Context context, double lat, double lon, final JsonResponseHandler handler) {
//        기능에 따라 매번 주소를 다르게 적어줘야함.
        String url = "http://apis.skplanetx.com/weather/current/minutely?version=1&lat=" + lat + "&lon=" + lon;

//        기능을 사용하기 위해 필요한 데이터를 담는 부분.

        Map<String, String> data = new HashMap<String, String>();
//        data.put("version", "1");
//        data.put("lat", "37.610465");
//        data.put("lon", "126.928954");

        AsyncHttpRequest.get(context, url, data, true, new AsyncHttpRequest.HttpResponseHandler() {

            @Override
            public boolean onPrepare() {
                return true;
            }

            @Override
            public void onResponse(String response) {
                System.out.println(response);
                try {
                    JSONObject json = new JSONObject(response);

                    if (handler != null)
                        handler.onResponse(json);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onCancelled() {

            }

        });
    }

}
