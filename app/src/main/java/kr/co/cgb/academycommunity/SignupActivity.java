package kr.co.cgb.academycommunity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SignupActivity extends BaseActivity {

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

    }
}
