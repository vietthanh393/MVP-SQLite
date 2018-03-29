package com.example.mvpdemo;

/**
 * Listener interface for Fragments accommodated in {@link ViewPager}
 * enabling them to know when it becomes visible inside the
 * ViewPager.
 */
public interface ViewPagerVisibilityListener {
    void onFragmentVisible();
}