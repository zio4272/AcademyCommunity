package kr.co.cgb.academycommunity;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.Calendar;

import kr.co.cgb.academycommunity.data.User;
import kr.co.cgb.academycommunity.util.ContextUtil;
import kr.co.cgb.academycommunity.util.GlobalData;
import kr.co.cgb.academycommunity.util.ServerUtil;

public class PostWriteActivity extends BaseActivity {

    private android.widget.EditText postEdt;
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

                if (postEdt.getText().toString().equals("")) {
                    Toast.makeText(mContext, "내용을 입력 해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }
                    AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
                    alert.setTitle("등록하시겠습니까?");
                    alert.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    });
                    alert.setPositiveButton("등록", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            User loginUser = ContextUtil.getLoginUserInfo(mContext);
                            ServerUtil.write_post(mContext, postEdt.getText().toString(), loginUser.getId(), new ServerUtil.JsonResponseHandler() {
                                @Override
                                public void onResponse(JSONObject json) {


                                    Log.d("게시물이 등록되었습니다.", json.toString());


                                }
                            });
                            finish();
                        }
                    });
                    alert.show();



            }
        });

    }

    @Override
    public void setValues() {

    }

    @Override
    public void bindViews() {

        this.sendBtn = (Button) findViewById(R.id.sendBtn);
        this.postEdt = (EditText) findViewById(R.id.postEdt);

    }
}
