package com.example.myim;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;

import com.tencent.qcloud.tim.uikit.TUIKit;
import com.tencent.qcloud.tim.uikit.base.IUIKitCallBack;

class ATask{
    String usersig;
    String userid;
    Cursor cursor;
    SQLiteDatabase db;
    IUIKitCallBack callback;
    public void setParaments(SQLiteDatabase db, String userid, Cursor cursor, IUIKitCallBack callback) {
        this.userid = userid;
        this.db = db;
        this.cursor = cursor;
        this.callback = callback;
        Log.e("1234",userid+usersig);
    }
    public MyAsyncTask getMyAsyncTask(){
        return new MyAsyncTask();
    }

    class MyAsyncTask extends AsyncTask<Void, Void, String> {

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected String doInBackground(Void... voids) {
        usersig = Utils.sendHttpRequest("http://javaserver.vveev.com:8080/sig/UserSigGet", userid);
        return usersig;

    }

    @Override
    protected void onPostExecute(String s) {
        usersig = s;
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
        TUIKit.login(userid, usersig, callback);

        Log.e("123456",userid+usersig);

    }
}
}