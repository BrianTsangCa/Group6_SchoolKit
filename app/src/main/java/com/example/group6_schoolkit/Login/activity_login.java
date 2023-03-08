package com.example.group6_schoolkit.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.group6_schoolkit.R;
import com.example.group6_schoolkit.taskCrud.HomeTaskCrud;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class activity_login extends AppCompatActivity {
    TextView editText_LoginPage_StudentId, editTextTextPassword2;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button btn_LoginPage=findViewById(R.id.btn_LoginPage_Login);
        Button btn_LoginPage_Register = findViewById(R.id.btn_LoginPage_Register);
        editText_LoginPage_StudentId=findViewById(R.id.editText_LoginPage_StudentId);
        editTextTextPassword2=findViewById(R.id.editTextTextPassword2);
        firebaseAuth=FirebaseAuth.getInstance();
        
//        btn_LoginPage.setOnClickListener((View v)-> {
//            String _email = editText_LoginPage_StudentId.getText().toString().trim();
//            String _password = editTextTextPassword2.getText().toString().trim();
//            firebaseAuth.signInWithEmailAndPassword(_email, _password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                @Override
//                public void onComplete(@NonNull Task<AuthResult> task) {
//                    if(task.isSuccessful()){
//                        Toast.makeText(activity_login.this, "Logged In", Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(activity_login.this, HomeTaskCrud.class);
//                        startActivity(intent);
//                    }else{
//                        Toast.makeText(activity_login.this, "not logged in", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });
//
//
//        });
        
        btn_LoginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String _email=editText_LoginPage_StudentId.getText().toString().trim();
                String _password=editTextTextPassword2.getText().toString().trim();
                firebaseAuth.signInWithEmailAndPassword(_email, _password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(activity_login.this, "Logged in", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(activity_login.this, HomeTaskCrud.class));
                        }else{
                            Toast.makeText(activity_login.this, "not logged in", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        //this will send the user to the register page if not yet registered
        btn_LoginPage_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    startActivity(new Intent(activity_login.this, RegActivity.class));
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }

            }
        });
    }
}