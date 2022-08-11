package com.example.myim;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NewOkHttp {
    public static NewOkHttp getInstance(){

        return new NewOkHttp();
    }
    public Handler mHandler;

    public void sendHttp(String url, String contentType, String body, Callback callback){


        if (contentType == null) {
            contentType = "application/json";
        }
        MediaType mediaType = MediaType.parse(contentType + "; charset=utf-8");
        OkHttpClient okHttpClient = new OkHttpClient();
        Request.Builder requestBuilder = new Request.Builder();
        requestBuilder.url(url).post(RequestBody.create(mediaType,body));
        Request request = requestBuilder.build();
//        StringBuilder responseInfo = new StringBuilder();
//        Callback callback = new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                Log.d(getClass().getName(), "onFailure: " + e.getMessage());
//            }
//
//            @SuppressLint("HandlerLeak")
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                StringBuilder responseInfo = new StringBuilder();
//
//                String code = response.protocol() + " " +response.code() + " " + response.message();
//                responseInfo.append(code);
//                Headers headers = response.headers();
//                for (int i = 0; i < headers.size(); i++) {
//                    String header = headers.name(i) + ":" + headers.value(i);
//                    responseInfo.append(header);
//                }
//                String rspBody = "onResponse: " + response.body().string();
//                responseInfo.append(rspBody);
//                String rspString = responseInfo.toString();
//                Log.d(getClass().getName() ,rspString);
//
//                Looper.prepare();
//                mHandler = new Handler(){
//                    @Override
//                    public void handleMessage(Message msg)
//                    {
//                        if(msg.what == 0x123)
//                        {
//                            textView.setText("123");
//                        }
//                    }
//
//                };
//                Looper.loop();
//
//            }
//        };

        okHttpClient.newCall(request).enqueue(callback);
    }
}
