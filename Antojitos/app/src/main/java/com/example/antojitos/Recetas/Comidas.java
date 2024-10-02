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

import com.example.antojitos.Comida.Pizza;
import com.example.antojitos.Comida.Sushi;
import com.example.antojitos.Hamburguesa;
import com.example.antojitos.MainActivity;
import com.example.antojitos.R;

public class Comidas extends AppCompatActivity {
    ImageView icono;
    ImageView iniciocomida;
    ImageView receta;
    ImageView ajustes;

    ConstraintLayout hamburguesa;
    ConstraintLayout pizza;
    ConstraintLayout sushi;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_comidas);

        icono=findViewById(R.id.icono1);
        icono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Comidas.this, MainActivity.class);
                startActivity(intent);
            }
        });

        iniciocomida=findViewById(R.id.comidasinicio);
        iniciocomida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Comidas.this, MainActivity.class);
                startActivity(intent);
            }
        });

        receta=findViewById(R.id.comidasrecetas);
        receta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Comidas.this, Guardar_Receta.class);
                startActivity(intent);
            }
        });

        ajustes=findViewById(R.id.comidasajustes);
        ajustes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Comidas.this, Ajutes.class);
                startActivity(intent);
            }
        });

        hamburguesa=findViewById(R.id.comidahamburguesa);
        hamburguesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Comidas.this, Hamburguesa.class);
                startActivity(intent);
            }
        });

        pizza=findViewById(R.id.comidapizza);
        pizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Comidas.this, Pizza.class);
                startActivity(intent);
            }
        });

        sushi=findViewById(R.id.comidasushi);
        sushi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Comidas.this, Sushi.class);
                startActivity(intent);
            }
        });

    }
}