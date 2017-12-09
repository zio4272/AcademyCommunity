package kr.co.cgb.academycommunity.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import kr.co.cgb.academycommunity.R;
import kr.co.cgb.academycommunity.data.Post;
import kr.co.cgb.academycommunity.data.User;
import kr.co.cgb.academycommunity.util.GlobalData;

/**
 * Created by PC on 2017-11-23.
 */

public class UserAdapter extends ArrayAdapter<User> {

    Context mContext;
    List<User> mList;
    LayoutInflater inf;

    public UserAdapter(Context context, List<User> list) {
        super(context, R.layout.user_list_item, list);

        mContext = context;
        mList = list;
        inf = LayoutInflater.from(mContext);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            row = inf.inflate(R.layout.user_list_item, null);
        }

        User data = mList.get(position);

        TextView userNameTxt = (TextView) row.findViewById(R.id.userNameTxt);
        ImageView userProfileImg = (ImageView) row.findViewById(R.id.userProfileImg);

        userNameTxt.setText(data.getUserName());

        Glide.with(getContext()).load(data.getUserProfileImg()).into(userProfileImg);
        userProfileImg.setScaleType(ImageView.ScaleType.CENTER_CROP);

        return row;
    }


}
