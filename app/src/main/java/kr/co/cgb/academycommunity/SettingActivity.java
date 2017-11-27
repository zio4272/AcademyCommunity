package kr.co.cgb.academycommunity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;

public class SettingActivity extends BaseActivity {

    private android.widget.ImageView backBtn;
    private android.widget.Switch switch2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @Override
    public void setValues() {

    }

    @Override
    public void bindViews() {
        this.switch2 = (Switch) findViewById(R.id.switch2);
        this.backBtn = (ImageView) findViewById(R.id.backBtn);

    }
}
