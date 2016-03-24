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
import android.widget.SimpleAdapter;

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
    private static final String[] strs = new String[]{"first", "second", "third", "fourth", "fifth", "1", "2", "3", "4", "5", "6"};
    StringBuilder stringBuilder = new StringBuilder();
    //保存数据的变量
    private ArrayList<HashMap<String, Object>> arrayList = new ArrayList<HashMap<String, Object>>();


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
        SimpleAdapter adapter = new SimpleAdapter(getActivity(), getData(), R.layout.me_about_text,
                new String[]{"news_title", "news_from", "news_judge", "news_time"}, new int[]{R.id.news_title1,
                R.id.news_from1, R.id.news_judge1, R.id.news_time1});
        listView.setAdapter(adapter);

        listView.setAdapter(adapter);

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
//                        System.out.println(response);
                        try {
                            JSONArray array = response.getJSONArray("data");

                            for (int i = 0; i < array.length(); i++) {
                                stringBuilder.append(array.getJSONObject(i)
                                        .getString("title"));

                                HashMap<String, Object> tempHashMap = new HashMap<String, Object>();
                                tempHashMap.put("news_title", array.getJSONObject(i)
                                        .getString("title"));
                                tempHashMap.put("news_from", array.getJSONObject(i)
                                        .getString("source"));
                                tempHashMap.put("news_judge", array.getJSONObject(i)
                                        .getString("comments_count")+"评论数量");
                                tempHashMap.put("news_time", array.getJSONObject(i)
                                        .getString("datetime"));

                                arrayList.add(tempHashMap);
                            }

//                            System.out.print(stringBuilder.toString());
//                            System.out.print(arrayList.toString());
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
     * getData
     */
    private ArrayList<HashMap<String, Object>> getData() {

        return arrayList;

    }

}

