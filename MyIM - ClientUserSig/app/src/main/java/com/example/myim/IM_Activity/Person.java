package com.example.myim.IM_Activity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myim.IM.TestInfo;
import com.example.myim.IM_Activity.person.AddFriends;
import com.example.myim.IM_Activity.person.DeleteFriend;
import com.example.myim.IM_Activity.person.HttpToolActivity;
import com.example.myim.MainActivity;
import com.example.myim.R;
import com.example.myim.StaticObj;
import com.example.myim.Test;
import com.example.myim.Utils;
import com.example.myim.live.Play;
import com.example.myim.live.Push;
import com.tencent.imsdk.TIMUserProfile;
import com.tencent.imsdk.TIMValueCallBack;
import com.tencent.qcloud.tim.uikit.TUIKit;
import com.tencent.qcloud.tim.uikit.base.ITitleBarLayout;
import com.tencent.qcloud.tim.uikit.base.IUIKitCallBack;
import com.tencent.qcloud.tim.uikit.component.TitleBarLayout;

import java.io.IOException;

public class Person extends AppCompatActivity {

    ImageView imageFace;

    static Handler mHandler;

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my);

        Button btnMy = findViewById(R.id.btnMy);
        btnMy.setBackground(getDrawable(R.drawable.my_onclick));

        TextView textView = findViewById(R.id.nick_name);
        TIMValueCallBack<TIMUserProfile> cb = new TIMValueCallBack<TIMUserProfile>(){
            @Override
            public void onError(int i, String s) {
            }
            @SuppressLint("ResourceAsColor")
            @Override
            public void onSuccess(TIMUserProfile timUserProfile) {
                textView.setText(timUserProfile.getNickName());
                textView.setTextSize(30);
                textView.setPadding(20,60,0,0);
            }
        };
        StaticObj.friendshipManager.getSelfProfile(cb);
        /** ??????SetNick??????Nick?????????UI */
        mHandler = new Handler(){
            @Override
            public void handleMessage(Message message){
                if (message.what == 0x123){
                    StaticObj.friendshipManager.getSelfProfile(cb);
                }
            }
        };


        /**=============????????????===============*/
        TestInfo testInfo = TestInfo.getInstance();
        
        /** ???????????? */
        textView.setOnClickListener(v->{
            startActivity(new Intent(this, SetNick.class));

        });

        /**?????????????????????????????? */
        TextView signatureView = findViewById(R.id.signature);
        String signature = testInfo.getSignature();
        if(signature!=null){
            signatureView.setText(signature);
        }
        /**??????*/
        imageFace = findViewById(R.id.face_image);
        imageFace.setOnClickListener(v->{
            /*????????????*/
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            //???????????????
            startActivityForResult(intent, 2);

        });

        /**=============????????????======================*/
        //??????????????????
        String[] strs = {"??????","??????","????????????","Http??????","????????????","test"};
        //??????ArrayAdapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this,android.R.layout.simple_expandable_list_item_1,strs);
        //??????ListView?????????????????????setAdapter?????????ListView??????Adapter???????????????
        ListView list_test = (ListView) findViewById(R.id.my_list);
        list_test.setAdapter(adapter);
        list_test.setOnItemClickListener((parent, view, position, id) -> {
            switch (position){
                case 0:
                    startActivity(new Intent(this, Push.class));
                    break;
                case 1:
                    startActivity(new Intent(this, Play.class));
                    break;
                case 2:
                    startActivity(new Intent(this, AddFriends.class));
                    break;
                case 3:
                    startActivity(new Intent(this, HttpToolActivity.class));
                    break;
                case 4:
                    startActivity(new Intent(this, DeleteFriend.class));
                    break;
                case 5:
                    startActivity(new Intent(this, Test.class));
                    break;

                default:
                    break;
            }
        });

        /**============ ????????????===================*/
        Button btnLogout = findViewById(R.id.btn_logout);
        btnLogout.setOnClickListener(v -> {
            exitLogin();
        });

        /**======??????????????????==============================================*/
        Button btnMsg = findViewById(R.id.btnMsg);
        Button btnContact = findViewById(R.id.btnContact);
        btnMsg.setOnClickListener(v -> {
//            startActivity(new Intent(this, Conversation.class));
            Log.e("1234","??????");
            Utils.loadActivity(this,this,Conversation.class);

        });

        btnContact.setOnClickListener(v -> {
//            startActivity(new Intent(this, Contact.class));
            Utils.loadActivity(this,this,Contact.class);

        });

        TitleBarLayout titleBarLayout = findViewById(R.id.title);
        titleBarLayout.setLeftIcon(0);
        titleBarLayout.setTitle("??????", ITitleBarLayout.POSITION.MIDDLE);
        titleBarLayout.setBackgroundColor(Color.parseColor("#00BFFF"));

    }

    public void exitLogin(){
        AlertDialog alert;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        alert = builder
                .setTitle("?????????????????????")
                //.setMessage("????????????????????????AlertDialog,\n??????????????????????????????????????????????????????")
                .setPositiveButton("??????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "?????????", Toast.LENGTH_SHORT).show();
                        IUIKitCallBack callback = new IUIKitCallBack() {
                            @Override
                            public void onSuccess(Object data) {

                            }

                            @Override
                            public void onError(String module, int errCode, String errMsg) {

                            }

                        };
                        TUIKit.logout(callback);
                        startActivity(new Intent(getApplicationContext(),  MainActivity.class));
                        overridePendingTransition(0,0);
                        finish();
                    }
                })
                .setNegativeButton("??????", (dialog, which) -> {

                })
                .create();
        alert.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1://????????????
                //??????????????????????????????Bitmap???????????????
                Bitmap bitmap1 = (Bitmap) data.getExtras().get("data");
                imageFace.setImageBitmap(bitmap1);
                break;
            case 2://????????????
//                Bitmap bitmap1 = (Bitmap) data.getExtras().get("data");
//                imageFace.setImageBitmap(bitmap1);

                Uri path = data.getData();
                //??????????????????????????????
                try {
                    Bitmap bitmap2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), path);
                    imageFace.setImageBitmap(bitmap2);
                } catch (IOException e) {
                    e.printStackTrace();
                }
//                Glide.with(getApplicationContext()).load(path).into(imageFace);
                break;
        }
    }
}
