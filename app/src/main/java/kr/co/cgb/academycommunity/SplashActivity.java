package kr.co.cgb.academycommunity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;

import kr.co.cgb.academycommunity.util.ServerUtil;

public class SplashActivity extends BaseActivity {

    private android.widget.TextView currentTempTxt;
    private android.widget.TextView skyTxt;
    private android.widget.ImageView skyImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        bindViews();
        setupEvents();
        setValues();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(mContext, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);

    }

    @Override
    public void setupEvents() {

    }

    @Override
    public void setValues() {

    }

    @Override
    public void bindViews() {
        this.skyImage = (ImageView) findViewById(R.id.skyImage);
        this.skyTxt = (TextView) findViewById(R.id.skyTxt);
        this.currentTempTxt = (TextView) findViewById(R.id.currentTempTxt);

    }
}
