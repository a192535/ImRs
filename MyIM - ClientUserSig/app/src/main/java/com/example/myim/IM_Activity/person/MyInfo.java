package com.example.myim.IM_Activity.person;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myim.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MyInfo extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_info);


        String nick = null;
        String[] strs = new String[]{"头像", nick, ""};
        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_expandable_list_item_1,strs);

    }
}
