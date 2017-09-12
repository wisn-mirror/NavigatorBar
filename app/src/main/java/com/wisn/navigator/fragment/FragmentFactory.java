package com.wisn.navigator.fragment;

import android.app.Fragment;

import java.util.HashMap;

/**
 * Created by wisn on 2017/9/12.
 */

public class FragmentFactory {
    public static HashMap<String, Fragment> mFragments = null;

    public static Fragment getFragment(String fag) {
        if (mFragments == null) {
            synchronized (FragmentFactory.class) {
                mFragments = new HashMap<>();
            }
        }
        Fragment fragment = mFragments.get(fag);
        if (fragment == null) {
            fragment = createFragment(fag);
            if (fragment != null)
                mFragments.put(fag, fragment);
        }
        return fragment;
    }

    private static Fragment createFragment(String fag) {
        switch (fag) {
            case "GiftFragment":
                return new GiftFragment();
            case "HomeFragment":
                return new HomeFragment();
            case "StartFragment":
                return new StartFragment();
            case "WatchFragment":
                return new WatchFragment();

        }
        return null;
    }
}
