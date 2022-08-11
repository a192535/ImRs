package com.example.myim.IM_Activity.contact;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myim.Utils;
import com.example.myim.R;

public class SearchFriend extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_friend);

        EditText editText = findViewById(R.id.search_edit);
        String a = Utils.getInput(editText);

        String[] strs = {""};
        ListView friendList = findViewById(R.id.listview);
        ListAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,strs);
        friendList.setAdapter(adapter);

    }
}
