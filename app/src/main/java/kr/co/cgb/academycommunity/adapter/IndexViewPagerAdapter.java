package kr.co.cgb.academycommunity.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import kr.co.cgb.academycommunity.fragment.NotifyFragment;
import kr.co.cgb.academycommunity.fragment.PostFragment;
import kr.co.cgb.academycommunity.fragment.SettingFragment;
import kr.co.cgb.academycommunity.fragment.UserFragment;

/**
 * Created by PC on 2017-11-23.
 */

public class IndexViewPagerAdapter extends FragmentStatePagerAdapter {

    public IndexViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new PostFragment();
            case 1:
                return new UserFragment();
            case 2:
                return new NotifyFragment();
            case 3:
                return new SettingFragment();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return 4;
    }
}
