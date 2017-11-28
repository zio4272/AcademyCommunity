package kr.co.cgb.academycommunity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class SignupActivity extends BaseActivity {

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

    }

    @Override
    public void setValues() {

    }

    @Override
    public void bindViews() {
        this.okBtn = (Button) findViewById(R.id.okBtn);
        this.MyInfoEdt = (EditText) findViewById(R.id.MyInfoEdt);
        this.lectureListSpinner = (Spinner) findViewById(R.id.lectureListSpinner);
        this.womanRadioBtn = (RadioButton) findViewById(R.id.womanRadioBtn);
        this.manRadioBtn = (RadioButton) findViewById(R.id.manRadioBtn);
        this.phoneEdt = (EditText) findViewById(R.id.phoneEdt);
        this.nameEdt = (EditText) findViewById(R.id.nameEdt);
        this.pwReEdt = (EditText) findViewById(R.id.pwReEdt);
        this.pwEdt = (EditText) findViewById(R.id.pwEdt);
        this.idCheckBtn = (TextView) findViewById(R.id.idCheckBtn);
        this.idEdt = (EditText) findViewById(R.id.idEdt);

    }
}
