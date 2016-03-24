package com.example.fiz.myapplication;

/**
 * Created by Fiz on 16/3/23.
 */
import cz.msebera.android.httpclient.Header;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;


public class Ayshtt {

    private void loginAsyncHttpClientPost(final String userName,
                                          final String userPass) {
        // 发送请求到服务器
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "http://172.16.237.144:8080/Login/LoginServlet";
        // 创建请求参数
        RequestParams params = new RequestParams();
        params.put("username", userName);
        params.put("userpass", userPass);
        // 执行post方法
        client.post(url, params, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers,
                                  byte[] responseBody) {
                // 设置值
//                tv_result.setText(new String(responseBody));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers,
                                  byte[] responseBody, Throwable error) {
                // 打印错误信息
                error.printStackTrace();

            }
        });
    }

}
