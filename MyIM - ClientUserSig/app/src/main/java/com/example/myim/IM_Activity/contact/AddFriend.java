package com.example.myim.IM_Activity.contact;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myim.R;

public class AddFriend extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_friend);

        EditText addEdit = findViewById(R.id.add_edit);
        Button sendAdd = findViewById(R.id.send_add);

    }
}
