package com.example.antojitos.Recetas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.antojitos.Hamburguesa;
import com.example.antojitos.MainActivity;
import com.example.antojitos.Postres.Flan;
import com.example.antojitos.Postres.Tiramisu;
import com.example.antojitos.Postres.Waffles;
import com.example.antojitos.R;
import com.example.antojitos.Refresco.Limon;

public class Postres extends AppCompatActivity {
    ImageView icono;
    ConstraintLayout flan;
    ConstraintLayout waffles;
    ConstraintLayout tiramisu;
    ImageView inicio;
    ImageView receta;
    ImageView ajustes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_postres);

        icono=findViewById(R.id.iconopostre);
        icono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Postres.this, MainActivity.class);
                startActivity(intent);
            }
        });

        flan=findViewById(R.id.postreflan);
        flan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Postres.this, Flan.class);
                startActivity(intent);
            }
        });

        waffles=findViewById(R.id.postrewaffles);
        waffles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Postres.this, Waffles.class);
                startActivity(intent);
            }
        });

        tiramisu=findViewById(R.id.postretiramisu);
        tiramisu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Postres.this, Tiramisu.class);
                startActivity(intent);
            }
        });

        inicio=findViewById(R.id.postresinicio);
        inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Postres.this, MainActivity.class);
                startActivity(intent);
            }
        });

        receta=findViewById(R.id.postresrecetas);
        receta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Postres.this, Guardar_Receta.class);
                startActivity(intent);
            }
        });

        ajustes=findViewById(R.id.postresajustes);
        ajustes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Postres.this, Ajutes.class);
                startActivity(intent);
            }
        });
    }
}