package kr.co.cgb.academycommunity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import kr.co.cgb.academycommunity.adapter.ReplyAdapter;
import kr.co.cgb.academycommunity.data.Post;
import kr.co.cgb.academycommunity.data.Reply;
import kr.co.cgb.academycommunity.data.User;
import kr.co.cgb.academycommunity.util.ContextUtil;
import kr.co.cgb.academycommunity.util.ServerUtil;
import kr.co.cgb.academycommunity.util.TimeAgoUtil;

public class PostPopupActivity extends BaseActivity {

    ReplyAdapter mAdapter;
    List<Reply> replyList = new ArrayList<>();
    public int selectedReply = -1;
    public int selectSubReply;

    int parentId = 1;


    private android.widget.ImageView profileImg;
    private android.widget.TextView lectureNameTxt;
    private android.widget.TextView writerNameTxt;
    private android.widget.TextView writeTimeTxt;
    private android.widget.TextView contentTxt;
    private android.widget.ListView replyListView;
    private TextView listenLectureTxt;
    private android.widget.EditText replyEdt;
    private android.widget.Button sendBtn;
    Post post;
    public User tagUser = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_popup);
        post = (Post) getIntent().getSerializableExtra("postdata");
        bindViews();
//        setValues();
        setupEvents();
//        getReplyFromJson();

    }

    @Override
    public void setupEvents() {

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                User loginUser = ContextUtil.getLoginUserInfo(mContext);
                Log.d("컹컹", loginUser.getId() + "번");

                if (replyEdt.getText().toString().equals("")) {
                    Toast.makeText(mContext, "댓글내용을 입력하세요.", Toast.LENGTH_SHORT).show();
                    return;
                }
                ServerUtil.write_reply(mContext, parentId, loginUser.getUserName(), replyEdt.getText().toString(), post.getId(), loginUser.getId(), new ServerUtil.JsonResponseHandler() {
                    @Override
                    public void onResponse(JSONObject json) {

                        Log.d("테스트", json.toString());

                        getReplyFromJson();
                        replyEdt.setText("");
//                            replyListView.smoothScrollToPosition(mAdapter.getCount() - 1);

                    }
                });

            }
        });

        replyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                parentId = replyList.get(i).getReplyId();

            }
        });


//        sendBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String input = replyEdt.getText().toString();
//                Log.d("확인", input);
//
//                int nextId = replyList.size() + 1;
//
//                if (selectedReply != -1) {
//
//                    for (Reply reply : post.replyList) {
//                        if (reply.getReplyId() == selectedReply) {
//                            reply.replies.add(new Reply(nextId, selectedReply, GlobalData.loginUserData, input, Calendar.getInstance(), post, tagUser));
//
//                        }
//
//                    }
//
//                } else {
//                    post.replyList.add(new Reply(nextId, selectedReply, GlobalData.loginUserData, input, Calendar.getInstance(), post, tagUser));
//                }
//
//                replyData();
//
//
//                selectedReply = -1;
//                mAdapter.notifyDataSetChanged();
//
//                int index = 0;
//                for (Reply reply : replyList) {
//                    if (reply.getReplyId() == nextId) {
//                        break;
//                    } else {
//                        index++;
//                    }
//
//                }
//                if (index > 0) {
//                    replyListView.setSelection(index);
//                }
//
//
//                replyEdt.setText("");
//            }
//        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        post = (Post) getIntent().getSerializableExtra("postdata");
        setValues();
    }

    void getReplyFromJson() {
        ServerUtil.get_reply(mContext, post.getId(), new ServerUtil.JsonResponseHandler() {
            @Override
            public void onResponse(JSONObject json) {
                try {
                    replyList.clear();
                    JSONArray jsonArray = json.getJSONArray("result");

                    for (int i = 0; i < jsonArray.length(); i++) {

                        replyList.add(Reply.getReplyFromJson(jsonArray.getJSONObject(i)));
                    }
                    mAdapter = new ReplyAdapter(mContext, replyList);
                    replyListView.setAdapter(mAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mAdapter.notifyDataSetChanged();
            }
        });

    }


    void replyData() {
        replyList.clear();
        for (Reply mainReply : post.replyList) {
            replyList.add(mainReply);

            for (Reply subReply : mainReply.replies) {
                replyList.add(subReply);

            }
        }


    }

    @Override
    public void setValues() {
        getReplyFromJson();

//        replyData();

        String profileStr = post.getUserWriterData().getUserProfileImg();
        if (profileStr.equals("noImage")) {
            profileImg.setImageResource(R.drawable.noimage);
        } else {
            Glide.with(mContext).load(post.getUserWriterData().getUserProfileImg()).into(profileImg);
        }


        listenLectureTxt.setText(post.userWriterData.listenLecture.getLectureName());
        writerNameTxt.setText(post.getUserWriterData().getUserName());
        contentTxt.setText(post.getPostContent());

        Calendar now = Calendar.getInstance();
        long time = now.getTimeInMillis() - post.getPostDate().getTimeInMillis();
        int minute = (int) (time / 1000 / 60);
        String minuteAgo = TimeAgoUtil.getTimeAgoString(minute);
        writeTimeTxt.setText(minuteAgo);


    }

    @Override
    public void bindViews() {
        this.replyEdt = (EditText) findViewById(R.id.replyEdt);
        this.sendBtn = (Button) findViewById(R.id.sendBtn);
        this.replyListView = (ListView) findViewById(R.id.replyListView);
        this.contentTxt = (TextView) findViewById(R.id.contentTxt);
        this.writeTimeTxt = (TextView) findViewById(R.id.writeTimeTxt);
        this.writerNameTxt = (TextView) findViewById(R.id.writerNameTxt);
        this.listenLectureTxt = (TextView) findViewById(R.id.listenLectureTxt);
        this.profileImg = (CircleImageView) findViewById(R.id.profileImg);
    }
}
