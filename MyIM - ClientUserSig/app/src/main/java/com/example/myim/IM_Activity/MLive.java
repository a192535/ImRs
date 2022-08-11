package com.example.myim.IM_Activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myim.Config;
import com.example.myim.R;
import com.tencent.live2.V2TXLiveDef;
import com.tencent.live2.V2TXLivePusher;
import com.tencent.live2.impl.V2TXLivePusherImpl;
import com.tencent.rtmp.TXLiveBase;
import com.tencent.rtmp.TXLivePusher;
import com.tencent.rtmp.ui.TXCloudVideoView;

public class MLive extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.live);


        final String licenceURL = Config.licenceURL;
        final String licenceKey = Config.licenceKey;
        final String rtmpURL = "rtmp://146036.livepush.myqcloud.com/live/123?txSecret=af325f1111b5c24e5be7b9769df3c4c0&txTime=6104E901";
        //初始化云直播，初始化所需对象
        TXLiveBase.getInstance().setLicence(this, licenceURL, licenceKey);
        V2TXLivePusher pusher = new V2TXLivePusherImpl(this, V2TXLiveDef.V2TXLiveMode.TXLiveMode_RTMP);
        final TXCloudVideoView mPusherView = findViewById(R.id.pusher_tx_cloud_view);
        pusher.setRenderView(mPusherView);
        Button startCamera = findViewById(R.id.startCamera);
        startCamera.setOnClickListener(v -> {
            pusher.startCamera(true);
            Log.e(getClass().getName(),"开启摄像头成功");
        });

    }
}
