package kr.co.cgb.academycommunity.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import kr.co.cgb.academycommunity.R;
import kr.co.cgb.academycommunity.data.Lecture;

/**
 * Created by PC on 2017-12-05.
 */

public class LectureFilterSpinnerAdapter extends ArrayAdapter<Lecture> {
    Context mContext;
    List<Lecture> mList;
    LayoutInflater inf;


    public LectureFilterSpinnerAdapter(Context context, List<Lecture> list) {
        super(context, R.layout.lecture_filter_spinner_item, list);
        mContext = context;
        mList = list;
        inf = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            row = inf.inflate(R.layout.lecture_filter_spinner_item, null);
        }

        Lecture data = mList.get(position);

        TextView sortTypeTxt = (TextView) row.findViewById(R.id.lectureFilterTxt);

        sortTypeTxt.setText(data.getLectureName());

        return row;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            row = inf.inflate(R.layout.lecture_filter_spinner_item, null);
        }


        Lecture data = mList.get(position);

        TextView sortTypeTxt = (TextView) row.findViewById(R.id.lectureFilterTxt);

        sortTypeTxt.setText(data.getLectureName());

        return row;
    }


}
