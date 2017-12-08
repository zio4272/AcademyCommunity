package kr.co.cgb.academycommunity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.Permission;
import java.util.ArrayList;

import kr.co.cgb.academycommunity.data.User;
import kr.co.cgb.academycommunity.util.ContextUtil;
import kr.co.cgb.academycommunity.util.ServerUtil;

public class MainActivity extends BaseActivity {

    private android.widget.Button signupBtn;
    private Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();
        setupEvents();
        setValues();
//        FirebaseMessaging.getInstance().subscribeToTopic("news");
        FirebaseInstanceId.getInstance().getToken();


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


    }


    @Override
    public void bindViews() {
        this.loginBtn = (Button) findViewById(R.id.loginBtn);
        this.signupBtn = (Button) findViewById(R.id.signupBtn);

    }
}
