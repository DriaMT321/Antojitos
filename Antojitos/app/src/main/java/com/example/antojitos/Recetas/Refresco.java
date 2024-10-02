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
import com.example.antojitos.R;
import com.example.antojitos.Refresco.Arandano;
import com.example.antojitos.Refresco.Limon;
import com.example.antojitos.Refresco.Mango;

public class Refresco extends AppCompatActivity {

    ImageView icono;
    ConstraintLayout arandano;
    ConstraintLayout mango;
    ConstraintLayout limon;
    ImageView inicio;
    ImageView receta;
    ImageView ajustes;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_refresco);

        icono=findViewById(R.id.iconorefresco);
        icono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Refresco.this, MainActivity.class);
                startActivity(intent);
            }
        });

        arandano=findViewById(R.id.refrescoarandano);
        arandano.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Refresco.this, Arandano.class);
                startActivity(intent);
            }
        });

        mango=findViewById(R.id.refrescomango);
        mango.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Refresco.this, Mango.class);
                startActivity(intent);
            }
        });

        limon=findViewById(R.id.refrescoLimon);
        limon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Refresco.this, Limon.class);
                startActivity(intent);
            }
        });

        inicio=findViewById(R.id.refrescosinicio);
        inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Refresco.this, MainActivity.class);
                startActivity(intent);
            }
        });

        receta=findViewById(R.id.refrescosrecetas);
        receta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Refresco.this, Guardar_Receta.class);
                startActivity(intent);
            }
        });

        ajustes=findViewById(R.id.refrescosajustes);
        ajustes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Refresco.this, Ajutes.class);
                startActivity(intent);
            }
        });
    }
}