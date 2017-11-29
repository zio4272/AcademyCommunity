package kr.co.cgb.academycommunity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import kr.co.cgb.academycommunity.util.ServerUtil;

public class SignupActivity extends BaseActivity {

    boolean isIdOk = false;

    private android.widget.EditText idEdt;
    private android.widget.TextView idCheckBtn;
    private android.widget.EditText pwEdt;
    private android.widget.EditText pwReEdt;
    private android.widget.EditText nameEdt;
    private android.widget.EditText phoneEdt;
    private android.widget.RadioButton manRadioBtn;
    private android.widget.RadioButton womanRadioBtn;
    private android.widget.Spinner lectureListSpinner;
    private android.widget.EditText MyInfoEdt;
    private android.widget.Button okBtn;
    private TextView pwMessageTxt;
    private android.widget.RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        bindViews();
        setupEvents();
        setValues();

        setTitle("회원가입");
    }

    @Override
    public void setupEvents() {

        idEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String changeStr = charSequence.toString();
                Log.d("변경된글자", changeStr);
                isIdOk = false;

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        pwEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                passwordCheck();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        pwReEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                passwordCheck();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        nameEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String changeStr = charSequence.toString();
                Log.d("변경된글자", changeStr);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        phoneEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String changeStr = charSequence.toString();
                Log.d("변경된글자", changeStr);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

//        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup radioGroup, int i) {
//
//            }
//        });


        idCheckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String inputId = idEdt.getText().toString();

                ServerUtil.idCheck(mContext, inputId, new ServerUtil.JsonResponseHandler() {
                    @Override
                    public void onResponse(JSONObject json) {
                        try {
                            if (json.getString("result").equals("중복입니다") ){
                                Toast.makeText(mContext, "이미 존재하는 아이디 입니다.", Toast.LENGTH_SHORT).show();
                            }
                            else if (json.getString("result").equals("사용가능합니다.")){
                                Toast.makeText(mContext, "사용 가능한 아이디 입니다.", Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });

//                String inputId = idEdt.getText().toString();
//                if (inputId.equals("user")) { // 서버연동해야함
//                    Toast.makeText(mContext, "이미 사용중인 아이디입니다.", Toast.LENGTH_SHORT).show();
//                } else if (inputId.length() == 0) {
//                    Toast.makeText(mContext, "아이디를 입력해주세요.", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(mContext, "사용해도 좋습니다.", Toast.LENGTH_SHORT).show();
//                    isIdOk = true;
//                }
            }
        });

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputPw = pwEdt.getText().toString();
                String inputCheckPw = pwReEdt.getText().toString();

                int gender = 0;
                if (manRadioBtn.isChecked()) {
                    gender = 0;
                    Toast.makeText(mContext, "남자선택" + gender, Toast.LENGTH_SHORT).show();
                } else if (womanRadioBtn.isChecked()) {
                    gender = 1;
                    Toast.makeText(mContext, "여자선택" + gender, Toast.LENGTH_SHORT).show();
                }

                if (!manRadioBtn.isChecked() && !womanRadioBtn.isChecked()) {
                    Toast.makeText(mContext, "성별을 선택해주세요.", Toast.LENGTH_SHORT).show();
                } else if (inputPw.length() >= 4 && inputPw.equals(inputCheckPw) && isIdOk) {

                    ServerUtil.signup(mContext, idEdt.getText().toString(), pwEdt.getText().toString(), nameEdt.getText().toString(), gender, phoneEdt.getText().toString(), null, MyInfoEdt.getText().toString(), new ServerUtil.JsonResponseHandler() {
                        @Override
                        public void onResponse(JSONObject json) {
                            json.toString();

                        }
                    });

                    Intent intent = new Intent(mContext, IndexActivity.class);
                    startActivity(intent);
                    Toast.makeText(mContext, "회원가입 성공", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(mContext, "회원가입에 실패했습니다.", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    void passwordCheck() {
        String pw = pwEdt.getText().toString();
        String pwCheck = pwReEdt.getText().toString();

        if (pw.length() == 0) {
            pwMessageTxt.setVisibility(View.VISIBLE);
            pwMessageTxt.setText("비밀번호를 입력해주세요.");
        } else if (!pw.equals(pwCheck)) {
            pwMessageTxt.setVisibility(View.VISIBLE);
            pwMessageTxt.setText("비밀번호가 일치하지 않습니다.");
        } else {
            pwMessageTxt.setVisibility(View.VISIBLE);
            pwMessageTxt.setText("회원가입이 가능합니다.");
        }
    }

    @Override
    public void setValues() {

    }

    @Override
    public void bindViews() {
        this.okBtn = (Button) findViewById(R.id.okBtn);
        this.MyInfoEdt = (EditText) findViewById(R.id.MyInfoEdt);
        this.lectureListSpinner = (Spinner) findViewById(R.id.lectureListSpinner);
        this.radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        this.womanRadioBtn = (RadioButton) findViewById(R.id.womanRadioBtn);
        this.manRadioBtn = (RadioButton) findViewById(R.id.manRadioBtn);
        this.phoneEdt = (EditText) findViewById(R.id.phoneEdt);
        this.nameEdt = (EditText) findViewById(R.id.nameEdt);
        this.pwMessageTxt = (TextView) findViewById(R.id.pwMessageTxt);
        this.pwReEdt = (EditText) findViewById(R.id.pwReEdt);
        this.pwEdt = (EditText) findViewById(R.id.pwEdt);
        this.idCheckBtn = (TextView) findViewById(R.id.idCheckBtn);
        this.idEdt = (EditText) findViewById(R.id.idEdt);
    }
}
