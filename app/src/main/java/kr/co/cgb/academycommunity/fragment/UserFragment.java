package kr.co.cgb.academycommunity.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import kr.co.cgb.academycommunity.R;
import kr.co.cgb.academycommunity.StudentDetailViewActivity;
import kr.co.cgb.academycommunity.adapter.UserAdapter;
import kr.co.cgb.academycommunity.data.User;

/**
 * Created by PC on 2017-11-23.
 */

public class UserFragment extends Fragment {

    UserAdapter mAdapter;
    private android.widget.ListView postListView;
    List<User> userList = new ArrayList<>();
    private GridView userListView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.user_fragment_item, container, false);
        this.userListView = (GridView) v.findViewById(R.id.userListView);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setupEvents();
        setValues();
    }

    private void setValues() {
        mAdapter = new UserAdapter(getContext(), userList);
        userListView.setAdapter(mAdapter);
    }

    private void setupEvents() {

        userListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(), StudentDetailViewActivity.class);
                startActivity(intent);
            }
        });
    }
}
