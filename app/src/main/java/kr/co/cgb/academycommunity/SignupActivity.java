package kr.co.cgb.academycommunity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import kr.co.cgb.academycommunity.util.ServerUtil;

public class SignupActivity extends BaseActivity {

    final int REQ_FOR_CAMERA = 1;
    final int REQ_FOR_GALLERY = 2;

    boolean isIdOk = false;
    int lectureNum = 0;
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
    private android.widget.ImageView userProfileImg;
    private TextView profileImgUploadTxt;
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        bindViews();
        setupEvents();
        setValues();

        token = FirebaseInstanceId.getInstance().getToken();

        Log.d("token값", token);
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


        idCheckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String inputId = idEdt.getText().toString();

                ServerUtil.idCheck(mContext, inputId, new ServerUtil.JsonResponseHandler() {
                    @Override
                    public void onResponse(JSONObject json) {
                        try {
                            if (json.getString("result").equals("중복입니다")) {
                                Toast.makeText(mContext, "이미 존재하는 아이디 입니다.", Toast.LENGTH_SHORT).show();
                            } else if (json.getString("result").equals("사용가능합니다.")) {
                                Toast.makeText(mContext, "사용 가능한 아이디 입니다.", Toast.LENGTH_SHORT).show();
                                isIdOk = true;

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });


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
                } else if (womanRadioBtn.isChecked()) {
                    gender = 1;
                }

                if (!manRadioBtn.isChecked() && !womanRadioBtn.isChecked()) {
                    Toast.makeText(mContext, "성별을 선택해주세요.", Toast.LENGTH_SHORT).show();
                } else if (inputPw.length() >= 1 && inputPw.equals(inputCheckPw) && isIdOk && lectureNum != 0) {

                    ServerUtil.signup(mContext, idEdt.getText().toString(), pwEdt.getText().toString(), nameEdt.getText().toString(), gender, phoneEdt.getText().toString(), null, MyInfoEdt.getText().toString(), lectureNum, token, new ServerUtil.JsonResponseHandler() {
                        @Override
                        public void onResponse(JSONObject json) {

                        }
                    });

                    Intent intent = new Intent(mContext, IndexActivity.class);
                    startActivity(intent);
                    finish();
                    Toast.makeText(mContext, "회원가입 성공", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(mContext, "회원가입에 실패했습니다.\n 누락된 정보가 있는지 확인 해주세요", Toast.LENGTH_SHORT).show();
                }

            }
        });

        lectureListSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(mContext, "클릭된 줄 :" + i, Toast.LENGTH_SHORT).show();
                lectureNum = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        profileImgUploadTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String[] items = {"사진찍기", "앨범에서 선택", "사진 삭제", "취소"};

                AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
                alert.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        if (i == 0) { // 카메라
                            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(takePictureIntent, REQ_FOR_CAMERA);
                        } else if (i == 1) { // 앨범
                            Intent intent = new Intent();
                            intent.setType("image/*");
                            intent.setAction(Intent.ACTION_GET_CONTENT);
                            startActivityForResult(intent, REQ_FOR_GALLERY);
                        }

                    }
                });

                alert.show();

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ_FOR_CAMERA) {
            if (resultCode == RESULT_OK) {
//                찍힌 사진을 가지고, 프로필 사진으로 설정

//                결과물 intent가 가진 모든 데이터를 받아오자 : getExtras => Bundle 변수.
                Bundle extra = data.getExtras();
//                extra 내부에는 사진으로 촬영한 그림파일 자체. Bitmap
                Bitmap profileBitmap = (Bitmap) extra.get("data");
                userProfileImg.setImageBitmap(profileBitmap);

            }
        } else if (requestCode == REQ_FOR_GALLERY) {
            if (resultCode == RESULT_OK) {
//                갤러리에서 선택된 사진을 프사로 설정.

                Uri uri = data.getData();
//                갤러리를 통해 받아온것? 선택된 사진이 어디에 있는지 위치 정보.

//                경로를 찾아가서 해당 사진 파일을 Bitmap으로 받아와야함.
//                MediaStore 클래스가 사진 파일 => 비트맵으로 변환해서 가져옴.

//                try : 한번 시도해봐. try 내부는 언제 에러가 터질지 모르는 부분. (예외 발생 가능 지점)
                //                uri 통해서 사진파일로 찾아감.
//                사진파일 있으면, 비트맵으로 변환. (변환을 해주는 객체 : getContentResolver())
//                그냥 이 문장만 쓰면 에러가 남. 왜? 예외처리 필요.
                Bitmap myBitmap = null;
                try {
                    myBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    userProfileImg.setImageBitmap(myBitmap);

                } catch (IOException e) {
                    Toast.makeText(mContext, "사진을 불러오는 중에 에러가 발생했습니다.", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }


            }
        }

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
        this.profileImgUploadTxt = (TextView) findViewById(R.id.profileImgUploadTxt);
        this.userProfileImg = (ImageView) findViewById(R.id.userProfileImg);
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
