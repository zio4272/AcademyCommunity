package kr.co.cgb.academycommunity.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import kr.co.cgb.academycommunity.PostPopupActivity;
import kr.co.cgb.academycommunity.PostWriteActivity;
import kr.co.cgb.academycommunity.R;
import kr.co.cgb.academycommunity.adapter.PostAdapter;
import kr.co.cgb.academycommunity.data.Post;
import kr.co.cgb.academycommunity.util.GlobalData;
import kr.co.cgb.academycommunity.util.ServerUtil;

/**
 * Created by the on 2017-11-22.
 */

public class PostFragment extends Fragment {

    PostAdapter mAdapter;
    private android.widget.ListView postListView;
    List<Post> postList = new ArrayList<>();
    private android.widget.ImageView postWriteImg;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.post_fragment_item, container, false);
        this.postWriteImg = (ImageView) v.findViewById(R.id.postWriteImg);
        this.postListView = (ListView) v.findViewById(R.id.postListView);

        return v;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupEvents();
        setValues();
        GlobalData.initData();
        getPostFromJson();
    }

    void getPostFromJson() {
        ServerUtil.getPost(getActivity(), new ServerUtil.JsonResponseHandler() {
            @Override
            public void onResponse(JSONObject json) {

                try {
                    postList.clear();
                    JSONArray jsonArray = json.getJSONArray("result");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        Post post = Post.getPostFromJson(jsonArray.getJSONObject(i));
                        postList.add(post);
//                        TODO - userProfileImg 없다고 뜨는거 해결 해야함
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                mAdapter.notifyDataSetChanged();

            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        getPostFromJson();
    }


    private void setValues() {
        postList = GlobalData.posts;
        mAdapter = new PostAdapter(getContext(), postList);
        postListView.setAdapter(mAdapter);
    }

    private void setupEvents() {
        postListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), PostPopupActivity.class);
                intent.putExtra("postdata", GlobalData.posts.get(i));
                startActivity(intent);
            }
        });

        postWriteImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PostWriteActivity.class);
                startActivity(intent);
            }
        });
    }
}
