package com.shakib_hussain.place_finder.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.shakib_hussain.place_finder.R;

public class SignUpActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


    }



    public void backActivity(View view) {

        onBackPressed();
        finish();
    }
}