package com.example.model_mine.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * ██╗   ██╗   █████╗   ███╗    ██╗   ██████╗
 * ╚██╗ ██╔╝  ██╔══██╗  ████╗   ██║  ██╔════╝
 * ╚████╔╝   ███████║  ██╔██╗  ██║  ██║  ███╗
 * ╚██╔╝    ██╔══██║  ██║╚██╗ ██║  ██║   ██║
 * ██║     ██║  ██║  ██║ ╚████║   ╚██████╔╝
 * ╚═╝     ╚═╝  ╚═╝  ╚═╝  ╚═══╝    ╚═════╝
 * Date:2021/12/7
 * Time:20:01
 * author:yanghaoyang
 */
public class FragmentAdapters extends FragmentPagerAdapter {
    private ArrayList<Fragment> list;
    private ArrayList<String> name;

    public FragmentAdapters(@NonNull @NotNull FragmentManager fm, ArrayList<Fragment> list, ArrayList<String> name) {
        super(fm);
        this.list = list;
        this.name = name;
    }

    public FragmentAdapters(@NonNull @NotNull FragmentManager fm, ArrayList<Fragment> list) {
        super(fm);
        this.list = list;
    }

    @NonNull
    @NotNull
    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return name.get(position);
    }

    @Override
    public void destroyItem(@NonNull @NotNull ViewGroup container, int position, @NonNull @NotNull Object object) {
    }
}
