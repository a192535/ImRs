//package com.example.myim;
//
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
//public class MSQLiteTool extends SQLiteOpenHelper {
//    /**
//     * 版本1 创建userinfo表，添加userid,usersig字段
//     * 版本2 创建friends表，添加index，friendId,nickName字段
//     */
//
//    public MSQLiteTool(Context context) {
//        super(context, "my.db", null, 2);
//    }
//    /**
//     * 声明onCreate方法，onUpgrade方法
//     * 数据库第一次创建时被调用
//     */
//    public void onCreate(SQLiteDatabase db) {}
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

//    /**创建数据库
//     * @return field1
//     * */
//    public static SQLiteDatabase getWriteDb(Context context, String table, String field1){
//        MSQLiteTool msqLiteHelper = new MSQLiteTool(context){
//            @Override
//            public void onCreate(SQLiteDatabase db) {
//                db.execSQL("CREATE TABLE "+table+"(" + "\n" +
//                        "id INTEGER PRIMARY KEY," + "\n" +
//                        field1 +" VARCHAR(100),");}};
//        SQLiteDatabase db = msqLiteHelper.getWritableDatabase();
//        return db;
//    }
//    /**创建数据库
//     * @return field2
//     * */
//    public static SQLiteDatabase getWriteDb(Context context, String table, String field1,String field2){
//        MSQLiteTool msqLiteHelper = new MSQLiteTool(context){
//            @Override
//            public void onCreate(SQLiteDatabase db) {
//                db.execSQL("CREATE TABLE "+table+"(" + "\n" +
//                        "id INTEGER PRIMARY KEY," + "\n" +
//                        field1 +" VARCHAR(255)," + "\n" +
//                        field2 + " VARCHAR(255));");}};
//        SQLiteDatabase db = msqLiteHelper.getWritableDatabase();
//        return db;
//    }
//    /**创建数据库
//     * @return field3
//     * */
//    public static SQLiteDatabase getWriteDb(Context context, String table, String field1,String field2,String field3){
//        MSQLiteTool msqLiteHelper = new MSQLiteTool(context){
//            public void onCreate(SQLiteDatabase db) {
//                db.execSQL("CREATE TABLE "+table+"(" + "\n" +
//                        "id INTEGER PRIMARY KEY," + "\n" +
//                        field1 +" VARCHAR(255)," + "\n" +
//                        field2 +" VARCHAR(255)," + "\n" +
//                        field3 + " VARCHAR(255));");}
//
//            @Override
//            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//                db.execSQL("CREATE TABLE "+table+"(" + "\n" +
//                        "id INTEGER PRIMARY KEY," + "\n" +
//                        field1 +" VARCHAR(255)," + "\n" +
//                        field2 +" VARCHAR(255)," + "\n" +
//                        field3 + " VARCHAR(255));");
//            }
//        };
//        SQLiteDatabase db = msqLiteHelper.getWritableDatabase();
//        return db;
//        }


//    /**
//     * 获取 Cursor
//     * @param db
//     * @return
//     */
//    public static Cursor getCursor(SQLiteDatabase db){
//        Cursor cursor = db.query("friends",null,null,null,null,null,null);
//        return cursor;
//    }
//}