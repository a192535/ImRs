//package com.example.myim.no_use;
//
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.os.Bundle;
//import android.widget.Button;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AlertDialog;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.example.myim.CustMethod;
//import com.example.myim.IM_Activity.Contact;
//import com.example.myim.IM_Activity.Conversation;
//import com.example.myim.MainActivity;
//import com.example.myim.live.Push;
//import com.example.myim.R;
//import com.example.myim.live.Play;
//import com.tencent.qcloud.tim.uikit.TUIKit;
//import com.tencent.qcloud.tim.uikit.base.IUIKitCallBack;
//
///**
// *底部菜单“工具”图标点击后的activity
// */
//public class Settool extends AppCompatActivity {
//
//        @Override
//        protected void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            setContentView(R.layout.settool);
//
//            Button btnMsg = findViewById(R.id.btnMsg);
//            Button btnContact = findViewById(R.id.btnContact);
//            Button butPush = findViewById(R.id.butPush);
//            Button btnPlay = findViewById(R.id.btnPlay);
//            Button btnLoginout = findViewById(R.id.btnLoginout);
//
//
//
//            btnPlay.setOnClickListener(v -> {if (CustMethod.ClickTime()) {
//                startActivity(new Intent(this, Play.class));
//            }});
//            butPush.setOnClickListener(v -> {if (CustMethod.ClickTime()){
//                startActivity(new Intent(this, Push.class));
//            }});
//            btnLoginout.setOnClickListener(v -> {
//                AlertDialog alert;
//                AlertDialog.Builder builder = new AlertDialog.Builder(this);
//                alert = builder
//                        .setTitle("确定退出登录？")
//                        //.setMessage("这是一个最普通的AlertDialog,\n带有三个按钮，分别是取消，中立和确定")
//                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                Toast.makeText(getApplicationContext(), "已退出", Toast.LENGTH_SHORT).show();
//                                IUIKitCallBack callback = new IUIKitCallBack() {
//                                    @Override
//                                    public void onSuccess(Object data) {
//
//                                    }
//
//                                    @Override
//                                    public void onError(String module, int errCode, String errMsg) {
//
//                                    }
//
//                                };
//                                TUIKit.logout(callback);
//                                startActivity(new Intent(getApplicationContext(),  MainActivity.class));
//                                overridePendingTransition(0,0);
//                                finish();
//                            }
//                        })
//                        .setNegativeButton("取消", (dialog, which) -> {
//
//                        })
//                        .create();
//                        alert.show();
//            });
//
//
//
//
//
//            /**======底部菜单按钮==============================================*/
//            btnMsg.setOnClickListener(v -> {if (CustMethod.ClickTime()){
//                startActivity(new Intent(this, Conversation.class));
//                overridePendingTransition(0,0);
//                finish();}});
//
//            btnContact.setOnClickListener(v -> {if (CustMethod.ClickTime()){
//                startActivity(new Intent(this, Contact.class));
//                overridePendingTransition(0,0);
//                finish();}});
//        }
//
//}
