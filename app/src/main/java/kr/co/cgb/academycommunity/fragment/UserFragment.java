package kr.co.cgb.academycommunity.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import kr.co.cgb.academycommunity.R;
import kr.co.cgb.academycommunity.StudentDetailViewActivity;
import kr.co.cgb.academycommunity.adapter.SortSpinnerAdapter;
import kr.co.cgb.academycommunity.adapter.UserAdapter;
import kr.co.cgb.academycommunity.data.Lecture;
import kr.co.cgb.academycommunity.data.User;
import kr.co.cgb.academycommunity.util.ServerUtil;

/**
 * Created by PC on 2017-11-23.
 */

public class UserFragment extends Fragment {

    List<Lecture> sortList = new ArrayList<>();
    SortSpinnerAdapter sortSpinnerAdapter;

    int lectureNum = 0;

    UserAdapter mAdapter;
    private android.widget.ListView postListView;
    List<User> userList = new ArrayList<>();
    List<User> sortUserList = new ArrayList<>();
    private GridView userListView;
    private android.widget.Spinner sortSpinner;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.user_fragment_item, container, false);
        this.sortSpinner = (Spinner) v.findViewById(R.id.sortSpinner);
        this.userListView = (GridView) v.findViewById(R.id.userListView);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupEvents();
        setValues();
        getUsersFromJson();
        getLectureFromJson();

    }

    private void getUsersFromJson() {

        ServerUtil.getAllUsers(getContext(), new ServerUtil.JsonResponseHandler() {
            @Override
            public void onResponse(JSONObject json) {
                try {
                    userList.clear();
                    JSONArray jsonArray = json.getJSONArray("result");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        User users = User.getUserFromJson(jsonArray.getJSONObject(i));
                        userList.add(users);
                        sortUserList.clear();
                        sortUserList.addAll(userList);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mAdapter.notifyDataSetChanged();
            }


        });


    }

    private void getLectureFromJson() {

        ServerUtil.getAllLectures(getContext(), new ServerUtil.JsonResponseHandler() {
            @Override
            public void onResponse(JSONObject json) {
                try {
                    sortList.clear();
                    sortList.add(new Lecture(true));
                    JSONArray jsonArray = json.getJSONArray("result");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        Lecture lecture = Lecture.getLectureFromJson(jsonArray.getJSONObject(i));
                        sortList.add(lecture);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                sortSpinnerAdapter.notifyDataSetChanged();
            }


        });


    }


    @Override
    public void onResume() {
        super.onResume();
        getUsersFromJson();
        getLectureFromJson();

    }


    private void setValues() {


        mAdapter = new UserAdapter(getContext(), sortUserList);
        userListView.setAdapter(mAdapter);


        sortSpinnerAdapter = new SortSpinnerAdapter(getContext(), sortList);
        sortSpinner.setAdapter(sortSpinnerAdapter);

    }

    private void setupEvents() {

        userListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(), StudentDetailViewActivity.class);
                intent.putExtra("userdata", sortUserList.get(i));
                startActivity(intent);
            }
        });

        sortSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                lectureNum = i;
                sortUserList.clear();

                if (i == 0) {
                    sortUserList.addAll(userList);
                }
                else {
                    for (User u : userList) {
                        Log.d("강의", u.listenLecture.getId()+ "");
                        if (sortList.get(i).getLectureName().equals(u.listenLecture.getLectureName())) {
                            sortUserList.add(u);
                        }
                    }

                }

                mAdapter.notifyDataSetChanged();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
