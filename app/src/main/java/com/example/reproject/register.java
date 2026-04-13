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

public class register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText email = findViewById(R.id.edtEmail1);
        EditText pass = findViewById(R.id.edtPassword1);
        EditText confirm = findViewById(R.id.edtConfirm);
        Button register = findViewById(R.id.btnRegister);
        TextView login = findViewById(R.id.relogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(register.this, login.class);
                startActivity(intent);
            }
        });



        FirebaseAuth auth = FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = email.getText().toString();
                String Pass = pass.getText().toString();
                String Confirm = confirm.getText().toString();

                if(Email.isEmpty() || Pass.isEmpty() || Confirm.isEmpty())
                {
                    Toast.makeText(register.this, "Nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!Pass.equals(Confirm)) {
                    Toast.makeText(register.this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
                    return;
                }

                auth.createUserWithEmailAndPassword(Email,Pass).addOnCompleteListener(task -> {
                    if(task.isSuccessful())
                    {
                        FirebaseUser user = auth.getCurrentUser();

                        if(user != null)
                        {
                            user.sendEmailVerification().addOnCompleteListener(task1 -> {
                                if(task1.isSuccessful())
                                {
                                    Toast.makeText(register.this, "Đã gửi xác minh email", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(register.this, verify.class));
                                }
                                else
                                {
                                    Toast.makeText(register.this, "tài khoản không tồn tại", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }

                        finish();
                    }
                    else
                    {
                        Toast.makeText(register.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}