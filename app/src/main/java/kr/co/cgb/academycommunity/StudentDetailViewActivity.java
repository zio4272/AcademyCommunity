package kr.co.cgb.academycommunity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;
import kr.co.cgb.academycommunity.data.User;

public class StudentDetailViewActivity extends BaseActivity {

    User user;


    private de.hdodenhof.circleimageview.CircleImageView profileimage;
    private android.widget.TextView nameTxt;
    private android.widget.TextView birthDayTxt;
    private android.widget.TextView addressTxt;
    private android.widget.TextView listenLectureTxt;
    private android.widget.TextView myInfoTxt;
    private android.widget.TextView phoneNumTxt;
    private android.widget.ImageView callNowImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail_view);
        user = (User) getIntent().getSerializableExtra("userdata");
        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {
        callNowImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + user.getUserPhoneNum()));
                if (ActivityCompat.checkSelfPermission(mContext, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(intent);

            }
        });
    }

    @Override
    public void setValues() {

        nameTxt.setText(user.getUserName());
        myInfoTxt.setText(user.getUserMyInfo());

        String profileStr = user.getUserProfileImg();
        if (profileStr.equals("noImage")) {
            profileimage.setImageResource(R.drawable.noimage);
        } else {
            Glide.with(mContext).load(user.getUserProfileImg()).into(profileimage);
            phoneNumTxt.setText(user.getUserPhoneNum());
        }

    }

    @Override
    public void bindViews() {
        this.callNowImg = (ImageView) findViewById(R.id.callNowImg);
        this.phoneNumTxt = (TextView) findViewById(R.id.phoneNumTxt);
        this.myInfoTxt = (TextView) findViewById(R.id.myInfoTxt);
        this.listenLectureTxt = (TextView) findViewById(R.id.listenLectureTxt);
        this.addressTxt = (TextView) findViewById(R.id.addressTxt);
        this.birthDayTxt = (TextView) findViewById(R.id.birthDayTxt);
        this.nameTxt = (TextView) findViewById(R.id.nameTxt);
        this.profileimage = (CircleImageView) findViewById(R.id.profile_image);

    }
}
