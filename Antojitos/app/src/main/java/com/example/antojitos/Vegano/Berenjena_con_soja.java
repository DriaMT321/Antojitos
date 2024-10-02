package com.example.antojitos.Vegano;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.antojitos.R;
import com.example.antojitos.Recetas.Vegano;

public class Berenjena_con_soja extends AppCompatActivity {

    Button volver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_berenjena_con_soja);

        volver=findViewById(R.id.entrar);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Berenjena_con_soja.this, Vegano.class);
                startActivity(intent);
            }
        });
    }
}