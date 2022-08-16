package com.chengfu.jitpack;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.chengfu.android.lib.ClassA;
import com.chengfu.android.lib1.MyClass1;
import com.chengfu.android.lib2.MyClass2;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ClassA classA = new ClassA();
        MyClass1 myClass1 = new MyClass1();
        MyClass2 myClass2 = new MyClass2();
    }
}