package com.example.myim.IM_Activity.person;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myim.NewOkHttp;
import com.example.myim.R;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.Response;

public class HttpToolActivity extends AppCompatActivity {

    static TextView textView;
    StringBuilder responseInfo = new StringBuilder();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.http);

        EditText http_addr = findViewById(R.id.http_addr);
        EditText http_body = findViewById(R.id.http_body);
        Button sendBtn = findViewById(R.id.send_button);
        textView = findViewById(R.id.http_info);

        sendBtn.setOnClickListener(v-> {
            String addr = http_addr.getText().toString();
            String body = http_body.getText().toString();
            Log.e("my" + getClass().getName(),addr+body);

            Message msg = new Message();
            msg.what = 0x123;
            Bundle bundle = new Bundle();

            msg.setData(bundle);

            Callback callback = new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.d(getClass().getName(), "onFailure: " + e.getMessage());
                }

                @SuppressLint("HandlerLeak")
                @Override
                public void onResponse(Call call, Response response) throws IOException {

                    String code = response.protocol() + " " +response.code() + " " + response.message();
                    responseInfo.append(code);
                    Headers headers = response.headers();
                    for (int i = 0; i < headers.size(); i++) {
                        String header = headers.name(i) + ":" + headers.value(i);
                        responseInfo.append(header);
                    }
                    String rspBody = "onResponse: " + response.body().string();
                    responseInfo.append(rspBody);
                    String rspString = responseInfo.toString();
                    Log.d(getClass().getName() ,rspString);


                    mHandler.sendEmptyMessage(0x123);

                }
            };

//            Callback callback = new Callback() {
//                @Override
//                public void onRespone(String st) {
//                    run(st);
//                }
//            };
            NewOkHttp.getInstance().sendHttp(addr,null,body,callback);
        });

    }

    @SuppressLint("HandlerLeak")
    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg)
        {
            if(msg.what == 0x123)
            {
                textView.setText(responseInfo);
            }
        }

    };

}
