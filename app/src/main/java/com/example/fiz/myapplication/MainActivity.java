package com.example.fiz.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.ArrayAdapter;


import java.util.ArrayList;
import java.util.List;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import android.util.Log;


import com.loopj.android.http.*;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;


public class MainActivity extends AppCompatActivity {

    private ViewPager mPager;
    private ArrayList<Fragment> fragmentsList;
    private LinearLayout ll_top;
    private TextView text1;
    private static final String Tag = "MainActivity";
    private List<String> mylist = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("demo");
        toolbar.setSubtitle("1234");
        toolbar.setLogo(R.drawable.ic_launcher);
        setSupportActionBar(toolbar);
        initView();

//        Log.e(MainActivity.Tag, mylist.toString());
    }


    private void initView() {


        ll_top = (LinearLayout) findViewById(R.id.ll_top);
        mPager = (ViewPager) findViewById(R.id.vPager);
        fragmentsList = new ArrayList<Fragment>();

        Fragment activityfragment = TestFragment.newInstance("首页");
        Fragment groupFragment = TestFragment.newInstance("消息");
        Fragment friendsFragment = TestFragment.newInstance("发现");
        Fragment chatFragment = TestFragment.newInstance("关于");
        Fragment about = About.newInstance("关于");
        //实例化
        Fragment homepage = Home.newInstance("首页");


        fragmentsList.add(homepage);/**首页**/
        fragmentsList.add(groupFragment);
        fragmentsList.add(friendsFragment);
        fragmentsList.add(about);

        mPager.setAdapter(new MyFragmentPagerAdapter(
                getSupportFragmentManager(), fragmentsList));
        mPager.setCurrentItem(0);


        BottomTabBar navigationBar = new BottomTabBar(this);
        ll_top.setBackgroundColor(Color.parseColor("#F8F8FF"));
        navigationBar.attachToParent(ll_top, new String[]{"首页", "消息", "发现",
                "我"}, mPager);
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

    public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        private ArrayList<Fragment> fragmentsList;

        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public MyFragmentPagerAdapter(FragmentManager fm,
                                      ArrayList<Fragment> fragments) {
            super(fm);
            this.fragmentsList = fragments;
        }

        @Override
        public int getCount() {
            return fragmentsList.size();
        }

        @Override

        public Fragment getItem(int arg0) {


            return fragmentsList.get(arg0);
        }

        @Override
        public int getItemPosition(Object object) {
            return super.getItemPosition(object);
        }

    }


}
