package kr.co.cgb.academycommunity;

import android.os.Bundle;
import android.widget.TextView;

import kr.co.cgb.academycommunity.data.User;

public class CallRecevierActivity extends BaseActivity {

    public static CallRecevierActivity act = null;

    private android.widget.TextView nameTxt;
    private android.widget.TextView phoneNumTxt;
    String receivePhoneNum;
    String receiveName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_recevier);
        receivePhoneNum = (String) getIntent().getSerializableExtra("phonenum");
        receiveName = (String) getIntent().getSerializableExtra("name");
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

    }

    @Override
    public void bindViews() {
        this.phoneNumTxt = (TextView) findViewById(R.id.phoneNumTxt);
        this.nameTxt = (TextView) findViewById(R.id.nameTxt);

    }
}
