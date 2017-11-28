package kr.co.cgb.academycommunity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import kr.co.cgb.academycommunity.adapter.ReplyAdapter;
import kr.co.cgb.academycommunity.data.Post;
import kr.co.cgb.academycommunity.data.Reply;
import kr.co.cgb.academycommunity.data.User;
import kr.co.cgb.academycommunity.util.GlobalData;
import kr.co.cgb.academycommunity.util.TimeAgoUtil;

public class PostPopupActivity extends BaseActivity {

    ReplyAdapter mAdapter;
    List<Reply> replyList = new ArrayList<>();
    public int selectedReply = -1;
    public int selectSubReply;


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
    public User tagUser = null;

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


        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = replyEdt.getText().toString();
                Log.d("확인", input);

                int nextId = replyList.size() + 1;

                if (selectedReply != -1) {

                    for (Reply reply : position.replyList) {
                        if (reply.getReplyId() == selectedReply) {
                            reply.replies.add(new Reply(nextId, selectedReply, GlobalData.loginUserData, input, Calendar.getInstance(), position, tagUser));

                        }

                    }

                } else {
                    position.replyList.add(new Reply(nextId, selectedReply, GlobalData.loginUserData, input, Calendar.getInstance(), position, tagUser));
                }

                replyData();


                selectedReply = -1;
                mAdapter.notifyDataSetChanged();

                int index = 0;
                for (Reply reply : replyList) {
                    if (reply.getReplyId() == nextId) {
                        break;
                    } else {
                        index++;
                    }

                }
                if (index > 0) {
                    replyListView.setSelection(index);
                }


                replyEdt.setText("");
            }
        });

    }

    void replyData() {
        replyList.clear();
        for (Reply mainReply : position.replyList) {
            replyList.add(mainReply);

            for (Reply subReply : mainReply.replies) {
                replyList.add(subReply);

            }
        }


    }

    @Override
    public void setValues() {

        replyData();
        mAdapter = new ReplyAdapter(mContext, replyList);
        replyListView.setAdapter(mAdapter);

//       TODO - 이미지가 없을 경우 NO IMAGE 출력
        Glide.with(mContext).load(position.getUserWriterData().getUserProfileUrl()).into(profileImg);
//        listenLectureTxt.setText(position.getUserWriterData().); TODO - 서버 연동 후 작업
        writerNameTxt.setText(position.getUserWriterData().getUserName());
        contentTxt.setText(position.getPostContent());
        String minuteAge = TimeAgoUtil.getTimeAgoString(position.getCreatedAt());
        writeTimeTxt.setText(minuteAge);


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
