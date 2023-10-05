package com.example.cadastroelogin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.cadastroelogin.util.firebaseconfig;
import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        auth = firebaseconfig.firebaseAuth();
    }
    public void logout (View view) {
        try {
            auth.signOut();
            finish();
        }catch (Exception e){
            e.printStackTrace();
        }

    }


}