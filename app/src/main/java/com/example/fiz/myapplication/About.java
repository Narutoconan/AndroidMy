package com.example.fiz.myapplication;

/**
 * Created by Fiz on 16/3/21.
 */

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ListView;
import android.widget.ArrayAdapter;

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

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

@SuppressLint("ValidFragment")
public class About extends Fragment {
    private String textString;
    private static final String[] strs = new String[]{"first", "second", "third", "fourth", "fifth","1","2","3","4","5","6"};
    private List<String> mylist = new ArrayList<String>();
    StringBuilder stringBuilder = new StringBuilder();


    public About(String textString) {
        this.textString = textString;
    }

    public static About newInstance(String textString) {
        About mFragment = new About(textString);
        return mFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.me_about, container, false);
        ListView listView = (ListView) view.findViewById(R.id.listView_about);
        //mylist赋值
        try {
            getPublicTimeline();
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        initData();
        System.out.print(mylist.toString());
        ArrayAdapter myadaPter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, strs);
        listView.setAdapter(myadaPter);

        return view;
    }





    /**
     * 获取服务端数据
     */
    public void getPublicTimeline() throws JSONException {
        TwitterRestClient.get("recent/?source=2&count=20&category=__all__&max_behot_time=1458732835.88&utm_source=toutiao&offset=0&_=1458732835806",
                null, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        // If the response is JSONObject instead of expected JSONArray
                        System.out.println(response);
                        try {
                            JSONArray array = response.getJSONArray("data");

                            for (int i = 0; i < array.length(); i++) {
                                stringBuilder.append(array.getJSONObject(i)
                                        .getString("datetime"));

                                mylist.add(array.getJSONObject(i)
                                        .getString("datetime"));

                            }

                            mylist.add("12344");
                            System.out.print(stringBuilder.toString());
                            System.out.print(mylist.toString());
                        } catch (Exception e) {
                            e.printStackTrace();
                            System.out.print("EXCCEPT///////");
                        }


                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONArray timeline) {
                        // Pull out the first event on the public timeline
                        try {
                            JSONObject firstEvent = (JSONObject) timeline.get(0);

                            String tweetText = firstEvent.getString("text");

                            // Do something with the response


                            System.out.println(tweetText);
                        } catch (JSONException e) {
                            //do something
                        }

                    }
                });
    }

    /**
     * 测试demo
     */
    public void initData() {
        for (int i = 0; i < 10; i++) {
            mylist.add("demo" + i);
        }
    }


}

