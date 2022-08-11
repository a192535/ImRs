package com.example.myim.IM_Activity.person;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myim.IM.FriendShip;
import com.example.myim.R;
import com.tencent.imsdk.v2.V2TIMFriendInfo;
import com.tencent.imsdk.v2.V2TIMFriendOperationResult;
import com.tencent.imsdk.v2.V2TIMManager;
import com.tencent.imsdk.v2.V2TIMValueCallback;

import java.util.ArrayList;
import java.util.List;

public class DeleteFriend extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_friends);

        EditText editId = findViewById(R.id.deleteID);
        Button addBtn = findViewById(R.id.delete_btn);
        addBtn.setOnClickListener(v -> {
            String id = editId.getEditableText().toString();
            FriendShip friendShip = new FriendShip();
            friendShip.deleteFriend(id);
        });
    }
}
