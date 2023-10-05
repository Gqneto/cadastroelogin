package com.example.cadastroelogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class CadastroActivity extends AppCompatActivity {

    Usuario usuario;
    FirebaseAuth autenticacao;

    EditText campoNome, campoEmail, campoSenha;
    Button btnCadastro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializar();
    }

    private void inicializar() {

        campoNome = findViewById(R.id.txt_nome);
        campoEmail = findViewById(R.id.txt_email);
        campoSenha = findViewById(R.id.txt_senha);
        btnCadastro = findViewById(R.id.btn_cadastro);
    }

    public void validarCadastro(View view) {

        String nome = campoNome.getText().toString();
        String email = campoEmail.getText().toString();
        String senha = campoSenha.getText().toString();

        if (!nome.isEmpty()) {
            if (!email.isEmpty()) {

                if (!senha.isEmpty()) {
                    usuario = new Usuario();

                    usuario.setNome(nome);
                    usuario.setEmail(email);
                    usuario.setSenha(senha);
                    //Execução do cadastro
                    cadastrarUsuario();

                } else {
                    Toast.makeText(this, "Preencha o campo Senha", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(this, "Preencha o  campo Email", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, "Preencha o  campo nome", Toast.LENGTH_SHORT).show();
        }

    }

    private void cadastrarUsuario() {
        autenticacao = firebaseconfig.firebaseAuth();
        autenticacao.createUserWithEmailAndPassword(
                usuario.getEmail(), usuario.getSenha()

        ).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(CadastroActivity.this, "Cadastro completado com sucesso", Toast.LENGTH_SHORT).show();
                } else {
                    String excecao = "";
                    try {
                        throw task.getException();

                    } catch (FirebaseAuthWeakPasswordException e) {
                        excecao = "Digite uma sehnha mais FORTE!";
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        excecao = "Digite um E-mail válido";
                    } catch (FirebaseAuthUserCollisionException e) {
                        excecao = "Esta conta já existe";
                    } catch (Exception e) {
                        excecao = "Erro ao cadastrar usuário + " + e.getMessage();
                        e.printStackTrace();
                    }
                    Toast.makeText(CadastroActivity.this, excecao, Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}


    
   
