package com.example.cadastroelogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cadastroelogin.model.Usuario;
import com.example.cadastroelogin.util.firebaseconfig;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    EditText logSenha,logEmail;
    Button btnlogin;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        auth = firebaseconfig.firebaseAuth();
        inicializarComponetes();

    }
    public void validarLogin (View view) {
        String email = logEmail.getText().toString();
        String senha = logSenha.getText().toString();

        if (!email.isEmpty()){
            if (!senha.isEmpty()){
                
            }else {
                Toast.makeText(this, "Preencha o Campo senha", Toast.LENGTH_SHORT).show();
                Usuario usuario = new Usuario();
                usuario.setEmail(email);
                usuario.setSenha(senha);

                logar(usuario);
            }
            
        }else {
            Toast.makeText(this, "Preencha o campo E-mail", Toast.LENGTH_SHORT).show();
        }





    }

    private void logar(Usuario usuario) {
        auth.signInWithEmailAndPassword(usuario.getEmail(), usuario.getSenha()
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    openHome();

                } else {
                    String excecao = "";
                    try {
                        throw task.getException();
                    } catch (FirebaseAuthInvalidUserException e) {
                        excecao = "Usuário Inválido";
                    }catch (FirebaseAuthInvalidCredentialsException e ){
                        excecao = "Email ou senha INCORRETO!";
                    }catch (Exception e){
                        excecao = "Erro ao efetuar Login" + e.getMessage();
                                e.printStackTrace();
                    }
                    Toast.makeText(LoginActivity.this, excecao, Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

    private void openHome() {
        Intent i = new Intent(LoginActivity.this,HomeActivity.class);
        startActivity(i);
    }


    public void cadastrar(View v){
        Intent i = new Intent(this, CadastroActivity.class);
        startActivity(i);
    }
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser usuarioAuth = auth.getCurrentUser();
        if(usuarioAuth != null ){
            openHome();
        }
    }



    private void inicializarComponetes(){
        logSenha = findViewById(R.id.login_senha);
        logEmail = findViewById(R.id.login_email);
        btnlogin = findViewById(R.id.btn_login);

    }
}
