package com.aaaliua.fragment;

import com.aaaliua.lifeapp.R;
import com.aaaliua.utils.Lib;
import com.dazhongcun.widget.PagerSlidingTabStrip;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

@SuppressLint("NewApi")
public class ContentFragment extends Fragment {

    private ViewPager mViewPager;
    private PagerSlidingTabStrip mTabs;

	private String title;
	
	public static ContentFragment newInstance(String title){
		return new ContentFragment(title);
	}
	
	public ContentFragment(String title) {
		this.title = title;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_main,container, false);
		if (Lib.hasLollipop())
            ((ActionBarActivity) getActivity()).getSupportActionBar().setElevation(0);
		
		
		mViewPager = (ViewPager) v.findViewById(R.id.awesomepager);
        mViewPager.setPageMargin(Lib.convertDpToPixels(getActivity(), 16));
        mTabs = (PagerSlidingTabStrip) v.findViewById(R.id.tabs);
		
        mViewPager.setAdapter(new PagerAdapter(getChildFragmentManager()));
        mTabs.setViewPager(mViewPager);
        mTabs.setVisibility(View.VISIBLE);

        mTabs.setIndicatorColor(Color.parseColor("#FEFE62")/*getResources().getColor(android.R.color.white)*/);
        mTabs.setIndicatorHeight(10);
        mTabs.setTextColorResource(android.R.color.white);
        mTabs.setUnderlineHeight(0);
        mTabs.setDividerColor(getResources().getColor(android.R.color.transparent));//间距线条
        // Work-around a bug that sometimes happens with the tabs
        mViewPager.setCurrentItem(0);

        if (Lib.hasLollipop())
            mTabs.setElevation(1f);
		
		return v;
	}
	
	
	private class PagerAdapter extends FragmentPagerAdapter {

        private final String[] TITLES = {getString(R.string.choiceAllMovies), getString(R.string.choiceFavorites), getString(R.string.choiceNewReleases),
                getString(R.string.chooserWatchList), getString(R.string.choiceWatchedMovies), getString(R.string.choiceUnwatchedMovies), getString(R.string.choiceCollections)};

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }

        @Override
        public Fragment getItem(int index) {
            switch (index) {
                case 0:
                    return new MsgFragment();
                case 1:
                    return new MsgFragment();
                case 2:
                    return new MsgFragment();
                case 3:
                    return new MsgFragment();
                case 4:
                    return new MsgFragment();
                case 5:
                    return new MsgFragment();
                case 6:
                    return new MsgFragment();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }
    }
}
