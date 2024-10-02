package com.example.antojitos.Vegano;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import com.example.antojitos.R;
import com.example.antojitos.Recetas.Mireceta2;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class VentanaReceta extends AppCompatActivity {

    private TextView tituloVentana, ingredientesVentana, descripcionVentana;
    private ImageView fotoVentana;
    private AppCompatButton volverBerenjena, btnEliminar, btnEditar;
    private DatabaseReference mDatabase;
    private String recipeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ventana_receta);

        tituloVentana = findViewById(R.id.tituloventana);
        ingredientesVentana = findViewById(R.id.ingredientesventana);
        descripcionVentana = findViewById(R.id.descripcionventana);
        fotoVentana = findViewById(R.id.fotoventana);
        volverBerenjena = findViewById(R.id.entrar);
        btnEliminar = findViewById(R.id.btnEliminar);
        btnEditar = findViewById(R.id.btnEditar);

        mDatabase = FirebaseDatabase.getInstance().getReference("recipes");

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String ingredients = intent.getStringExtra("ingredients");
        String description = intent.getStringExtra("description");
        String imageUrl = intent.getStringExtra("imageUrl");
        recipeId = intent.getStringExtra("recipeId");

        tituloVentana.setText(title);
        ingredientesVentana.setText(ingredients);
        descripcionVentana.setText(description);
        if (imageUrl != null && !imageUrl.isEmpty()) {
            Picasso.get().load(imageUrl).into(fotoVentana);
        }

        volverBerenjena.setOnClickListener(v -> onBackPressed());

        btnEliminar.setOnClickListener(v -> deleteRecipe());

        btnEditar.setOnClickListener(v -> {
            Intent editIntent = new Intent(VentanaReceta.this, Mireceta2.class);
            editIntent.putExtra("recipeId", recipeId);
            editIntent.putExtra("title", title);
            editIntent.putExtra("ingredients", ingredients);
            editIntent.putExtra("description", description);
            editIntent.putExtra("imageUrl", imageUrl);
            startActivity(editIntent);
        });
    }

    private void deleteRecipe() {
        if (recipeId != null) {
            mDatabase.child(recipeId).removeValue().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    finish(); // Close the activity and go back to the previous screen
                } else {
                    // Handle error
                }
            });
        }
    }
}
