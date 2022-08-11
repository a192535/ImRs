package com.example.myim.IM_Activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myim.Config;
import com.example.myim.IM_Activity.ChatLyout;
import com.example.myim.IM_Activity.Contact;
import com.example.myim.MainActivity;
import com.example.myim.R;
import com.example.myim.Utils;
import com.tencent.imsdk.v2.V2TIMSDKConfig;
import com.tencent.qcloud.tim.uikit.TUIKit;
import com.tencent.qcloud.tim.uikit.component.TitleBarLayout;
import com.tencent.qcloud.tim.uikit.config.CustomFaceConfig;
import com.tencent.qcloud.tim.uikit.config.GeneralConfig;
import com.tencent.qcloud.tim.uikit.config.TUIKitConfigs;
import com.tencent.qcloud.tim.uikit.modules.conversation.ConversationLayout;
import com.tencent.qcloud.tim.uikit.modules.conversation.ConversationListLayout;

public class Conversation extends AppCompatActivity {
    public static int a;
    public static String b;
    public static boolean fromwho;
    public static final int SDKAPPID = Config.SDKAPPID; // 您的 SDKAppID
    private Context context;

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conversation);
        context= Conversation.this;

        Button btnMsg = findViewById(R.id.btnMsg);
        btnMsg.setBackground(getDrawable(R.drawable.cvs_onclick));

        /**初始化*/
        //button初始化
        Button btnContact = findViewById(Config.btnContact);
        Button btnMy = findViewById(Config.btnTools);

        TUIKitConfigs configs = TUIKit.getConfigs();
        configs.setSdkConfig(new V2TIMSDKConfig());
        configs.setCustomFaceConfig(new CustomFaceConfig());
        configs.setGeneralConfig(new GeneralConfig());
        TUIKit.init(this, SDKAPPID, configs);
        ConversationLayout conversationLayout = findViewById(R.id.conversation_layout);


        /**聊天列表逻辑*/
        // 初始化聊天面板
        conversationLayout.initDefault();
        ConversationListLayout listLayout = conversationLayout.getConversationList();
        ConversationListLayout.OnItemClickListener listener = (view, position, messageInfo) -> {
//            a = messageInfo.getTitle().length();
//            b = messageInfo.getTitle();
//            fromwho = true;
            startActivity(new Intent(this, ChatLyout.class));
        };
        listLayout.setOnItemClickListener(listener);
        // 获取 TitleBarLayout
//        TitleBarLayout titleBarLayout = conversationLayout.findViewById(R.id.conversation_title);
        TitleBarLayout titleBarLayout = conversationLayout.getTitleBar();
        /**顶部菜单*/
        titleBarLayout.setOnRightClickListener(v -> {
            initPopWindow(v);
        });
        titleBarLayout.setTitle("会话", TitleBarLayout.POSITION.MIDDLE);
        titleBarLayout.setBackgroundColor(Color.parseColor("#00BFFF"));

        /**底部菜单*/
        btnContact.setOnClickListener(v -> {
            //开始新的activity
//            startActivity(new Intent(this, Contact.class));
            Utils.loadActivity(this,this,Contact.class);

        });
        btnMy.setOnClickListener(v -> {
//            startActivity(new Intent(this, Config.c));
            Utils.loadActivity(this,this,Person.class);

        });
    }

    private void initPopWindow(View v) {
        View view = LayoutInflater.from(context).inflate(R.layout.pop_cvst, null, false);
        TextView live_pop = (TextView) view.findViewById(R.id.pop_live);
        //1.构造一个PopupWindow，参数依次是加载的View，宽高
        final PopupWindow popWindow = new PopupWindow(view,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popWindow.setAnimationStyle(R.anim.anim_pop);  //设置加载动画

        //这些为了点击非PopupWindow区域，PopupWindow会消失的，如果没有下面的
        //代码的话，你会发现，当你把PopupWindow显示出来了，无论你按多少次后退键
        //PopupWindow并不会关闭，而且退不出程序，加上下述代码可以解决这个问题
        popWindow.setTouchable(true);
        popWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });
        popWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));    //要为popWindow设置一个背景才有效


        //设置popupWindow显示的位置，参数依次是参照View，x轴的偏移量，y轴的偏移量
        popWindow.showAsDropDown(v, 100, 0);

        //设置popupWindow里的按钮的事件
        live_pop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context,MLive.class));
            }
        });
    }
}