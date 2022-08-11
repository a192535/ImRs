//package com.example.myim;
//
//import android.os.Bundle;
//import android.os.PersistableBundle;
//import android.util.Log;
//import android.widget.Toast;
//
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.tencent.liteav.TXLiteAVCode;
//import com.tencent.trtc.TRTCCloud;
//import com.tencent.trtc.TRTCCloudDef;
//import com.tencent.trtc.TRTCCloudListener;
//
//import static com.tencent.trtc.TRTCCloudDef.TRTC_APP_SCENE_VIDEOCALL;
//
//public class TRTCMain extends AppCompatActivity{
//
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
//        super.onCreate(savedInstanceState, persistentState);
//
//        TRTCCloud mTRTCCloud = TRTCCloud.sharedInstance(getApplicationContext());
//        mTRTCCloud.setListener(new TRTCCloudListener(){
//            private static final String TAG = "onError";
//
//            // 错误通知监听，错误通知意味着 SDK 不能继续运行
//            @Override
//            public void onError(int errCode, String errMsg, Bundle extraInfo) {
//                Log.d(TAG, "sdk callback onError");
//                if (getApplicationContext() != null) {
//                    Toast.makeText(getApplicationContext(), "onError: " + errMsg + "[" + errCode+ "]" , Toast.LENGTH_SHORT).show();
//                    if (errCode == TXLiteAVCode.ERR_ROOM_ENTER_FAIL) {
//                        mTRTCCloud.exitRoom();
//                    }
//                }
//            }
//        });
//
//        TRTCCloudDef.TRTCParams params = new TRTCCloudDef.TRTCParams();
//        params.sdkAppId = Config.SDKAPPID;
//        params.userId = Config.userid;
//        params.roomId = 908;
//        params.userSig = Config.usersig;
//
//        mTRTCCloud.enterRoom(params,TRTC_APP_SCENE_VIDEOCALL);
//
//    }
//}