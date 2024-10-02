package com.example.antojitos.Vegano;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.antojitos.R;
import com.example.antojitos.Recetas.Tragos;
import com.example.antojitos.Recetas.Vegano;
import com.example.antojitos.Tragos.Apple_Martini;

public class Ramen_Vegano extends AppCompatActivity {

    Button volver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ramen_vegano);

        volver=findViewById(R.id.VolverRamen);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Ramen_Vegano.this, Vegano.class);
                startActivity(intent);
            }
        });

    }
}