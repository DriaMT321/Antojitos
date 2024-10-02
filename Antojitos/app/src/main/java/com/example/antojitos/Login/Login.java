package com.example.antojitos.Login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.antojitos.MainActivity;
import com.example.antojitos.R;
import com.example.antojitos.Recetas.SosAdminActivity;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;

public class Login extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        EditText emailEditText = findViewById(R.id.editTextTextEmail);
        EditText passwordEditText = findViewById(R.id.ingresa_tu_comtraseña);
        MaterialButton loginButton = findViewById(R.id.Login);

        TextView crearCuenta = findViewById(R.id.Crearcuenta);
        crearCuenta.setOnClickListener(v -> {
            Intent intent = new Intent(Login.this, Crear_cuenta.class);
            startActivity(intent);
        });

        loginButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(Login.this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
                return;
            }

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(Login.this, task -> {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            if (user != null) {
                                sendWelcomeEmail(user.getEmail());
                                checkAdminStatus(user.getUid());
                            }
                        } else {
                            Log.e(TAG, "Error al iniciar sesión: " + task.getException().getMessage());
                            Toast.makeText(Login.this, "Error al iniciar sesión: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        });
    }

    private void sendWelcomeEmail(String userEmail) {
        String subject = "Bienvenido a Antojitos!";
        String message = "Gracias por usar nuestra aplicacion, " + userEmail + "! Esperemos que lo pases bien.";
        Log.d(TAG, "Te enviamos saludos " + userEmail);
        new Thread(() -> new JavaMailAPI(userEmail, subject, message).sendEmail()).start();
    }

    private void checkAdminStatus(String userId) {
        mDatabase.child("users").child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    Crear_cuenta.User user = snapshot.getValue(Crear_cuenta.User.class);
                    if (user != null && user.admin) {
                        Intent intent = new Intent(Login.this, SosAdminActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Intent intent = new Intent(Login.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                } else {
                    Toast.makeText(Login.this, "Usuario no encontrado en la base de datos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Login.this, "Error al verificar el estado de administrador: " + error.getMessage(), Toast.LENGTH_SHORT).show();
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
