package com.example.myim;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.ViewDebug;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Date;

/**
 * 用于封装自定义方法的自定义类
 */
public class Utils {

    /**
     * 简化安卓自带Toast
     * @param context 上下文
     * @param str 要输出的字符串
     */
    public static void Toast(Context context, String str) {
        Toast.makeText(context.getApplicationContext(), str, Toast.LENGTH_SHORT).show();
    }

    /**
     * 简化安卓获取输入框数据
     * @param editText 输入框对象
     * @return 返回字符串类型的输入内容
     */
    public static String getInput(EditText editText){
        editText.setInputType(InputType.TYPE_CLASS_TEXT);
        return editText.getText().toString();
    }

    /**
     * @ClickTime()
     * 设置按钮的点击频率限制
     */
    public static void setButtonListener(Button button, View.OnClickListener listener){
        if (ClickTime()){
            button.setOnClickListener(listener);
        }
    }
    private static long datebuf;
    public static long datebufnow;

    /** 判断按钮点击间隔是否大于500毫秒 */
    public static boolean ClickTime() {
        Date date = new Date(System.currentTimeMillis());
        datebufnow = date.getTime();
        boolean a;
        if (a = true) {
            a = datebufnow - datebuf > 50;
            Log.v("1234 ",String.valueOf(datebuf));
            Log.v("1234",String.valueOf(datebufnow));

            Date date1 = new Date(System.currentTimeMillis());
            datebuf = date1.getTime();
        }
        Log.v("PlayDatenow",String.valueOf(a));
        return a;
    }

    /** 加载Activity */
    public static void loadActivity(Activity mThis, Context context, Class mClass){
        if(ClickTime()) {
            mThis.startActivity(new Intent(context, mClass));
            mThis.overridePendingTransition(0,0);
            mThis.finish();

        }
    }



    /** 测试未用 */
    public static Bitmap circleCrop(Bitmap source) {
        if (source == null) return null;
        int size = Math.min(source.getWidth(), source.getHeight());
        int x = (source.getWidth() - size) / 2;
        int y = (source.getHeight() - size) / 2;
        Bitmap squared = Bitmap.createBitmap(source, x, y, size, size);
        Bitmap result = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(result);
        Paint paint = new Paint();
        paint.setShader(new BitmapShader(squared, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
        paint.setAntiAlias(true);
        float r = size / 2f;
        canvas.drawCircle(r, r, r, paint);
        return result;
    }

    /** 发送post请求到后端生成签名 */
    public static String sendHttpRequest(String address,String userid) {
        return HttpTool.sendHttpRequest(address,"userId=" + userid);
    }

    /** 获取UserSig并写入SQLite */
    public static String getUserSig(SQLiteDatabase db,String userid){

        String usersig = GenerateTestUserSig.genTestUserSig(userid);
        db.beginTransaction();
        try {
            ContentValues values1 = new ContentValues();
            values1.put("userid", userid);
            values1.put("usersig", usersig);
            db.execSQL("delete from userinfo;");
            db.insert("userinfo", null, values1);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
        return usersig;
    }



}

