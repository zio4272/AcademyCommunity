package kr.co.cgb.academycommunity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import kr.co.cgb.academycommunity.adapter.SearchAdapter;
import kr.co.cgb.academycommunity.data.Post;
import kr.co.cgb.academycommunity.util.ServerUtil;
import kr.co.cgb.academycommunity.util.SoundSearcher;

public class SearchActivity extends BaseActivity {

    SearchAdapter mAdapter;
    List<Post> postList = new ArrayList<>();
    List<Post> postSearchList = new ArrayList<>();

    private android.widget.ImageView backBtn;
    private android.widget.EditText searchEdt;
    private android.widget.ListView searchListview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        bindViews();
        setupEvents();
        setValues();
        getPostFromJson();


    }

    @Override
    public void setupEvents() {

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        searchEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                postSearch(searchEdt.getText().toString());

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        searchListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(mContext, PostPopupActivity.class);
                intent.putExtra("postdata", postSearchList.get(i));
                startActivity(intent);
            }
        });


    }

    void getPostFromJson() {
        ServerUtil.getPost(mContext, new ServerUtil.JsonResponseHandler() {
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


            }
        });

    }


    private void postSearch(String inputStr) {
        postSearchList.clear();
        if (inputStr.length() > 0) {
            for (Post post : postList) {
                if (post.getPostContent().startsWith(inputStr)) {
                    postSearchList.add(post);
                }
            }

            if (postSearchList.size() == 0) {
                for (Post post : postList) {
                    if (SoundSearcher.matchString(post.getPostContent(), inputStr)) {
                        postSearchList.add(post);
                    }
                }
            }
        } else {
//            postSearchList.addAll(postList);
        }
        if (mAdapter != null) {
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void setValues() {
        mAdapter = new SearchAdapter(mContext, postSearchList);
        searchListview.setAdapter(mAdapter);

    }

    @Override
    public void bindViews() {
        this.searchListview = (ListView) findViewById(R.id.searchListview);
        this.searchEdt = (EditText) findViewById(R.id.searchEdt);
        this.backBtn = (ImageView) findViewById(R.id.backBtn);

    }
}
