package com.example.reproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        EditText email = findViewById(R.id.edtEmail1);
        EditText pass = findViewById(R.id.edtPassword1);
        Button login = findViewById(R.id.btnLogin);
        TextView register = findViewById(R.id.txtRegister);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this, register.class);
                startActivity(intent);
            }
        });

        FirebaseAuth auth = FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = email.getText().toString().trim();
                String Pass = pass.getText().toString().trim();

                if(Email.isEmpty() || Pass.isEmpty())
                {
                    Toast.makeText(login.this, "Nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }

                auth.signInWithEmailAndPassword(Email, Pass).addOnCompleteListener(task -> {
                   if (task.isSuccessful())
                   {
                       FirebaseUser user = auth.getCurrentUser();
                       if (user != null && user.isEmailVerified())
                       {
                           Toast.makeText(login.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                           startActivity(new Intent(login.this, MainActivity.class));
                       }
                       else if (user != null && !user.isEmailVerified())
                       {
                           user.sendEmailVerification();
                           startActivity(new Intent(login.this, verify.class));
                       }
                       else
                       {
                           if(task.getException() != null)
                           {
                               Toast.makeText(login.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                               auth.signOut();
                           }
                       }
                   }
                   else
                   {
                       Toast.makeText(login.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                   }
                });
            }
        });
    }
}