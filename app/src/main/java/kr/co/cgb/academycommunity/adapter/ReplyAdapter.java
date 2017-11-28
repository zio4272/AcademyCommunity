package kr.co.cgb.academycommunity.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import kr.co.cgb.academycommunity.PostPopupActivity;
import kr.co.cgb.academycommunity.R;
import kr.co.cgb.academycommunity.data.Post;
import kr.co.cgb.academycommunity.data.Reply;

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
        TextView mainReplyLikeTxt = (TextView) row.findViewById(R.id.mainReplyLikeTxt);
        TextView mainReplyAddTxt = (TextView) row.findViewById(R.id.mainReplyAddTxt);

        LinearLayout subReplyLayout = (LinearLayout) row.findViewById(R.id.subReplyLayout);
        ImageView subReplyProfileImg = (ImageView) row.findViewById(R.id.subReplyProfileImg);
        TextView subReplyUserNameTxt = (TextView) row.findViewById(R.id.subReplyUserNameTxt);
        final TextView subReplyTagNameTxt = (TextView) row.findViewById(R.id.subReplyTagNameTxt);
        TextView subReplyContentTxt = (TextView) row.findViewById(R.id.subReplyContentTxt);
        TextView subReplyTimeTxt = (TextView) row.findViewById(R.id.subReplyTimeTxt);
        TextView subReplyLikeTxt = (TextView) row.findViewById(R.id.subReplyLikeTxt);
        TextView subReplyAddTxt = (TextView) row.findViewById(R.id.subReplyAddTxt);


        if (data.getParentId() == -1) {
            // 댓글인 경우
            mainReplyLayout.setVisibility(View.VISIBLE);
            subReplyLayout.setVisibility(View.GONE);

            mainReplyUserNameTxt.setText(data.getUserWriterData().getUserName());
            mainReplyContentTxt.setText(data.getReplyContent());

            mainReplyAddTxt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int mainReply = data.getReplyId();
                    Log.d("오리지날", mainReply + "");
//                    subReplyTagNameTxt.setText(data.post.getUserWriterData().getUserName());
//                    subReplyTagNameTxt.setVisibility(View.VISIBLE);
                    ((PostPopupActivity) mContext).tagUser =  data.getUserWriterData();
                    ((PostPopupActivity) mContext).selectedReply = mainReply;
                }
            });

        } else {
            mainReplyLayout.setVisibility(View.GONE);
            subReplyLayout.setVisibility(View.VISIBLE);
            subReplyTagNameTxt.setVisibility(View.VISIBLE);

            subReplyUserNameTxt.setText(data.getUserWriterData().getUserName());
            subReplyContentTxt.setText(data.getReplyContent());

            if (data.getUserWriterData() != null) {
                subReplyTagNameTxt.setText(data.getTagUserName().getUserName());
            }
        }

        subReplyAddTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int subReply = data.getReplyId();
                Log.d("서브", subReply + "");
                subReplyTagNameTxt.setText(data.getTagUserName().getUserName());
//                subReplyTagNameTxt.setText(data.post.getUserWriterData().getUserName());
//                subReplyTagNameTxt.setVisibility(View.VISIBLE);
                ((PostPopupActivity) mContext).tagUser =  data.getUserWriterData();
                ((PostPopupActivity) mContext).selectSubReply = subReply;

            }
        });
        return row;
    }


}
