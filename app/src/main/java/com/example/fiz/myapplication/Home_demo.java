package com.example.fiz.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONException;

import java.text.Format;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Fiz on 16/3/25.
 */
public class Home_demo  extends Fragment{
    private String textString;
    private static final String Tag = "MainActivity";

    private ArrayList<HashMap<String, Object>> arrayList = new ArrayList<HashMap<String, Object>>();

    public Home_demo(String textString) {
        this.textString = textString;
    }
    public  static  Home_demo newInstance(String textString){
        Home_demo mFragment = new Home_demo(textString);
        return  mFragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_page, container, false);
        final ListView listView = (ListView) view.findViewById(R.id.listView);

        SimpleAdapter adapter = new SimpleAdapter(getActivity(), getData(), R.layout.home_page_text,
                new String[]{"news_title", "news_from", "news_judge","news_time"}, new int[]{R.id.news_title, R.id.news_from, R.id.news_judge,R.id.news_time});
        listView.setAdapter(adapter);
        /**
         *事件监听
         */
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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

    private ArrayList<HashMap<String, Object>> getData() {
        for (int i = 0; i < 100; i++) {

            HashMap<String, Object> tempHashMap = new HashMap<String, Object>();
            tempHashMap.put("news_title", i);
            tempHashMap.put("news_from", i);
            tempHashMap.put("news_judge", i);
            tempHashMap.put("news_time", i);

            arrayList.add(tempHashMap);
        }

        return arrayList;

    }

}
