package kr.co.cgb.academycommunity.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.Calendar;
import java.util.List;

import kr.co.cgb.academycommunity.PostPopupActivity;
import kr.co.cgb.academycommunity.R;
import kr.co.cgb.academycommunity.data.Reply;
import kr.co.cgb.academycommunity.data.User;
import kr.co.cgb.academycommunity.util.ContextUtil;
import kr.co.cgb.academycommunity.util.TimeAgoUtil;

/**
 * Created by PC on 2017-11-24.
 */

public class ReplyAdapter extends ArrayAdapter<Reply> {

    Context mContext;
    List<Reply> mList;
    LayoutInflater inf;

    public ReplyAdapter(Context context, List<Reply> list) {
        super(context, R.layout.reply_list_item, list);

        mContext = context;
        mList = list;
        inf = LayoutInflater.from(mContext);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            row = inf.inflate(R.layout.reply_list_item, null);
        }

        final Reply data = mList.get(position);


        LinearLayout mainReplyLayout = (LinearLayout) row.findViewById(R.id.mainReplyLayout);
        ImageView mainReplyProfileImg = (ImageView) row.findViewById(R.id.mainReplyProfileImg);
        TextView mainReplyUserNameTxt = (TextView) row.findViewById(R.id.mainReplyUserNameTxt);
        TextView mainReplyContentTxt = (TextView) row.findViewById(R.id.mainReplyContentTxt);
        TextView mainReplyTimeTxt = (TextView) row.findViewById(R.id.mainReplyTimeTxt);
//        TextView mainReplyLikeTxt = (TextView) row.findViewById(R.id.mainReplyLikeTxt);
//        TextView mainReplyAddTxt = (TextView) row.findViewById(R.id.mainReplyAddTxt);

        LinearLayout subReplyLayout = (LinearLayout) row.findViewById(R.id.subReplyLayout);
        ImageView subReplyProfileImg = (ImageView) row.findViewById(R.id.subReplyProfileImg);
        TextView subReplyUserNameTxt = (TextView) row.findViewById(R.id.subReplyUserNameTxt);
        final TextView subReplyTagNameTxt = (TextView) row.findViewById(R.id.subReplyTagNameTxt);
        TextView subReplyContentTxt = (TextView) row.findViewById(R.id.subReplyContentTxt);
        TextView subReplyTimeTxt = (TextView) row.findViewById(R.id.subReplyTimeTxt);
//        TextView subReplyLikeTxt = (TextView) row.findViewById(R.id.subReplyLikeTxt);
//        TextView subReplyAddTxt = (TextView) row.findViewById(R.id.subReplyAddTxt);


        if (data.getParentId() == data.getReplyId()) {
            // 댓글인 경우
            mainReplyLayout.setVisibility(View.VISIBLE);
            subReplyLayout.setVisibility(View.GONE);

            mainReplyUserNameTxt.setText(data.getWriteUser().getUserName());
            mainReplyContentTxt.setText(data.getReplyContent());

//            mainReplyAddTxt.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    int mainReply = data.getReplyId();
//                    Log.d("오리지날", mainReply + "");
////                    subReplyTagNameTxt.setText(data.post.getReplyWriteName().getUserName());
////                    subReplyTagNameTxt.setVisibility(View.VISIBLE);
//                    ((PostPopupActivity) mContext).tagUser =  data.getReplyWriteName();
//                    ((PostPopupActivity) mContext).selectedReply = mainReply;
//                }
//            });

        } else {
            mainReplyLayout.setVisibility(View.GONE);
            subReplyLayout.setVisibility(View.VISIBLE);
            subReplyTagNameTxt.setVisibility(View.VISIBLE);

            subReplyUserNameTxt.setText(data.getWriteUser().getUserName());
            subReplyContentTxt.setText(data.getReplyContent());

            subReplyTagNameTxt.setText(data.tagUserName.getUserName());
//            if (data.getReplyWriteName() != null) {
//                subReplyTagNameTxt.setText(data.getTagUserName().getUserName());
//            }

//            subReplyAddTxt.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    int subReply = data.getReplyId();
//                    Log.d("서브", subReply + "");
////                subReplyTagNameTxt.setText(data.post.getReplyWriteName().getUserName());
////                subReplyTagNameTxt.setVisibility(View.VISIBLE);
//                    ((PostPopupActivity) mContext).tagUser =  data.getReplyWriteName();
//                    ((PostPopupActivity) mContext).selectSubReply = subReply;
//
//                }
//            });
        }

        String profileStr = data.getWriteUser().getUserProfileImg();
        if (profileStr.equals("noImage")) {
            mainReplyProfileImg.setImageResource(R.drawable.noimage);
            subReplyProfileImg.setImageResource(R.drawable.noimage);
        } else {
            Glide.with(mContext).load(data.getWriteUser().getUserProfileImg()).into(mainReplyProfileImg);
            Glide.with(mContext).load(data.getWriteUser().getUserProfileImg()).into(subReplyProfileImg);
        }
        mainReplyProfileImg.setScaleType(ImageView.ScaleType.CENTER_CROP);
        subReplyProfileImg.setScaleType(ImageView.ScaleType.CENTER_CROP);

        Calendar now = Calendar.getInstance();
        long time = now.getTimeInMillis() - data.getReplyDate().getTimeInMillis();
        int minute = (int) (time / 1000 / 60);
        String minuteAgo = TimeAgoUtil.getTimeAgoString(minute);
        mainReplyTimeTxt.setText(minuteAgo);
        subReplyTimeTxt.setText(minuteAgo);

//        mainReplyLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                final String [] reply = {"답글달기", "취소"};
//
//                new AlertDialog.Builder(getContext())
//
//                         .setItems(reply, new DialogInterface.OnClickListener() {
//                             @Override
//                             public void onClick(DialogInterface dialogInterface, int i) {
//                                 if (i == 1 ){
//                                     Toast.makeText(mContext, "취소되었습니다.", Toast.LENGTH_SHORT).show();
//                                 }
//
//                             }
//                         })
//                        .show();
//
//            }
//        });


        return row;
    }


}
