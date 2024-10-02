package com.example.antojitos.Refresco;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.antojitos.Postres.Flan;
import com.example.antojitos.R;
import com.example.antojitos.Recetas.Postres;
import com.example.antojitos.Recetas.Refresco;

public class Limon extends AppCompatActivity {

    Button volver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_limon);

        volver=findViewById(R.id.VolverLimon);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Limon.this, Refresco.class);
                startActivity(intent);
            }
        });


    }
}