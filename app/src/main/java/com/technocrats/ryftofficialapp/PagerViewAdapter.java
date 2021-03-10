package com.technocrats.ryftofficialapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PagerViewAdapter extends FragmentPagerAdapter {

    public PagerViewAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        Fragment fragment=null;
        switch (position)
        {
            case 0:
                fragment= new DetailsFragment();
                break;



            case 1:
                fragment= new DevFragment();
                break;



        }










        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
