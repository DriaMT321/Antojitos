package com.example.antojitos.Comida;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.antojitos.Hamburguesa;
import com.example.antojitos.R;
import com.example.antojitos.Recetas.Comidas;

public class Pizza extends AppCompatActivity {
    Button volver2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pizza);

        volver2=findViewById(R.id.Volver2);
        volver2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Pizza.this, Comidas.class);
                startActivity(intent);
            }
        });
    }
}