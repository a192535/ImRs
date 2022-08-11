package com.example.myim.no_use;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.tencent.android.tpush.XGIOperateCallback;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.XGPushManager;
import java.net.HttpURLConnection;
import java.net.URL;

public class TpnsPush  extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);


    //    public TpnsPush(Provider provider, String type, String algorithm, String className, List<String> aliases, Map<String, String> attributes) {
//        super(provider, type, algorithm, className, aliases, attributes);
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
        //离线推送消息
        try {
            URL url = new URL("https://api.tpns.tencent.com/v3/push/app");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("post");
            conn.setRequestProperty("audience_type","all");

        } catch (Exception e) {
            e.printStackTrace();
        }

 }
}



