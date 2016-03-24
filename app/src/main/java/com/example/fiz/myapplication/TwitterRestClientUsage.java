package com.example.fiz.myapplication;

/**
 * Created by Fiz on 16/3/23.
 */


import org.json.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.support.v7.app.AppCompatActivity;


import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.*;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class TwitterRestClientUsage {}

//    public void getPublicTimeline() throws JSONException {
//        TwitterRestClient.get("recent/?source=2&count=20&category=__all__&max_behot_time=1458732835.88&utm_source=toutiao&offset=0&_=1458732835806",
//                null, new JsonHttpResponseHandler() {
//                    @Override
//                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                        // If the response is JSONObject instead of expected JSONArray
//                        System.out.println(response);
//                        try {
//                            JSONArray array = response.getJSONArray("data");
//                            List<String> list = new ArrayList<String>();
//
//                            StringBuilder stringBuilder = new StringBuilder();
//                            for (int i = 0; i < array.length(); i++) {
//                                stringBuilder.append(array.getJSONObject(i)
//                                        .getString("datetime"));
////                                list.add(array.getJSONObject(i)
////                                        .getString("abstract"));
//                            }
//
//                            System.out.print(stringBuilder.toString());
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                            System.out.print("EXCCEPT///////");
//                        }
//
//
//                    }
//
//                    @Override
//                    public void onSuccess(int statusCode, Header[] headers, JSONArray timeline) {
//                        // Pull out the first event on the public timeline
//                        try {
//                            JSONObject firstEvent = (JSONObject) timeline.get(0);
//
//                            String tweetText = firstEvent.getString("text");
//
//                            // Do something with the response
//                            System.out.println(tweetText);
//                        } catch (JSONException e) {
//                            //do something
//                        }
//
//                    }
//                });
////    }
//}