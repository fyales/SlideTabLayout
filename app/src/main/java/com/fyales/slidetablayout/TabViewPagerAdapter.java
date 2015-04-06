package com.fyales.slidetablayout;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


/**
 * @author fyales
 */
public class TabViewPagerAdapter extends FragmentPagerAdapter {

    private final int PAGE_COUNT = 3;


    private String mTabTitle[] = new String[]{"朝代","人物","战争"};
    private Context mContext;

    public TabViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.mContext = context;
    }

    @Override
    public Fragment getItem(int position) {

        return BaseFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return  mTabTitle[position];
    }
}
