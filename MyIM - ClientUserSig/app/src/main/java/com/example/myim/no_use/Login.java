package com.example.myim.no_use;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myim.Config;
import com.example.myim.IM_Activity.ChatLyout;
import com.example.myim.IM_Activity.Conversation;
import com.example.myim.R;
import com.tencent.android.tpush.XGIOperateCallback;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.XGPushManager;
import com.tencent.imsdk.v2.V2TIMConversation;
import com.tencent.imsdk.v2.V2TIMMessage;
import com.tencent.imsdk.v2.V2TIMSDKConfig;
import com.tencent.qcloud.tim.uikit.TUIKit;
import com.tencent.qcloud.tim.uikit.base.IMEventListener;
import com.tencent.qcloud.tim.uikit.base.IUIKitCallBack;
import com.tencent.qcloud.tim.uikit.config.CustomFaceConfig;
import com.tencent.qcloud.tim.uikit.config.GeneralConfig;
import com.tencent.qcloud.tim.uikit.config.TUIKitConfigs;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import static com.example.myim.no_use.GenerateTestUserSig.genTestUserSig;

public class Login extends AppCompatActivity {

    public static String userid;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //离线推送注册
        XGPushConfig.enableDebug(this, true);
        XGPushManager.registerPush(this, new XGIOperateCallback() {
            @Override
            public void onSuccess(Object data, int flag) {
                //token在设备卸载重装的时候有可能会变
                Log.d("TPush", "注册成功，设备token为：" + data);
            }
            @Override
            public void onFail(Object data, int errCode, String msg) {
                Log.d("TPush", "注册失败，错误码：" + errCode + ",错误信息：" + msg);
            }
        });

        // 配置 Config，请按需配置
        IMEventListener listener = new IMEventListener() {
            @Override
            public void onForceOffline() {
                super.onForceOffline();
            }

            @Override
            public void onUserSigExpired() {
                super.onUserSigExpired();
            }

            @Override
            public void onConnected() {
                super.onConnected();
            }

            @Override
            public void onDisconnected(int code, String desc) {
                super.onDisconnected(code, desc);
                Toast.makeText(getApplicationContext(), "连接断开" , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onWifiNeedAuth(String name) {
                super.onWifiNeedAuth(name);
            }

            @Override
            public void onRefreshConversation(List<V2TIMConversation> conversations) {
                super.onRefreshConversation(conversations);
            }

            @Override
            public void onNewMessage(V2TIMMessage v2TIMMessage) {
                super.onNewMessage(v2TIMMessage);
            }
        };

        TUIKit.addIMEventListener(listener);
        TUIKitConfigs configs = TUIKit.getConfigs();
        configs.setSdkConfig(new V2TIMSDKConfig());
        configs.setCustomFaceConfig(new CustomFaceConfig());
        configs.setGeneralConfig(new GeneralConfig());
        TUIKit.init(this, Config.SDKAPPID, configs);

        //离线推送消息
        try {
            URL url = new URL("https://api.tpns.tencent.com/v3/push/app");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("post");
            conn.setRequestProperty("audience_type","all");

        } catch (Exception e) {
            e.printStackTrace();
        }

        //实例化回调对象并登录
        IUIKitCallBack callback = new IUIKitCallBack() {
            @Override
            public void onSuccess(Object data) {
                Toast.makeText(getApplicationContext(), "登录成功 " , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(String module, int errCode, String errMsg) {
                Toast.makeText(getApplicationContext(), "登录失败 " , Toast.LENGTH_SHORT).show();
            }
        };
        //Log.e("123",genTestUserSig("123"));

        //按钮1监听
        Button btnone = (Button) findViewById(R.id.btnLogin);
        //重写点击事件的处理方法onClick()
        btnone.setOnClickListener(v -> {
            //获取输入框的userid
            EditText editText = findViewById(R.id.edit);
            editText.setInputType(InputType.TYPE_CLASS_TEXT);
            String userid = editText.getText().toString();
            this.userid = userid;
            TUIKit.login(userid, genTestUserSig(userid), callback);
            //开始新的activity
            startActivity(new Intent(this, Conversation.class));
            overridePendingTransition(0,0);
            finish();
            //Toast.makeText(getApplicationContext(), "登录成功，用户名: " + userid , Toast.LENGTH_SHORT).show();
        });

        //按钮2监听
        Button btntwo = (Button) findViewById(R.id.btnRegister);
        //重写点击事件的处理方法onClick()
        btntwo.setOnClickListener(v -> {
            //开始新的activity
            startActivity(new Intent(this, ChatLyout.class));
        });




    }

}