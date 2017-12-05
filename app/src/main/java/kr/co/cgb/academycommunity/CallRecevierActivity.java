package kr.co.cgb.academycommunity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class CallRecevierActivity extends BaseActivity {

    public static CallRecevierActivity act = null;

    private android.widget.TextView nameTxt;
    private android.widget.TextView phoneNumTxt;
    String receivePhoneNum;
    String receiveName;
    String lectureName;
    private android.widget.ImageView profileImg;
    private TextView lectureTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_recevier);
        receivePhoneNum = (String) getIntent().getSerializableExtra("phonenum");
        receiveName = (String) getIntent().getSerializableExtra("name");
        lectureName = (String) getIntent().getSerializableExtra("lecture");
        act = this;
        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {

    }

    @Override
    public void setValues() {
        phoneNumTxt.setText(receivePhoneNum);
        nameTxt.setText(receiveName);
        lectureTxt.setText(lectureName);


    }

    @Override
    public void bindViews() {
        this.phoneNumTxt = (TextView) findViewById(R.id.phoneNumTxt);
        this.lectureTxt = (TextView) findViewById(R.id.lectureTxt);
        this.nameTxt = (TextView) findViewById(R.id.nameTxt);
        this.profileImg = (ImageView) findViewById(R.id.profileImg);

    }
}
