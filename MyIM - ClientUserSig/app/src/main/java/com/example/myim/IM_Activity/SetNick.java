package com.example.myim.IM_Activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myim.R;
import com.example.myim.StaticObj;
import com.example.myim.Utils;
import com.tencent.imsdk.TIMCallBack;
import com.tencent.imsdk.TIMUserProfile;

import java.util.HashMap;

public class SetNick extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_nick);

        EditText nick_edit = findViewById(R.id.nick_edit);
        Button save_nick = findViewById(R.id.save_nick);

        save_nick.setOnClickListener(v->{
            /** 设定昵称 */
            TIMCallBack callBack = new TIMCallBack(){
                @Override
                public void onError(int i, String s) {

                }

                @Override
                public void onSuccess() {

                }
            };
            HashMap<String,Object> hashMap = new HashMap<>();
            hashMap.put(TIMUserProfile.TIM_PROFILE_TYPE_KEY_NICK,nick_edit.getText().toString());
            StaticObj.friendshipManager.modifySelfProfile(hashMap,callBack);
            Utils.Toast(getApplicationContext(),"保存成功！");
            /** 通知Person更新UI */
            Person.mHandler.sendEmptyMessage(0x123);
            finish();
        });

    }
}
