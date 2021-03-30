package com.example.konaking;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

// FragMant1과 Fragment2를 연결하여 화면슬라이드를 이용하기 위한 클래스
public class VPAdapter extends FragmentStatePagerAdapter {
    private ArrayList<Fragment> items; // item ArrayList 생성
    public VPAdapter(FragmentManager fm) {
        super(fm);
        items = new ArrayList<Fragment>();
        items.add(new Fragment1()); // item ArrayList에 Fragment1 추가
        items.add(new Fragmant2()); // item ArrayList에 Fragment2 추가
    }

    @Override
    public Fragment getItem(int position) {
        return items.get(position);
    }

    @Override
    public int getCount() {
        return items.size();
    }
}
