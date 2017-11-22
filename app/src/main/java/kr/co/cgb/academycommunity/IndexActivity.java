package kr.co.cgb.academycommunity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

import kr.co.cgb.academycommunity.fragment.PostFragment;

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



        MyViewPagerAdapter adapter = new MyViewPagerAdapter(getSupportFragmentManager());
        mainViewPager.setAdapter(adapter);
        mainViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


        tabLayout.addTab(tabLayout.newTab().setText("Tab One"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab Two"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab Three"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mainViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

    class MyViewPagerAdapter extends FragmentPagerAdapter {
        private int tabCount;
        public MyViewPagerAdapter(FragmentManager fm) {
            super(fm);
            tabCount = tabCount;
        }



        @Override
        public Fragment getItem(int position) {

            // Returning the current tabs
            switch (position) {
                case 0:
                    PostFragment tab1 = new PostFragment();
                    return tab1;
                case 1:
                    PostFragment tab2 = new PostFragment();
                    return tab2;
                case 2:
                    PostFragment tab3 = new PostFragment();
                    return tab3;
                default:
                    return null;
            }
        }






        @Override
        public int getCount() {
            return tabCount;
        }
    }

    @Override
    public void bindViews() {
        this.mainViewPager = (ViewPager) findViewById(R.id.mainViewPager);
        this.tabLayout = (TabLayout) findViewById(R.id.tabLayout);
    }
}
