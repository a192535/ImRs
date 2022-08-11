package com.example.myim.no_use;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

//class Endview {
//
//    public static class EndView {
//        private static Activity activity;
//        // 运用了单例模式中的饿汉式
//        private static final EndView endView = new EndView();
//
//        public static EndView getEndView(Activity activitys) {
//            setActivity(activitys);
//            return endView;
//        }
//
//        private <T extends View> T getView(int id) {
//            View mview = activity.findViewById(id);
//            // activity.getWindow();
//
//            return (T) mview;
//        }
//
//        // textview
//        public EndView settext(int id, String txt) {
//            TextView textView = getView(id);
//            textView.setText(txt);
//            return this;
//        }
//
//        // 获取activity
//        private static void setActivity(Activity activity) {
//            EndView.activity = activity;
//        }
//    }
//}