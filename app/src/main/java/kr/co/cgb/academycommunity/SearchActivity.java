package kr.co.cgb.academycommunity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class SearchActivity extends BaseActivity {

    private android.widget.ImageView backLayoutBtn;
    private android.widget.EditText searchEdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {

        backLayoutBtn.setOnClickListener(new View.OnClickListener() {
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

        this.searchEdt = (EditText) findViewById(R.id.searchEdt);
        this.backLayoutBtn = (ImageView) findViewById(R.id.backLayoutBtn);

    }
}
