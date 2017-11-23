package kr.co.cgb.academycommunity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.ImageViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import kr.co.cgb.academycommunity.adapter.IndexViewPagerAdapter;

public class IndexActivity extends BaseActivity {

    private android.support.design.widget.TabLayout tabLayout;
    private ViewPager mainViewPager;
    private int[] tabIcons = {R.mipmap.ic_launcher, R.mipmap.ic_launcher_round, R.mipmap.ic_launcher, R.mipmap.ic_launcher_round};


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

        tabLayout.setupWithViewPager(mainViewPager);


//        tabLayout.addTab(tabLayout.newTab().setText("TALK"));
//        tabLayout.addTab(tabLayout.newTab().setText("PROFILE"));
//        tabLayout.addTab(tabLayout.newTab().setText("MYINFO"));
//        tabLayout.addTab(tabLayout.newTab().setText("TAB 4"));
        tabLayout.getTabAt(0).setIcon(tabIcons[0]).setText("TALK");
        tabLayout.getTabAt(1).setIcon(tabIcons[1]).setText("STUDENT");
        tabLayout.getTabAt(2).setIcon(tabIcons[2]).setText("MYPROFILE");
        tabLayout.getTabAt(3).setIcon(tabIcons[3]).setText("ETC");


        String[] titles = {"TALK", "STUDENT", "MYPROFILE", "ETC"};


        for (int i = 0; i < 4 ; i ++ ){
            LayoutInflater inf = LayoutInflater.from(mContext);
            View v = inf.inflate(R.layout.custom_tab, null);
            TextView tv = (TextView) v.findViewById(R.id.customTabText);
            tv.setText(titles[i]);
            ImageView iv = (ImageView) v.findViewById(R.id.customTabImg);
            iv.setImageResource(tabIcons[i]);
            tabLayout.getTabAt(i).setCustomView(v);
        }





    }

    private void setupTapIcons() {


    }


    @Override
    public void bindViews() {
        this.mainViewPager = (ViewPager) findViewById(R.id.mainViewPager);
        this.tabLayout = (TabLayout) findViewById(R.id.tabLayout);
    }
}
