package com.example.cadastroelogin.util;

import com.google.firebase.auth.FirebaseAuth;

public class firebaseconfig {

    private static FirebaseAuth auth;

    public static  FirebaseAuth firebaseAuth (){
        if(auth ==null)
            auth= FirebaseAuth.getInstance();
        return auth;
    }
}
