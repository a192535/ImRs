package com.example.myim.IM_Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myim.Config;
import com.example.myim.IM_Activity.contact.AddFriend;
import com.example.myim.IM_Activity.contact.BlackList;
import com.example.myim.IM_Activity.contact.MyGroup;
import com.example.myim.IM_Activity.contact.NewFriends;
import com.example.myim.R;
import com.example.myim.Utils;
import com.tencent.imsdk.v2.V2TIMSDKConfig;
import com.tencent.qcloud.tim.uikit.TUIKit;
import com.tencent.qcloud.tim.uikit.component.TitleBarLayout;
import com.tencent.qcloud.tim.uikit.config.CustomFaceConfig;
import com.tencent.qcloud.tim.uikit.config.GeneralConfig;
import com.tencent.qcloud.tim.uikit.config.TUIKitConfigs;
import com.tencent.qcloud.tim.uikit.modules.contact.ContactAdapter;
import com.tencent.qcloud.tim.uikit.modules.contact.ContactItemBean;
import com.tencent.qcloud.tim.uikit.modules.contact.ContactLayout;
import com.tencent.qcloud.tim.uikit.modules.contact.ContactListView;

public class Contact extends AppCompatActivity  {
    /**
     * 被点击后把的a值传到Chatlayout判断点击来自通讯录
     */
    public static int a ;//判断getID后的字符串长度
    /**
     * 被点击后把的b值传到Chatlayout判断点击的是谁
     */
    public static String b ;//getid数据
    public static boolean fromwho ;
    public static ContactItemBean contactItemBean;

    public static final int SDKAPPID = Config.SDKAPPID; // 您的 SDKAppID

    /**
     * Activity 入口
     */
    @SuppressLint({"ResourceType", "UseCompatLoadingForDrawables"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact);

        Button btnContact = findViewById(R.id.btnContact);
        btnContact.setBackground(getDrawable(R.drawable.ctt_onclick));

        /**
         * 初始化
         */
        Button btnMsg = findViewById(Config.btnMsg);
        Button btnMy = findViewById(Config.btnTools);
        TUIKitConfigs configs = TUIKit.getConfigs();
        configs.setSdkConfig(new V2TIMSDKConfig());
        configs.setCustomFaceConfig(new CustomFaceConfig());
        configs.setGeneralConfig(new GeneralConfig());
        TUIKit.init(this, SDKAPPID, configs);

        ContactLayout contactLayout = findViewById(R.id.contact_layout);
        ContactListView contactListView = contactLayout.getContactListView();

        /**
         * 标题区配置
         */
        TitleBarLayout titleBarLayout = contactLayout.getTitleBar();
        View.OnClickListener ICONlistener = v -> startActivity(new Intent(this, AddFriend.class));
        titleBarLayout.setOnRightClickListener(ICONlistener);
        titleBarLayout.setBackgroundColor(Color.parseColor("#00BFFF"));

            /** 联系人列表点击监听 */
        ContactListView.OnItemClickListener listener = (position, contact) -> {
            /** 获取Id，传值给ChatLayout */
            a = contact.getId().length();
            b = contact.getId();
            fromwho = true;
            /** 赋值给静态变量”contactItemBean“，此变量传值给FriendProfile类的initData() */
            contactItemBean = contact;

            if(contact.getId().equals("新的联系人")){
                startActivity(new Intent(this, NewFriends.class));
            }else if(contact.getId().equals("我的群聊")){
                startActivity(new Intent(this, MyGroup.class));
            }else if(contact.getId().equals("黑名单")){
                startActivity(new Intent(this, BlackList.class));
            }else {
                startActivity(new Intent(this, FriendProfile.class));
            }
            };
        /**
         * ===== 联系人layout配置
         */
        contactListView.setOnItemClickListener(listener);

        contactLayout.initDefault();

        /**
         * 底部菜单点击事件
         */
        btnMsg.setOnClickListener(v -> {
            Utils.loadActivity(this,this,Conversation.class);

        });
        btnMy.setOnClickListener(v -> {
            Utils.loadActivity(this,this,Person.class);

        });

    }


}
