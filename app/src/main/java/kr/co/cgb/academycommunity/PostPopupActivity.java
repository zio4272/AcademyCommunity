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
import kr.co.cgb.academycommunity.util.GlobalData;
import kr.co.cgb.academycommunity.util.TimeAgoUtil;

public class PostPopupActivity extends BaseActivity {

    ReplyAdapter mAdapter;
    List<Reply> replyList = new ArrayList<>();

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

//        sendBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String inputString = replyEdt.getText().toString();
//
//                int parentId = Integer.parseInt(replyEdt.getText().toString());
//
//                int index = replyList.size();
//
//                if (parentId != -1) {
//
//
//                    for (int i = 0; i < replyList.size(); i++) {
//                        Reply data  = replyList.get(i);
//                        if (parentId == data.getReplyId()){
//                            index = i;
//                        }
//                        else if (parentId == data.getParentId()) {
//                            index = i;
//                        }
//                    }
//                }
//
//                if (parentId == -1){
////                    replyList.add(index, new Reply(replyList.size() + 1, parentId, GlobalData.loginUserData.getUserName(), inputString, Calendar.getInstance(),));
//                }
//
//            }
//        });
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = replyEdt.getText().toString();
                Log.d("확인", input);
//                int index = replyList.size();

//                int parentId = Integer.parseInt(replyEdt.getTag().toString());

                replyList.add(new Reply(replyList.size() + 1 , -1 , GlobalData.loginUserData, input, Calendar.getInstance(), position));

                mAdapter.notifyDataSetChanged();

                replyListView.setSelection(mAdapter.getCount() - 1);

                replyEdt.setText("");
            }
        });

    }

    @Override
    public void setValues() {

        for (Reply mainReply : position.replyList) {
            replyList.add(mainReply);

            for (Reply subReply : mainReply.replies) {
                replyList.add(subReply);

            }
        }

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
