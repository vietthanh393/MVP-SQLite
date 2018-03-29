package com.example.mvpdemo;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.mvpdemo.listuser.ListUserFragment;
import com.example.mvpdemo.newuser.NewUserFragment;

public class MainActivity extends AppCompatActivity {
    private static final int TAB_NEW_USER_ID = 0;
    private static final int TAB_LIST_USER_ID = 1;
    private BottomNavigationView bottomNavigationView;
    private ViewPager viewPager;
    private MenuItem prevMenuItem;
    private NewUserFragment newUserFragment;
    private ListUserFragment listUserFragment;
    private ViewPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hideActionBar();
        setupBottomView();
        setupViewPager();
        setupAdapter(viewPager);
    }

    private void hideActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    private void setupBottomView() {
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_new:
                                viewPager.setCurrentItem(TAB_NEW_USER_ID);
                                break;
                            case R.id.action_list:
                                viewPager.setCurrentItem(TAB_LIST_USER_ID);
                                break;
                        }
                        return false;
                    }
                });
    }

    private void setupViewPager() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                } else {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                prevMenuItem = bottomNavigationView.getMenu().getItem(position);
                sendFragmentVisibilityChange(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setupAdapter(ViewPager viewPager) {
        pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        newUserFragment = new NewUserFragment();
        listUserFragment = new ListUserFragment();
        pagerAdapter.addFragment(newUserFragment);
        pagerAdapter.addFragment(listUserFragment);
        viewPager.setAdapter(pagerAdapter);
    }

    private void sendFragmentVisibilityChange(final int position) {
        try {
            final Fragment fragment = getFragment(position);
            if (fragment instanceof ViewPagerVisibilityListener) {
                ((ViewPagerVisibilityListener) fragment).onFragmentVisible();
            }
        } catch (IllegalStateException e) {
            Log.e("MainActivity", "IllegalStateException: " + e);
        }
    }

    private Fragment getFragment(int position) {
        if (position == TAB_NEW_USER_ID) {
            return newUserFragment;
        } else if (position == TAB_LIST_USER_ID) {
            return listUserFragment;
        }
        throw new IllegalStateException("Unknown fragment index: " + position);
    }
}
