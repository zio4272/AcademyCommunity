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
import kr.co.cgb.academycommunity.adapter.PostAdapter;
import kr.co.cgb.academycommunity.data.Post;

/**
 * Created by the on 2017-11-22.
 */

public class PostFragment extends Fragment {

    PostAdapter mAdapter;
    private android.widget.ListView postListView;
    List<Post> postList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.post_fragment_item, container, false);
        this.postListView = (ListView) v.findViewById(R.id.postListView);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setupEvents();
        setValues();
    }

    private void setValues() {
        mAdapter = new PostAdapter(getContext(), postList);
        postListView.setAdapter(mAdapter);
    }

    private void setupEvents() {
    }
}
