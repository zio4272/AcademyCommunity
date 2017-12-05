package kr.co.cgb.academycommunity.util;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.zip.Inflater;

import kr.co.cgb.academycommunity.CallRecevierActivity;
import kr.co.cgb.academycommunity.PostPopupActivity;
import kr.co.cgb.academycommunity.R;
import kr.co.cgb.academycommunity.data.User;

/**
 * Created by PC on 2017-12-04.
 */

public class PhoneStateReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(final Context context, Intent intent) {

        try {
            System.out.println("Receiver start");
            String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
            String incomingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);




//            User user = (User) intent.getSerializableExtra("user");

            if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                Toast.makeText(context, "Incoming Call State", Toast.LENGTH_SHORT).show();
                Toast.makeText(context, "Ringing State Number is -" + incomingNumber, Toast.LENGTH_SHORT).show();
//                if (incomingNumber.equals(user.getUserPhoneNum())) {
//                    Toast.makeText(context, "전화한사람 이름" + user.getUserName(), Toast.LENGTH_SHORT).show();
//                }
//                else {
//                    Toast.makeText(context, "모르는 번호 입니다.", Toast.LENGTH_SHORT).show();
//                }
                ServerUtil.getUserByPhoneNum(context, incomingNumber, new ServerUtil.JsonResponseHandler() {
                    @Override
                    public void onResponse(JSONObject json) {
//                        {"result":{"loginId":"admin","loginPw":"1111","userMyInfo":"안녕하세요 운영자입니다.","userGender":0,"userProfileImg":"","id":14,"userName":"김현철","userPhoneNum":"01051424272","lectureId":1}}
                        Log.d("json", json.toString());
                    }
                });


                Intent mIntent = new Intent(context.getApplicationContext(), CallRecevierActivity.class);
                mIntent.putExtra("phonenum", incomingNumber);
//                mIntent.putExtra("name", user.getUserName());
                mIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(mIntent);


            }
            if ((state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK))) {
                Toast.makeText(context, "Call Received State", Toast.LENGTH_SHORT).show();
            }
            if (state.equals(TelephonyManager.EXTRA_STATE_IDLE)) {
                Toast.makeText(context, "Call Idle State", Toast.LENGTH_SHORT).show();
                CallRecevierActivity.act.finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
