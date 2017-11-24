package kr.co.cgb.academycommunity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.DialogTitle;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import kr.co.cgb.academycommunity.adapter.ReplyAdapter;
import kr.co.cgb.academycommunity.data.Reply;

public class PostPopupActivity extends BaseActivity {

    ReplyAdapter mAdapter;
    List<Reply> replyList = new ArrayList<>();
    private android.widget.ImageView profileImg;
    private android.widget.TextView lectureNameTxt;
    private android.widget.TextView writerNameTxt;
    private android.widget.TextView writeTimeTxt;
    private android.widget.TextView contentTxt;
    private android.widget.ListView replyListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_post_popup);
        bindViews();
        setupEvents();
        setValues();



    }

    @Override
    public void setupEvents() {


    }

    @Override
    public void setValues() {
        mAdapter = new ReplyAdapter(mContext, replyList);
        replyListView.setAdapter(mAdapter);

    }

    @Override
    public void bindViews() {
        this.replyListView = (ListView) findViewById(R.id.replyListView);
        this.contentTxt = (TextView) findViewById(R.id.contentTxt);
        this.writeTimeTxt = (TextView) findViewById(R.id.writeTimeTxt);
        this.writerNameTxt = (TextView) findViewById(R.id.writerNameTxt);
        this.lectureNameTxt = (TextView) findViewById(R.id.lectureNameTxt);
        this.profileImg = (ImageView) findViewById(R.id.profileImg);

    }
}
