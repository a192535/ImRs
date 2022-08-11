package com.example.myim.live;

import android.os.Bundle;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myim.R;
import com.example.myim.Utils;
import com.tencent.live2.V2TXLiveDef;
import com.tencent.live2.V2TXLivePlayer;
import com.tencent.live2.impl.V2TXLivePlayerImpl;
import com.tencent.rtmp.ui.TXCloudVideoView;

public class Play extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_layout);

        String flvUrl = "http://play12.vveev.com/live/123.flv";

        TXCloudVideoView mView = findViewById(R.id.video_view);
        V2TXLivePlayer mLivePlayer = new V2TXLivePlayerImpl(this);
        mLivePlayer.setRenderView(mView);

        Button btnStartPlay = findViewById(R.id.btnStartPlay);
        Button btnStopPlay = findViewById(R.id.btnStopPlay);
        Button btnPause = findViewById(R.id.btnPause);
        Button btn0 = findViewById(R.id.btn0);
        Button btn90 = findViewById(R.id.btn90);
        Button btn180 = findViewById(R.id.btn180);
        Button btn270 = findViewById(R.id.btn270);
        Button btnFill = findViewById(R.id.btnFill);
        Button btnFit = findViewById(R.id.btnFit);

        btnStartPlay.setOnClickListener(v -> {
            mLivePlayer.startPlay(flvUrl);
            Utils.Toast(this,"开始播放");
        });
        btnStopPlay.setOnClickListener(v -> {
            mLivePlayer.stopPlay();
            Utils.Toast(this,"停止播放");
        });
        btnPause.setOnClickListener(v -> {
            mLivePlayer.pauseAudio();
            mLivePlayer.pauseVideo();
        });
        btn0.setOnClickListener(v -> mLivePlayer.setRenderRotation(V2TXLiveDef.V2TXLiveRotation.V2TXLiveRotation0));
        btn90.setOnClickListener(v-> mLivePlayer.setRenderRotation(V2TXLiveDef.V2TXLiveRotation.V2TXLiveRotation90));
        btn180.setOnClickListener(v-> mLivePlayer.setRenderRotation(V2TXLiveDef.V2TXLiveRotation.V2TXLiveRotation180));
        btn270.setOnClickListener(v-> mLivePlayer.setRenderRotation(V2TXLiveDef.V2TXLiveRotation.V2TXLiveRotation270));
        btnFill.setOnClickListener(v-> mLivePlayer.setRenderFillMode(V2TXLiveDef.V2TXLiveFillMode.V2TXLiveFillModeFill));
        btnFit.setOnClickListener(v-> mLivePlayer.setRenderFillMode(V2TXLiveDef.V2TXLiveFillMode.V2TXLiveFillModeFit));
    }
}