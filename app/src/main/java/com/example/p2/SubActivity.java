package com.example.p2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;

public class SubActivity extends AppCompatActivity
{

    private ViewPager2 viewPager;
    private frag1 f1;
    private frag2 f2;
    private frag3 f3;
    private TabLayout tabLayout;
    private PageAdapter pageAdapter;

    private String nickName;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        createFragment();
        createViewpager();
        settingTabLayout();

        Intent intent = getIntent();
        nickName = intent.getStringExtra("name");
        Toast.makeText(this, nickName+"님 환영합니다!", Toast.LENGTH_SHORT).show();
    }
    public String getNick(){
        return nickName;
    }
    private void createFragment(){
        f1 = new frag1();
        f2 = new frag2();
        f3 = new frag3();
    }
    private void createViewpager(){
        viewPager = (ViewPager2)findViewById(R.id.viewpager_control);
        pageAdapter = new PageAdapter(getSupportFragmentManager(),getLifecycle());
        pageAdapter.addFragment(f1);
        pageAdapter.addFragment(f2);
        pageAdapter.addFragment(f3);

        viewPager.setAdapter(pageAdapter);

        viewPager.setUserInputEnabled(false); //no swiping
    }
    private void settingTabLayout(){
        tabLayout = (TabLayout)findViewById(R.id.tablayout_control);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int pos = tab.getPosition();
                switch(pos){
                    case 0:
                        viewPager.setCurrentItem(0);
                        break;
                    case 1:
                        viewPager.setCurrentItem(1);
                        break;
                    case 2:
                        viewPager.setCurrentItem(2);
                        break;
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }


        });
    }
}