package com.example.myim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myim.IM.TestInfo;
import com.example.myim.IM_Activity.ChatLyout;
import com.example.myim.IM_Activity.Conversation;
import com.tencent.imsdk.v2.V2TIMConversation;
import com.tencent.imsdk.v2.V2TIMManager;
import com.tencent.imsdk.v2.V2TIMMessage;
import com.tencent.imsdk.v2.V2TIMSDKConfig;
import com.tencent.qcloud.tim.uikit.TUIKit;
import com.tencent.qcloud.tim.uikit.base.IMEventListener;
import com.tencent.qcloud.tim.uikit.base.IUIKitCallBack;
import com.tencent.qcloud.tim.uikit.config.CustomFaceConfig;
import com.tencent.qcloud.tim.uikit.config.GeneralConfig;
import com.tencent.qcloud.tim.uikit.config.TUIKitConfigs;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    /**
     * 声明：全局变量
     */
    public static String userid;
    public static Context context;
//    private static int errCode;
    /**
     * 基本的初始化
     */
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
            Toast.makeText(getApplicationContext(), "连接断开", Toast.LENGTH_SHORT).show();
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
    TUIKitConfigs configs = TUIKit.getConfigs();
    V2TIMManager manager = V2TIMManager.getInstance();
    IUIKitCallBack callback = new IUIKitCallBack() {

        @Override
        public void onSuccess(Object data) {
            Toast.makeText(getApplicationContext(), "登录成功 ", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this, Conversation.class));
            overridePendingTransition(0, 0);
            finish();
        }

        @Override
        public void onError(String module, int errCode, String errMsg) {
            Toast.makeText(getApplicationContext(), "登录失败 " + errCode, Toast.LENGTH_SHORT).show();
//                if (errCode==6206){
//                    MainActivity.errCode = 6206;
//
//                }
        }
    };
    int status = manager.getLoginStatus();

    /**
     * Actiivity入口
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * 定义上下文
         */
        context = MainActivity.this;
        /**
         * SQLite 对象初始化
         */
        SQL sql  = new SQL(this);
        SQLiteDatabase db = sql.getWritableDatabase();
        Cursor cursor = db.query("userinfo",null,null,null,null,null,null);
        String setuserid = null;
        String setusersig = null;
        /**
         * IM初始化
         */
        TUIKit.addIMEventListener(listener);
        configs.setSdkConfig(new V2TIMSDKConfig());
        configs.setCustomFaceConfig(new CustomFaceConfig());
        configs.setGeneralConfig(new GeneralConfig());
        TUIKit.init(this, Config.SDKAPPID, configs);

        /**
         * 读取登录过的数据
         */
        if (cursor.moveToFirst()) {
            do{
                setuserid = String.valueOf(cursor.getInt(cursor.getColumnIndex("userid")));
                setusersig = cursor.getString(cursor.getColumnIndex("usersig"));
            }while(cursor.moveToNext());
        }

        /**
         * 自动登录
         */
        if(setuserid!=null&& setusersig!=null){

            TUIKit.login(userid, GenerateTestUserSig.genTestUserSig(userid), callback);

            TUIKit.login(setuserid, setusersig, callback);
            TestInfo testInfo = TestInfo.getInstance();
            Log.e(this.getLocalClassName(), testInfo.getAllowType());
        }
        /**
         * 监听
         * btnLogin：登录按钮
         * btnRegister：注册按钮
         * edit1：登录输入框
         */
        Button btnLogin = findViewById(R.id.btnLogin);
        Button btnRegister = findViewById(R.id.btnRegister);
        EditText editText = findViewById(R.id.edit1);
        btnLogin.setOnClickListener(v -> {
            userid = editText.getText().toString();
//            ATask task = new ATask();
//            task.setParaments(db,userid,cursor,callback);
//            task.getMyAsyncTask().execute();
            TUIKit.login(userid,Utils.getUserSig(db,userid),callback);
            if(status == 1){
                skipMain();
            }
        });
        btnRegister.setOnClickListener(v -> {
            startActivity(new Intent(this, ChatLyout.class));
        });
    }
    /**
     * 定义：登录方法
     */
    protected void skipMain(){
        startActivity(new Intent(getApplicationContext(), Conversation.class));
        overridePendingTransition(0, 0);
        Utils.Toast(this,"登录成功");
        finish();
    }

}