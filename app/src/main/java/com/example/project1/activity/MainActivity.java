package com.example.project1.activity;

import android.os.Bundle;

import com.example.project1.R;
import com.example.project1.adapter.SimplePagerAdapter;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

public class  MainActivity extends AppCompatActivity {

    @BindView(R.id.vp_main)
    ViewPager viewPager;
    @BindView(R.id.tabs)
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }

    private void init(){
        SimplePagerAdapter adapter = new SimplePagerAdapter(getSupportFragmentManager(),this);

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
