package kr.co.cgb.academycommunity.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.json.JSONObject;
import org.json.JSONStringer;

import java.util.Calendar;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import kr.co.cgb.academycommunity.R;
import kr.co.cgb.academycommunity.data.Post;
import kr.co.cgb.academycommunity.util.TimeAgoUtil;

/**
 * Created by the on 2017-11-22.
 */

public class PostAdapter extends ArrayAdapter<Post> {


    Context mContext;
    List<Post> mList;
    LayoutInflater inf;

    public PostAdapter(Context context, List<Post> list) {
        super(context, R.layout.post_list_item, list);

        mContext = context;
        mList = list;
        inf = LayoutInflater.from(mContext);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            row = inf.inflate(R.layout.post_list_item, null);


        }

        LinearLayout postLayout = (LinearLayout) row.findViewById(R.id.postLayout);
        CircleImageView profileImg = (CircleImageView) row.findViewById(R.id.profileImg);
//        ImageView profileImg = (ImageView) row.findViewById(R.id.profileImg);
        ImageView profileINoImg = (ImageView) row.findViewById(R.id.profileINoImg);
        TextView writerNameTxt = (TextView) row.findViewById(R.id.writerNameTxt);
        TextView writeTimeTxt = (TextView) row.findViewById(R.id.writeTimeTxt);
        TextView contentTxt = (TextView) row.findViewById(R.id.contentTxt);
        TextView replyCountTxt = (TextView) row.findViewById(R.id.replyCountTxt);


        Post data = mList.get(position);


        String profileStr = data.getUserWriterData().getUserProfileImg();
        if (profileStr.equals("noImage")) {
            profileImg.setImageResource(R.drawable.noimage);
        } else {
            Glide.with(mContext).load(data.getUserWriterData().getUserProfileImg()).into(profileImg);
        }
        profileImg.setScaleType(ImageView.ScaleType.CENTER_CROP);

        writerNameTxt.setText(data.userWriterData.getUserName());

        Calendar now = Calendar.getInstance();
        Log.d("현재시간", now +"");
       long time = now.getTimeInMillis() - data.getPostDate().getTimeInMillis();
        Log.d("시간계산", time+"");

        Log.d("작성된 시간",data.getPostDate().getTimeInMillis() +"");

       int minute = (int) (time / 1000 / 60);
       Log.d("시간나누기", minute +"");
        String minuteAgo = TimeAgoUtil.getTimeAgoString(minute);
        writeTimeTxt.setText(minuteAgo);

//            글자수 20 제한 뒤는 ...으로 표기
        if (data.getPostContent().length() > 20) {
            String contentHTML = data.getPostContent().substring(0, 20) + "...";
            contentTxt.setText(Html.fromHtml(contentHTML));
        } else {
            contentTxt.setText(data.getPostContent());
        }
        return row;
    }


}
