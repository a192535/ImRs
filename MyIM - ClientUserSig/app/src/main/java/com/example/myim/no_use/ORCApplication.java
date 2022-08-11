//package com.example.myim;
//
//
//import android.os.Bundle;
//import android.os.Handler;
//import android.view.View;
//import android.widget.Button;
//import android.widget.TextView;
//
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//
//
//import android.os.AsyncTask;
//import android.widget.EditText;
//import android.widget.ProgressBar;
//import android.widget.TextView;
//
//public class ORCApplication extends AppCompatActivity implements View.OnClickListener {
//
//    EditText et_request;
//    Button btn_request;
//    TextView tv_response;
//    ProgressBar pb_request;
//    //自定义的AsyncTask类
//    class MyAsyncTask extends AsyncTask<Void, Void, String>{
//
//        @Override
//        protected void onPreExecute() {
//            //在进行http访问前给进度条可视化
//            pb_request.setVisibility(View.VISIBLE);
//        }
//
//        @Override
//        protected String doInBackground(Void... voids) {
//            return HttpTool.sendHttpRequest("http://119.28.57.117:8080/sig/UserSigGet",et_request.getText().toString());
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            //获得response后先隐藏进度条再在Textview中显示response信息
//            pb_request.setVisibility(View.GONE);
//            tv_response.setText(s);
//
//        }
//    }
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.test);
//        //各控件的初始化
//        init();
//    }
//    private void init() {
//        et_request = findViewById(R.id.edit1);
//        btn_request = findViewById(R.id.btnSender);
//        tv_response = findViewById(R.id.output);
//        pb_request = findViewById(R.id.pb_request);
//        btn_request.setOnClickListener(this);
//    }
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.btnSender :
//                new MyAsyncTask().execute();
//        }
//    }
//}