package com.example.fiz.myapplication;

/**
 * Created by Fiz on 16/3/21.
 */

import android.annotation.SuppressLint;
import android.app.LauncherActivity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.app.Activity;

import android.widget.SimpleAdapter;


import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import cz.msebera.android.httpclient.Header;

@SuppressLint("ValidFragment")
public class Home extends Fragment {
    private String textString;
    private static final String Tag = "MainActivity";

    private ArrayList<HashMap<String, Object>> arrayList = new ArrayList<HashMap<String, Object>>();

    public Home(String textString) {
        this.textString = textString;
    }

    public static Home newInstance(String textString) {
        Home mFragment = new Home(textString);
        return mFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_page, container, false);
        final ListView listView = (ListView) view.findViewById(R.id.listView);
        try {
            getPublicTimeline();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        SimpleAdapter adapter = new SimpleAdapter(getActivity(), getData(), R.layout.home_page_text,
                new String[]{"news_title", "news_from", "news_judge","news_time"}, new int[]{R.id.news_title, R.id.news_from, R.id.news_judge,R.id.news_time});
        listView.setAdapter(adapter);
        /**
         *事件监听
         */
        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if ( ! arrayList.get(position).equals(null)){
                    Log.d(Tag, "THIS IS A JOKKE AA  UN FUNC  M ");

                }else {
                    Log.d(Tag, "THIS IS A error are you undenstard AA  UN FUNC  M ");

                }

            }
        });


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

                                HashMap<String, Object> tempHashMap = new HashMap<String, Object>();
                                tempHashMap.put("news_title", array.getJSONObject(i)
                                        .getString("title"));
                                tempHashMap.put("news_from", array.getJSONObject(i)
                                        .getString("source"));
                                tempHashMap.put("news_judge", array.getJSONObject(i)
                                        .getString("comments_count") + "评论数量");
                                tempHashMap.put("news_time", array.getJSONObject(i)
                                        .getString("datetime"));

                                arrayList.add(tempHashMap);
                            }
                            System.out.print(arrayList.toString());
                            System.out.print("状态码" + statusCode);
                        } catch (Exception e) {
                            e.printStackTrace();
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
    /**
     * 捕获单击事件
     */


}

