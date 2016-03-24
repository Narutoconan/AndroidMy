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
import android.widget.TextView;

import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cz.msebera.android.httpclient.Header;

@SuppressLint("ValidFragment")
public class Home extends Fragment {
    private String textString;
    private static final String[] strs = new String[]{"first", "second", "third", "fourth", "fifth"};
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
        ListView listView = (ListView) view.findViewById(R.id.listView);
//

        /* 参数一多，有些人就头晕了。这里解说下，各个参数的意思。
         * 第一个参数 this 代表的是当前上下文，可以理解为你当前所处的activity
         * 第二个参数 getData() 一个包含了数据的List,注意这个List里存放的必须是map对象。simpleAdapter中的限制是这样的List<? extends Map<String, ?>> data
         * 第三个参数 R.layout.user 展示信息的组件
         * 第四个参数 一个string数组，数组内存放的是你存放数据的map里面的key。
         * 第五个参数：一个int数组，数组内存放的是你展示信息组件中，每个数据的具体展示位置，与第四个参数一一对应
         * */
        SimpleAdapter adapter = new SimpleAdapter(getActivity(), getData(), R.layout.home_page_text,
                new String[]{"news_title", "news_from", "news_judge","news_time"}, new int[]{R.id.news_title, R.id.news_from, R.id.news_judge,R.id.news_time});
        listView.setAdapter(adapter);


//        viewhello.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_checked, strs));

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

