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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressLint("ValidFragment")
public class Home extends Fragment {
    private String textString;
    private static final String[] strs = new String[]{"first", "second", "third", "fourth", "fifth"};


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
     * @author chenzheng_java
     * @description 准备一些测试数据
     * @return 一个包含了数据信息的hashMap集合
     */
    private ArrayList<HashMap<String, Object>> getData(){
        ArrayList<HashMap<String, Object>> arrayList = new ArrayList<HashMap<String,Object>>();
        for(int i=0;i<100;i++){
            HashMap<String, Object> tempHashMap = new HashMap<String, Object>();
            tempHashMap.put("news_title", "新闻标题"+i);
            tempHashMap.put("news_from", "新闻来源"+i);
            tempHashMap.put("news_judge", "评论数量"+i);
            tempHashMap.put("news_time", "发布时间"+i);

            arrayList.add(tempHashMap);

        }


        return arrayList;

    }
}

