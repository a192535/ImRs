package com.example.myim.no_use;


import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tencent.qcloud.tim.uikit.component.TitleBarLayout;
import com.tencent.qcloud.tim.uikit.modules.chat.interfaces.IMessageLayout;
import com.tencent.qcloud.tim.uikit.modules.chat.layout.message.MessageLayout;
import com.tencent.qcloud.tim.uikit.modules.conversation.ConversationLayout;
import com.tencent.qcloud.tim.uikit.modules.conversation.ConversationListAdapter;
import com.tencent.qcloud.tim.uikit.modules.conversation.ConversationListLayout;
import com.tencent.qcloud.tim.uikit.modules.conversation.base.ConversationInfo;
import com.tencent.qcloud.tim.uikit.modules.conversation.interfaces.IConversationAdapter;
import com.tencent.qcloud.tim.uikit.modules.conversation.interfaces.IConversationProvider;
import com.tencent.qcloud.tim.uikit.modules.message.MessageInfo;

import static androidx.core.content.ContextCompat.startActivity;

public class CustConversation {

    public static void customizeConversation(final ConversationLayout layout) {

        ConversationListLayout listLayout = (ConversationListLayout) layout.getConversationList();

        listLayout.setItemTopTextSize(16); // 设置adapter item中top文字大小
        listLayout.setItemBottomTextSize(12);// 设置adapter item中bottom文字大小
        listLayout.setItemDateTextSize(10);// 设置adapter item中timeline文字大小
        listLayout.setItemAvatarRadius(5);// 设置adapter item头像圆角大小
        listLayout.disableItemUnreadDot(false);// 设置adapter item是否不显示未读红点，默认显示
        listLayout.getListLayout();
        /*
        ConversationListLayout.OnItemClickListener listener = (view, position, messageInfo) -> {
           startActivity(new Intent(Conversation.this, ChatLyout.class));
        };
        listLayout.setOnItemClickListener(listener);
        */

        // 动态插入，删除Item，包括自定义会话
        final ConversationInfo customInfo = new ConversationInfo();
        customInfo.setType(ConversationInfo.TYPE_CUSTOM);
        customInfo.setConversationId("8989");
        customInfo.setId("12345");
        customInfo.setGroup(false);
        customInfo.setTitle("乔丹风行8代跑鞋 风随我动！");
        customInfo.getTitle();
        customInfo.getId();
        customInfo.getConversationId();


//        customInfo.setIconUrl("https://img1.gtimg.com/ninja/2/2019/03/ninja155375585738456.jpg");

        //实例化ConversationAdapter
        ConversationListAdapter conversationListAdapter = new ConversationListAdapter();
        conversationListAdapter.addItem(0,customInfo);
        conversationListAdapter.getItem(0);

        listLayout.getAdapter();

//
//
//        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                layout.addConversationInfo(0, customInfo);
//            }
//        }, 3000);

//        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                layout.removeConversationInfo(0);
//            }
//        }, 5000);
    }

}