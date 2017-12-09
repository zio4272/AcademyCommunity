package kr.co.cgb.academycommunity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;

import kr.co.cgb.academycommunity.data.User;
import kr.co.cgb.academycommunity.util.ContextUtil;
import kr.co.cgb.academycommunity.util.ServerUtil;

public class MainActivity extends BaseActivity {

    MainActivity act;

    private android.widget.Button signupBtn;
    private Button loginBtn;
    private android.widget.TextView currentTempTxt;
    private android.widget.TextView skyTxt;
    private android.widget.ImageView skyImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();
        setupEvents();
        setValues();

        String token = FirebaseInstanceId.getInstance().getToken();

//        Log.d("token값", token);
    }


    @Override
    public void setupEvents() {

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, SignupActivity.class);
                startActivity(intent);
                finish();
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder loginPopup = new AlertDialog.Builder(mContext);
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
                View layout = inflater.inflate(R.layout.popup_login_item, null);
                final EditText idEdt = (EditText) layout.findViewById(R.id.idEdt);
                final EditText pwEdt = (EditText) layout.findViewById(R.id.pwEdt);

                loginPopup.setTitle("로그인하시겠습니까?");
                loginPopup.setView(layout);

                loginPopup.setPositiveButton("로그인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        final String loginIdStr = idEdt.getText().toString();
                        final String loginPwStr = pwEdt.getText().toString();
                        ServerUtil.login(mContext, loginIdStr, loginPwStr, new ServerUtil.JsonResponseHandler() {
                            @Override
                            public void onResponse(JSONObject json) {

                                try {
                                    if (json.getBoolean("result")) {
                                        if (loginIdStr.equals(json.getJSONObject("userlist").getString("loginId")) && loginPwStr.equals(json.getJSONObject("userlist").getString("loginPw"))) {
                                            Toast.makeText(mContext, "로그인 되었습니다.", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(mContext, IndexActivity.class);
                                            JSONObject user = json.getJSONObject("userlist");
                                            ContextUtil.login(mContext, User.getUserFromJson(user));
                                            startActivity(intent);
                                            finish();
                                        }
                                    }
//                                    TODO - 로그인 실패시 토스트가 안뜸, 확인 해야함.
                                    else {
                                        Toast.makeText(mContext, "아이디 또는 비밀번호를 확인 해주세요.", Toast.LENGTH_SHORT).show();
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        });


                    }
                });

                loginPopup.setNegativeButton("취소", null);
                loginPopup.show();


            }
        });

    }

    @Override
    public void setValues() {

        act = this;


        TedPermission.with(mContext)
                .setPermissionListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted() {

                    }

                    @Override
                    public void onPermissionDenied(ArrayList<String> deniedPermissions) {

                    }
                })
                .setDeniedMessage("권한 설정 해야함..흐흐흐")
                .setPermissions(Manifest.permission_group.PHONE)
                .check();

        // 학원 위도 경도 37.570509, 126.989100
        ServerUtil.getCurrentWeatherFromServer(mContext, 37.570509, 126.989100, new ServerUtil.JsonResponseHandler() {
            @Override
            public void onResponse(JSONObject json) {

                Log.d("실시간날씨JSON", json.toString());

                try {
                    skyTxt.setText(json.getJSONObject("weather").getJSONArray("minutely").getJSONObject(0).getJSONObject("sky").getString("name"));
                    currentTempTxt.setText(String.format(Locale.KOREA, "%.1f ˚", Double.parseDouble(json.getJSONObject("weather").getJSONArray("minutely").getJSONObject(0).getJSONObject("temperature").getString("tc"))));

                    String weatherState = json.getJSONObject("weather").getJSONArray("minutely").getJSONObject(0).getJSONObject("sky").getString("name");
                    switch (weatherState) {
                        case "상태없음":
                            skyImage.setImageResource(R.drawable.sky_a00);
                            break;
                        case "맑음":
                            skyImage.setImageResource(R.drawable.sky_a01);
                            break;
                        case "구름조금":
                            skyImage.setImageResource(R.drawable.sky_a02);
                            break;
                        case "구름많음":
                            skyImage.setImageResource(R.drawable.sky_a03);
                            break;
                        case "구름많고 비":
                            skyImage.setImageResource(R.drawable.sky_a04);
                            break;
                        case "구름많고 눈":
                            skyImage.setImageResource(R.drawable.sky_a05);
                            break;
                        case "구름많고 비 또는 눈":
                            skyImage.setImageResource(R.drawable.sky_a06);
                            break;
                        case "흐림":
                            skyImage.setImageResource(R.drawable.sky_a07);
                            break;
                        case "흐리고 비":
                            skyImage.setImageResource(R.drawable.sky_a08);
                            break;
                        case "흐리고 눈":
                            skyImage.setImageResource(R.drawable.sky_a09);
                            break;
                        case "흐리고 비 또는 눈":
                            skyImage.setImageResource(R.drawable.sky_a10);
                            break;
                        case "흐리고낙뢰":
                            skyImage.setImageResource(R.drawable.sky_a11);
                            break;
                        case "뇌우, 비":
                            skyImage.setImageResource(R.drawable.sky_a12);
                            break;
                        case "뇌우, 눈":
                            skyImage.setImageResource(R.drawable.sky_a13);
                            break;
                        case "뇌우, 비 또는 눈":
                            skyImage.setImageResource(R.drawable.sky_a14);
                            break;

                    }

//                            하늘상태코드명
//                            -SKY_A00:상태없음
//                            -sky_a01:맑음
//                            -SKY_A02:구름조금
//                            -SKY_A03:구름많음
//                            -SKY_A04:구름많고 비
//                            -SKY_A05:구름많고 눈
//                            -SKY_A06:구름많고 비 또는 눈
//                            -SKY_A07:흐림
//                            -SKY_A08:흐리고 비
//                            -SKY_A09:흐리고 눈
//                            -SKY_A10:흐리고 비 또는 눈
//                            -SKY_A11:흐리고 낙뢰
//                            -SKY_A12:뇌우, 비
//                            -SKY_A13:뇌우, 눈
//                            -SKY_A14:뇌우, 비 또는 눈

//                    if (json.getJSONObject("weather").getJSONArray("minutely").getJSONObject(0).getJSONObject("sky").getString("name").equals("흐림")) {
//                        skyImage.setImageResource(R.drawable.above_shadow);
//                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });


    }


    @Override
    public void bindViews() {
        this.loginBtn = (Button) findViewById(R.id.loginBtn);
        this.signupBtn = (Button) findViewById(R.id.signupBtn);
        this.skyImage = (ImageView) findViewById(R.id.skyImage);
        this.skyTxt = (TextView) findViewById(R.id.skyTxt);
        this.currentTempTxt = (TextView) findViewById(R.id.currentTempTxt);

    }
}
