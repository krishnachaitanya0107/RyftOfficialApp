package com.technocrats.ryftofficialapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

public class ProjectDetailActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private TextView details, dev;
    private PagerViewAdapter pagerViewAdapter;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_detail);

        Toolbar toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Project Details");

        details=findViewById(R.id.details);
        dev=findViewById(R.id.dev);
        viewPager=findViewById(R.id.FragmentContainer);
        intent=getIntent();

        viewPager.setCurrentItem(0);

        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(0);
            }
        });

        dev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(1);
            }
        });



        pagerViewAdapter = new PagerViewAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerViewAdapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                onChangeTab(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    private void onChangeTab(int position) {

        if(position==0)
        {
            details.setTextSize(20);
            dev.setTextSize(15);

        }
        if(position==1)
        {
            details.setTextSize(15);
            dev.setTextSize(20);

        }

    }
}