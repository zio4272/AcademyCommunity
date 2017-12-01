package kr.co.cgb.academycommunity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PostWriteActivity extends BaseActivity {

    private android.widget.EditText replyEdt;
    private android.widget.Button sendBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_write);
        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
//                TODO 글쓰기 전송 서버 연동
            }
        });

    }

    @Override
    public void setValues() {

    }

    @Override
    public void bindViews() {

        this.sendBtn = (Button) findViewById(R.id.sendBtn);
        this.replyEdt = (EditText) findViewById(R.id.replyEdt);

    }
}
