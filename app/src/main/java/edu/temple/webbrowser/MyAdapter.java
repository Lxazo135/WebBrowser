package edu.temple.webbrowser;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * Created by Lan on 11/2/2017.
 */

public class MyAdapter extends FragmentStatePagerAdapter {
    private int fragmentCount = 30;


    public MyAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public BrowserFragment getItem(int position) {
        BrowserFragment bf = BrowserFragment.newInstance(position);
        return bf;
    }

    @Override
    public int getCount() {
        return fragmentCount;
    }
}
