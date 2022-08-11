package com.example.myim.IM_Activity.contact;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myim.R;
import com.tencent.qcloud.tim.uikit.base.ITitleBarLayout;
import com.tencent.qcloud.tim.uikit.component.TitleBarLayout;
import com.tencent.qcloud.tim.uikit.modules.contact.ContactLayout;

public class NewFriends extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact);

        ContactLayout contactLayout = findViewById(R.id.contact_layout);
        TitleBarLayout titleBarLayout = contactLayout.getTitleBar();
        titleBarLayout.setBackgroundColor(Color.parseColor("#00BFFF"));
        titleBarLayout.setTitle("新的联系人", ITitleBarLayout.POSITION.MIDDLE);



    }
}
