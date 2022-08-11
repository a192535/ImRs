package com.example.myim.IM_Activity;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myim.Config;
import com.example.myim.Utils;
import com.example.myim.R;
import com.tencent.imsdk.v2.V2TIMConversation;
import com.tencent.imsdk.v2.V2TIMSDKConfig;
import com.tencent.qcloud.tim.uikit.TUIKit;
import com.tencent.qcloud.tim.uikit.component.TitleBarLayout;
import com.tencent.qcloud.tim.uikit.config.CustomFaceConfig;
import com.tencent.qcloud.tim.uikit.config.GeneralConfig;
import com.tencent.qcloud.tim.uikit.config.TUIKitConfigs;
import com.tencent.qcloud.tim.uikit.modules.chat.base.ChatInfo;

public class ChatLyout extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chatlyout);

        String a = null;
        //初始化
        TUIKitConfigs configs = TUIKit.getConfigs();
        configs.setSdkConfig(new V2TIMSDKConfig());
        configs.setCustomFaceConfig(new CustomFaceConfig());
        configs.setGeneralConfig(new GeneralConfig());
        TUIKit.init(this, Config.SDKAPPID, configs);
        // 从布局文件中获取聊天面板
        com.tencent.qcloud.tim.uikit.modules.chat.ChatLayout chatLayout = findViewById(R.id.chat_layout);

        TitleBarLayout title = chatLayout.getTitleBar();
        title.setBackgroundColor(Color.parseColor("#00BFFF"));
        chatLayout.initDefault();
        ChatInfo chatInfo = new ChatInfo();
        //利用fromwho判断点击来自哪个activity
        if (Conversation.fromwho) {
            a = Conversation.b;
            Conversation.fromwho = false;
            Utils.Toast(this, "from会话");
        } else if (Contact.fromwho){
            a = Contact.b;
            Contact.fromwho = false;
            Utils.Toast(this,"from通讯录");
        }
        else {
            Utils.Toast(this,"以上都不匹配");
        }
        chatInfo.setId(a);//必要参数
        chatInfo.setChatName(a);//必要参数
        chatInfo.setType(V2TIMConversation.V2TIM_C2C);
        chatLayout.setChatInfo(chatInfo);


    }
}
