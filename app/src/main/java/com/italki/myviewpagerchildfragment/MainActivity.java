package com.italki.myviewpagerchildfragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends FragmentActivity {
    private ViewPager mPager;
    private ViewPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPager = (ViewPager) findViewById(R.id.my_pager);
        mAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        //The Fragments supplied by the FragmentPagerAdapter are auto-tagged when they're instantiated.
        Fragment fragment = (Fragment) getSupportFragmentManager().
                findFragmentByTag("android:switcher:" + R.id.my_pager + ":" + mPager.getCurrentItem());

        if (fragment != null && fragment instanceof BaseFragment) // could be null if not instantiated yet
        {
            if (fragment.getView() != null) {
                BaseFragment bf = (BaseFragment)fragment;
                if(bf.isShowingChild()) {
                    replaceChild(bf, mPager.getCurrentItem());
                }
                else {
                    backButton();
                }
            }
        }
    }

    // Back Button Pressed
    private void backButton() {
        finish();
    }

    public void replaceChild(BaseFragment oldFrg, int position) {
        mAdapter.replaceChildFragment(oldFrg, position);
    }
}
