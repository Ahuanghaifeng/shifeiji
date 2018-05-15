package com.sfj.sfj.ui.fragment;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;


import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;
import com.sfj.sfj.R;
import com.sfj.sfj.base.BaseFragment;
import com.sfj.sfj.widget.AppToolbar;
import com.sfj.sfj.widget.NoScrollViewPager;

/**
 * Created by wangyu on 2018/1/8.
 */

public class HomeFragment extends BaseFragment {

    private NoScrollViewPager mViewPager;
    private BottomBar mBottomBar;
    private int position;
    public static final String BUNDLE_KEY_MONEY = "bundle_key_money";

    public static HomeFragment newInstance(String money) {

        Bundle args = new Bundle();
        args.putString(BUNDLE_KEY_MONEY, money);
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mBottomBar = (BottomBar) view.findViewById(R.id.bottomBar);
        mViewPager = (NoScrollViewPager) view.findViewById(R.id.view_pager);
        mViewPager.setAdapter(new HomeFragmentAdapter(getChildFragmentManager()));
        mViewPager.setOffscreenPageLimit(3);

        mBottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId) {
                    case R.id.tab_weather:
                        position = 0;
                        break;
                    case R.id.tab_machine:
                        position = 1;
                        break;
                    case R.id.tab_control:
                        position = 2;
                        break;
                }
                mViewPager.setCurrentItem(position);
            }
        });
    }

    @Override
    protected boolean initTitle(AppToolbar appBar) {
        return false;
    }




    public class HomeFragmentAdapter extends FragmentPagerAdapter {


        public HomeFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return createFragment(position);
        }

        @Override
        public int getCount() {

            return 3;
        }

    }

    public Fragment createFragment(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = WeatherFragment.newInstance();
                break;
            case 1:
                fragment = MachineFragment.newInstance();
                break;
            case 2:
                fragment = ControlFragment.newInstance();
                break;
        }
        return fragment;
    }

}
