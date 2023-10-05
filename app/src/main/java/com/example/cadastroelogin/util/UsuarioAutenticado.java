package com.example.cadastroelogin.util;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UsuarioAutenticado {

   public static FirebaseUser usuarioLogado(){
       FirebaseAuth usuario = firebaseconfig.firebaseAuth();
       return usuario.getCurrentUser();

   }

   }

