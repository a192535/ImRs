package com.example.myim.live;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import com.example.myim.Config;
import com.example.myim.Utils;
import com.example.myim.R;
import com.tencent.liteav.device.TXDeviceManager;
import com.tencent.live2.V2TXLiveDef;
import com.tencent.live2.V2TXLivePusher;
import com.tencent.live2.V2TXLivePusherObserver;
import com.tencent.live2.impl.V2TXLivePusherImpl;
import com.tencent.rtmp.TXLiveBase;
import com.tencent.rtmp.ui.TXCloudVideoView;
import static android.content.ContentValues.TAG;

public class Push extends AppCompatActivity {
    //类变量，服务于下方镜像切换的判断逻辑
    boolean a =true;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.push_layout);
        //定义常量
        final String licenceURL = Config.licenceURL;
        final String licenceKey = Config.licenceKey;
        final String rtmpURL = "rtmp://146036.livepush.myqcloud.com/live/123?txSecret=af325f1111b5c24e5be7b9769df3c4c0&txTime=6104E901";
        //初始化云直播，初始化所需对象
        TXLiveBase.getInstance().setLicence(this, licenceURL, licenceKey);
        V2TXLivePusher mLivePusher = new V2TXLivePusherImpl(this, V2TXLiveDef.V2TXLiveMode.TXLiveMode_RTMP); //指定对应的直播协议为 RTMP
        V2TXLivePusherObserver listener = new V2TXLivePusherObserver(){};
        TXDeviceManager devManager = mLivePusher.getDeviceManager();
        //启动本地摄像头预览
        final TXCloudVideoView mPusherView = findViewById(R.id.pusher_tx_cloud_view);
        final int ret = mLivePusher.startPush(rtmpURL.trim());

        mLivePusher.setRenderView(mPusherView);
        //初始化调试按钮
        Button btnStartCamera = findViewById(R.id.btnStartCamera);
        Button btnStopCamera = findViewById(R.id.btnStopCamera);
        Button btnStartPush = findViewById(R.id.btnStartPush);
        Button btnStopPush = findViewById(R.id.btnStopPush);
        Button btnMir = findViewById(R.id.btnMir);
        Button btnCamera = findViewById(R.id.btnCamera);
        //设置按钮监听事件
        btnStartCamera.setOnClickListener(v -> mLivePusher.startCamera(true));
        btnStopCamera.setOnClickListener(v -> mLivePusher.stopCamera());
        btnStartPush.setOnClickListener(v -> {
            //显示Toast信息
            Utils.Toast(this,"开始推流");
            mLivePusher.startPush(rtmpURL);
            mLivePusher.setObserver(listener);
            if (ret == -5) {
                Log.e(TAG, "startRTMPPush: license 校验失败");
            }
        });
        btnStopPush.setOnClickListener(v -> mLivePusher.stopPush());
        btnMir.setOnClickListener(v -> {
            if(a) {
                mLivePusher.setRenderMirror(V2TXLiveDef.V2TXLiveMirrorType.V2TXLiveMirrorTypeDisable);
                a = false;
            }
            else{
                mLivePusher.setRenderMirror(V2TXLiveDef.V2TXLiveMirrorType.V2TXLiveMirrorTypeAuto);
                a=true;
            }
        });
        btnCamera.setOnClickListener(v -> devManager.switchCamera(!devManager.isFrontCamera()));



    }
}