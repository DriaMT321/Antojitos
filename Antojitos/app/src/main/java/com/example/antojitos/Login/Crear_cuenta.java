package com.example.antojitos.Login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.antojitos.R;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Crear_cuenta extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cuenta);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        EditText emailEditText = findViewById(R.id.editTextemail);
        EditText passwordEditText = findViewById(R.id.ingresa_tu_contra);
        MaterialButton createAccountButton = findViewById(R.id.Login1);

        createAccountButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(Crear_cuenta.this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
                return;
            }

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(Crear_cuenta.this, task -> {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            if (user != null) {
                                saveUserData(user.getUid(), email, password);
                            }
                            Toast.makeText(Crear_cuenta.this, "Cuenta creada exitosamente", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Crear_cuenta.this, Login.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(Crear_cuenta.this, "Error al crear la cuenta: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        });
    }

    private void saveUserData(String userId, String email, String password) {
        User user = new User(email, password, false); // Admin is false by default
        mDatabase.child("users").child(userId).setValue(user)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(Crear_cuenta.this, "Datos guardados exitosamente", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Crear_cuenta.this, "Error al guardar los datos: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public static class User {
        public String email;
        public String password;
        public boolean admin;

        public User() {
            // Default constructor required for calls to DataSnapshot.getValue(User.class)
        }

        public User(String email, String password, boolean admin) {
            this.email = email;
            this.password = password;
            this.admin = admin;
        }
    }
}
