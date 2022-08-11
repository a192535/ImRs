package com.example.myim.IM_Activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myim.IM_Activity.ChatLyout;
import com.example.myim.IM_Activity.Contact;
import com.example.myim.R;
import com.tencent.qcloud.tim.uikit.modules.contact.ContactItemBean;
import com.tencent.qcloud.tim.uikit.modules.contact.FriendProfileLayout;

public class FriendProfile extends AppCompatActivity {

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friendprofilelayout);

        Context context = this;
        FriendProfileLayout friendView = findViewById(R.id.conversation_layout);
        FriendProfileLayout.OnButtonClickListener listener = new FriendProfileLayout.OnButtonClickListener() {
            @Override
            public void onStartConversationClick(ContactItemBean info) {
                startActivity(new Intent(context, ChatLyout.class));
            }

            @Override
            public void onDeleteFriendClick(String id) {
//                deleteFriend(id,context);
            }
        };
        friendView.setOnButtonClickListener(listener);

        friendView.initData(Contact.contactItemBean);


    }
    public void deleteFriend(String id,Context context){
        AlertDialog alert;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        alert = builder
                .setTitle("确定删除？")
                //.setMessage("这是一个最普通的AlertDialog,\n带有三个按钮，分别是取消，中立和确定")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(getApplicationContext(), "已删除", Toast.LENGTH_SHORT).show();
//                        FriendShip ship = new FriendShip();
//                        ship.deleteFriend(id);
//                        startActivity(new Intent(getApplicationContext(),  Contact.class));
//                        overridePendingTransition(0,0);
//                        finish();
                    }
                })
                .setNegativeButton("取消", (dialog, which) -> {

                })
                .create();
        alert.show();
    }
}
