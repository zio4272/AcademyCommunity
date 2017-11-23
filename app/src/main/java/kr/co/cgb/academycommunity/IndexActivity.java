package kr.co.cgb.academycommunity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Toast;

import kr.co.cgb.academycommunity.adapter.IndexViewPagerAdapter;

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

        mainViewPager.setAdapter(new IndexViewPagerAdapter(getSupportFragmentManager()));
        mainViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mainViewPager));
        tabLayout.addTab(tabLayout.newTab().setText("TALK"));
        tabLayout.addTab(tabLayout.newTab().setText("PROFILE"));
        tabLayout.addTab(tabLayout.newTab().setText("MYINFO"));
        tabLayout.addTab(tabLayout.newTab().setText("TAB 4"));


    }


    @Override
    public void bindViews() {
        this.mainViewPager = (ViewPager) findViewById(R.id.mainViewPager);
        this.tabLayout = (TabLayout) findViewById(R.id.tabLayout);
    }
}
