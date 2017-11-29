package kr.co.cgb.academycommunity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

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
                                    if (loginIdStr.equals(json.getJSONObject("result").getString("loginId")) && loginPwStr.equals(json.getJSONObject("result").getString("loginPw"))){
                                        Toast.makeText(mContext, "로그인 가능한 아이디 입니다.", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(mContext, IndexActivity.class);
                                        startActivity(intent);
                                    }
                                    else if (json.getString("result").equals("로그인실패")){
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

    }

    @Override
    public void bindViews() {
        this.loginBtn = (Button) findViewById(R.id.loginBtn);
        this.signupBtn = (Button) findViewById(R.id.signupBtn);

    }
}
