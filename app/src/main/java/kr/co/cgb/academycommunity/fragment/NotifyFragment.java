package kr.co.cgb.academycommunity.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import kr.co.cgb.academycommunity.R;
import kr.co.cgb.academycommunity.adapter.NotifyAdapter;
import kr.co.cgb.academycommunity.data.Notify;
import kr.co.cgb.academycommunity.util.GlobalData;

/**
 * Created by PC on 2017-11-27.
 */

public class NotifyFragment extends Fragment {

    NotifyAdapter mAdapter;
    private android.widget.ListView postListView;
    List<Notify> notifyList = new ArrayList<>();
    private ListView notifyListView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.notify_fragment_item, container, false);
        this.notifyListView = (ListView) v.findViewById(R.id.notifyListView);


        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupEvents();
        setValues();
        GlobalData.initData();
    }

    private void setValues() {
        mAdapter = new NotifyAdapter(getContext(), notifyList);
        notifyListView.setAdapter(mAdapter);

    }

    private void setupEvents() {

    }
}
