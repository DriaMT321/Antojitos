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
import com.example.antojitos.Vegano.Berenjena_con_soja;
import com.example.antojitos.Vegano.Ramen_Vegano;
import com.example.antojitos.Vegano.Tofu_a_la_barbacoa;

public class Vegano extends AppCompatActivity {

    ImageView icono;
    ConstraintLayout tofu;
    ConstraintLayout ramen;
    ConstraintLayout berenjena1;
    ImageView inicio;
    ImageView receta;
    ImageView ajustes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_vegano);

        icono=findViewById(R.id.iconovegano);
        icono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Vegano.this, MainActivity.class);
                startActivity(intent);
            }
        });

        tofu=findViewById(R.id.VeganoTofubarbacoa);
        tofu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Vegano.this, Tofu_a_la_barbacoa.class);
                startActivity(intent);
            }
        });

        ramen=findViewById(R.id.VeganoRamen);
        ramen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Vegano.this, Ramen_Vegano.class);
                startActivity(intent);
            }
        });

        berenjena1=findViewById(R.id.VeganoBerenjena);
        berenjena1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Vegano.this, Berenjena_con_soja.class);
                startActivity(intent);
            }
        });

        inicio=findViewById(R.id.veganoinicio);
        inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Vegano.this, MainActivity.class);
                startActivity(intent);
            }
        });

        receta=findViewById(R.id.veganorecetas);
        receta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Vegano.this, Guardar_Receta.class);
                startActivity(intent);
            }
        });

        ajustes=findViewById(R.id.veganoajustes);
        ajustes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Vegano.this, Ajutes.class);
                startActivity(intent);
            }
        });
    }
}