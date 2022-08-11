package com.example.myim.no_use;

import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public abstract class Parent extends AppCompatActivity {

    public abstract void input(Button btn);
    public void mButton(Button button){
        button.setOnClickListener(
                v -> {

                    input(button);

                }
        );

    }


}
