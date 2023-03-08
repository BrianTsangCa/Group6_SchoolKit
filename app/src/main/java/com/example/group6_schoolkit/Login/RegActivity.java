package com.example.group6_schoolkit.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.group6_schoolkit.R;
import com.example.group6_schoolkit.taskCrud.HomeTaskCrud;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegActivity extends AppCompatActivity {
    Button buttonLogin,buttonRegister;
    EditText editTextTextPersonName, editTextTextEmailAddress, editTextTextPassword;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        buttonLogin=findViewById(R.id.buttonLogin);
        buttonRegister=findViewById(R.id.buttonRegister);
        editTextTextPersonName=findViewById(R.id.editTextTextPersonName);
        editTextTextEmailAddress=findViewById(R.id.editTextTextEmailAddress);
        editTextTextPassword=findViewById(R.id.editTextTextPassword);
        firebaseAuth=FirebaseAuth.getInstance();

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegActivity.this, activity_login.class));
            }
        });

//        if(firebaseAuth.getCurrentUser()!=null){
//            startActivity(new Intent(RegActivity.this, HomeTaskCrud.class));
//            finish();
//        }

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String _email = editTextTextEmailAddress.getText().toString().trim();
                String _password = editTextTextPassword.getText().toString().trim();

                firebaseAuth.createUserWithEmailAndPassword(_email, _password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(RegActivity.this, "Register Success", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegActivity.this, activity_login.class));
                        }else{
                            Toast.makeText(RegActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }
}