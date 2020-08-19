package com.example.todoreminder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class ForgotActivity extends AppCompatActivity {
    // forgot password activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
    }
    public void launchCancel (View view){
        Intent intent = new Intent (this, MainActivity.class);
        startActivity(intent);
    }
    public void launchSignIn (View view){
        Intent intent1 = new Intent (this, MainActivity.class);
        startActivity(intent1);
    }
}
