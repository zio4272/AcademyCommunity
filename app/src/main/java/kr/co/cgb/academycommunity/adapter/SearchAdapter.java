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
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import kr.co.cgb.academycommunity.PostPopupActivity;
import kr.co.cgb.academycommunity.R;
import kr.co.cgb.academycommunity.SearchActivity;
import kr.co.cgb.academycommunity.data.Post;
import kr.co.cgb.academycommunity.data.Reply;


public class SearchAdapter extends ArrayAdapter<Post> {

    Context mContext;
    List<Post> mList;
    LayoutInflater inf;

    public SearchAdapter(Context context, List<Post> list) {
        super(context, R.layout.search_list_item, list);

        mContext = context;
        mList = list;
        inf = LayoutInflater.from(mContext);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            row = inf.inflate(R.layout.search_list_item, null);
        }

        Post data = mList.get(position);
        TextView resultTxt = (TextView) row.findViewById(R.id.resultTxt);
        resultTxt.setText(data.getPostContent());

        return row;
    }
}



