package kr.co.cgb.academycommunity.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import kr.co.cgb.academycommunity.R;
import kr.co.cgb.academycommunity.data.Notify;
import kr.co.cgb.academycommunity.data.Post;

/**
 * Created by PC on 2017-11-27.
 */

public class NotifyAdapter extends ArrayAdapter<Notify> {

    Context mContext;
    List<Notify> mList;
    LayoutInflater inf;

    public NotifyAdapter(Context context, List<Notify> list) {
        super(context, R.layout.notify_list_item, list);

        mContext = context;
        mList = list;
        inf = LayoutInflater.from(mContext);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            row = inf.inflate(R.layout.notify_list_item, null);




        }
        return row;
    }

    @Override
    public int getCount() {
        return 5;
    }
}
