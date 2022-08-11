package com.example.myim.IM_Activity.person;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myim.Utils;
import com.example.myim.IM.FriendShip;
import com.example.myim.R;
import com.example.myim.SQL;
import com.tencent.imsdk.v2.V2TIMFriendApplication;
import com.tencent.qcloud.tim.uikit.modules.contact.ContactItemBean;

import java.util.ArrayList;
import java.util.List;


public class AddFriends extends AppCompatActivity {

    SQLiteDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_friends);

        /**
         * 监听
         * addBtn ：添加好友
         */
        EditText editId = findViewById(R.id.ID);
        Button addBtn = findViewById(R.id.delete_btn);
        FriendShip friendShip = new FriendShip();
        addBtn.setOnClickListener(v -> {
            String id = editId.getEditableText().toString();
            db = SQL.getWriteDB(this);
            db.beginTransaction();
            try {
                ContentValues values1 = new ContentValues();
                values1.put("friendId", id);
                db.insert("friends", null, values1);
                db.setTransactionSuccessful();
            }finally {
                db.endTransaction();
            }
            friendShip.addFriend(id);
            Utils.Toast(this,"添加成功"+id);
        });
        Button btn1 = findViewById(R.id.add_btn1);
        btn1.setOnClickListener(v->{
            List<V2TIMFriendApplication> applyList = friendShip.getApplyList();
            for (V2TIMFriendApplication friendApplication : applyList){
                Log.e("1234好友申请",friendApplication.getUserID());
            }
        });
    }
    /**
     * 调用此方法以获取ContactItemBean 项目
     */
    public static ContactItemBean getItem(String id) {
        ContactItemBean friendItem = new ContactItemBean();
        friendItem.setId(id);
        friendItem.setFriend(true);
        return friendItem;
    }

    /**
     * 此接口返回friendid数组
     */
    public static ArrayList<String> getId(Context context){
        Cursor cursor = SQL.getCursor(context);

        ArrayList<String> friendsId = new ArrayList<>();
        String friendId = null;
        if (cursor.moveToFirst()) {
            do{
                friendId = cursor.getString(cursor.getColumnIndex("friendId"));
                friendsId.add(friendId);
            }while(cursor.moveToNext());
        }
        return friendsId;
    }

//    /**
//     * 静态封装，直接调用，不需要再new对象
//     */
//    public static ArrayList<String> getFriendId(Context context){
//        AddFriends addFriends = new AddFriends();
//        return addFriends.getId(context);
//    }


}