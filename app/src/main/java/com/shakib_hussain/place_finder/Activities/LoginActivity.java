package com.shakib_hussain.place_finder.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.shakib_hussain.place_finder.MainActivity;
import com.shakib_hussain.place_finder.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }


    public void signUp(View view) {

        startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
    }

    public void login(View view) {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }
}