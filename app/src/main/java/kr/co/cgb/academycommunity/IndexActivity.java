package kr.co.cgb.academycommunity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

public class IndexActivity extends BaseActivity {

    private android.support.design.widget.TabLayout tabLayout;
    private ViewPager mainViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        bindViews();
        setupEvents();
        setValues();

    }


    @Override
    public void setupEvents() {

    }

    @Override
    public void setValues() {


        tabLayout.addTab(tabLayout.newTab().setText("TAB 1"));
        tabLayout.addTab(tabLayout.newTab().setText("TAB 2"));
        tabLayout.addTab(tabLayout.newTab().setText("TAB 3"));
        tabLayout.addTab(tabLayout.newTab().setText("TAB 4"));


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tabs) {

                Fragment fragment;
                Toast.makeText(mContext, "선택된 탭 " + tabs.getPosition(), Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {


            }
        });


    }


    @Override
    public void bindViews() {
        this.mainViewPager = (ViewPager) findViewById(R.id.mainViewPager);
        this.tabLayout = (TabLayout) findViewById(R.id.tabLayout);
    }
}
