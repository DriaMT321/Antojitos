package com.example.antojitos;

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

import com.example.antojitos.Recetas.Comidas;
import com.example.antojitos.Recetas.Guardar_Receta;
import com.example.antojitos.Recetas.Mireceta;
import com.example.antojitos.Recetas.Postres;
import com.example.antojitos.Recetas.Refresco;
import com.example.antojitos.Recetas.Tragos;
import com.example.antojitos.Recetas.Vegano;

import org.checkerframework.checker.units.qual.min;

public class MainActivity extends AppCompatActivity {

    ConstraintLayout comida;
    ConstraintLayout tragos;
    ConstraintLayout postres;
    ConstraintLayout refresco;
    ConstraintLayout mi_receta;
    ConstraintLayout vegano;
    ImageView Inicioreceta;
    ImageView InicioAjustes1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       comida=findViewById(R.id.LayoutComidas);
       comida.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(MainActivity.this, Comidas.class);
               startActivity(intent);
           }
       });

       tragos=findViewById(R.id.LayoutTragos);
       tragos.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(MainActivity.this, Tragos.class);
               startActivity(intent);
           }
       });

       postres=findViewById(R.id.LayoutPostres);
       postres.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(MainActivity.this, Postres.class);
               startActivity(intent);
           }
       });

       refresco=findViewById(R.id.LayoutRefresco);
       refresco.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(MainActivity.this, Refresco.class);
               startActivity(intent);
           }
       });

       mi_receta=findViewById(R.id.Layoutmireceta);
       mi_receta.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(MainActivity.this, Mireceta.class);
               startActivity(intent);
           }
       });

       vegano=findViewById(R.id.LayoutVegano);
       vegano.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(MainActivity.this, Vegano.class);
               startActivity(intent);
           }
       });

        Inicioreceta=findViewById(R.id.iniciomireceta1);
        Inicioreceta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Guardar_Receta.class);
                startActivity(intent);
            }
        });


    }
}