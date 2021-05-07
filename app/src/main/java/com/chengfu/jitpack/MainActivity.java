package com.chengfu.jitpack;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.chengfu.one.One;
import com.chengfu.two.Two;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        One one;
        Two two;
    }
}