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

public class Tofu_a_la_barbacoa extends AppCompatActivity {

    Button volver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tofu_ala_barbacoa);

        volver=findViewById(R.id.VolverTofu);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Tofu_a_la_barbacoa.this, Vegano.class);
                startActivity(intent);
            }
        });

    }
}