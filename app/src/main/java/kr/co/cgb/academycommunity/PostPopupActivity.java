package kr.co.cgb.academycommunity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import kr.co.cgb.academycommunity.adapter.PostAdapter;
import kr.co.cgb.academycommunity.adapter.ReplyAdapter;
import kr.co.cgb.academycommunity.data.Post;
import kr.co.cgb.academycommunity.data.Reply;
import kr.co.cgb.academycommunity.data.User;
import kr.co.cgb.academycommunity.util.GlobalData;

public class PostPopupActivity extends BaseActivity {

    ReplyAdapter mAdapter;
    List<Reply> replyList = new ArrayList<>();
    List<Post> postList = new ArrayList<>();
    private android.widget.ImageView profileImg;
    private android.widget.TextView lectureNameTxt;
    private android.widget.TextView writerNameTxt;
    private android.widget.TextView writeTimeTxt;
    private android.widget.TextView contentTxt;
    private android.widget.ListView replyListView;
    private TextView listenLectureTxt;
    private android.widget.EditText replyEdt;
    private android.widget.Button sendBtn;
    Post position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_popup);
        position = (Post) getIntent().getSerializableExtra("postdata");
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

        listenLectureTxt.setText(position.getUserWriterData().);
        writerNameTxt.setText(position.getUserWriterData().getUserName());
        contentTxt.setText(position.getPostContent());


    }

    @Override
    public void bindViews() {
        this.sendBtn = (Button) findViewById(R.id.sendBtn);
        this.replyEdt = (EditText) findViewById(R.id.replyEdt);
        this.replyListView = (ListView) findViewById(R.id.replyListView);
        this.contentTxt = (TextView) findViewById(R.id.contentTxt);
        this.writeTimeTxt = (TextView) findViewById(R.id.writeTimeTxt);
        this.writerNameTxt = (TextView) findViewById(R.id.writerNameTxt);
        this.listenLectureTxt = (TextView) findViewById(R.id.listenLectureTxt);
        this.profileImg = (CircleImageView) findViewById(R.id.profileImg);

    }
}
