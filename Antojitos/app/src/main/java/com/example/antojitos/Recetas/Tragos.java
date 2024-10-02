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

import com.example.antojitos.MainActivity;
import com.example.antojitos.R;
import com.example.antojitos.Tragos.Apple_Martini;
import com.example.antojitos.Tragos.Mojito;
import com.example.antojitos.Tragos.Shamrock_Sour;

public class Tragos extends AppCompatActivity {

    ImageView icono;
    ConstraintLayout mojito;
    ConstraintLayout apple_martini;
    ConstraintLayout shamrock_sour;
    ImageView inicio;
    ImageView receta;
    ImageView ajustes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tragos);

        icono=findViewById(R.id.icono1);
        icono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Tragos.this, MainActivity.class);
                startActivity(intent);
            }
        });

        mojito=findViewById(R.id.tragomojito);
        mojito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Tragos.this, Mojito.class);
                startActivity(intent);
            }
        });

        apple_martini=findViewById(R.id.tragoapplemartini);
        apple_martini.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Tragos.this, Apple_Martini.class);
                startActivity(intent);
            }
        });

        shamrock_sour=findViewById(R.id.tragoshamrock);
        shamrock_sour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Tragos.this, Shamrock_Sour.class);
                startActivity(intent);
            }
        });

        inicio=findViewById(R.id.tragosinicio);
        inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Tragos.this, MainActivity.class);
                startActivity(intent);
            }
        });

        receta=findViewById(R.id.tragosrecetas);
        receta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Tragos.this, Guardar_Receta.class);
                startActivity(intent);
            }
        });

        ajustes=findViewById(R.id.tragosajustes);
        ajustes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Tragos.this, Ajutes.class);
                startActivity(intent);
            }
        });
    }
}