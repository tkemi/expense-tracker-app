package com.example.project1.adapter;

import android.content.Context;

import com.example.project1.fragment.FragmentAddCategory;
import com.example.project1.fragment.FragmentAddExpense;
import com.example.project1.fragment.FragmentExpensesByCategory;
import com.example.project1.fragment.FragmentExpensesView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class SimplePagerAdapter extends FragmentPagerAdapter {

    private static final int FRAGMENT_COUNT=4;
    public static final int FRAGMENT_ADD_EXPENSE=0;
    public static final int FRAGMENT_EXPENSES_VIEW=1;
    public static final int FRAGMENT_EXPENSES_BY_CATEGORY=2;
    public static final int FRAGMENT_ADD_CATEGORY=3;
    private List<String> mTitles;

    public SimplePagerAdapter(@NonNull FragmentManager fm, Context context) {
        super(fm);
        initTitles(context);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case FRAGMENT_ADD_EXPENSE:
                return FragmentAddExpense.newInstance();
            case FRAGMENT_EXPENSES_VIEW:
                return FragmentExpensesView.newInstance();
            case FRAGMENT_EXPENSES_BY_CATEGORY:
                return FragmentExpensesByCategory.newInstance();
            case FRAGMENT_ADD_CATEGORY:
                return FragmentAddCategory.newInstance();
        }

        return null;
    }

    @Override
    public int getCount() {
        return FRAGMENT_COUNT;
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }

    private void initTitles(Context context){
        mTitles = new ArrayList<>();
        mTitles.add("Add expense");
        mTitles.add("View expenses");
        mTitles.add("Expenses by category");
        mTitles.add("Add category");
    }
}
