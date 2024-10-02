package com.example.antojitos.Recetas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.antojitos.MainActivity;
import com.example.antojitos.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Guardar_Receta extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecipeAdapter adapter;
    private List<Mireceta.Recipe> recipeList;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    ImageView icono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_guardar_receta);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        icono = findViewById(R.id.Lista);
        icono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Guardar_Receta.this, MainActivity.class);
                startActivity(intent);
            }
        });

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recipeList = new ArrayList<>();
        adapter = new RecipeAdapter(this, recipeList);
        recyclerView.setAdapter(adapter);

        mDatabase = FirebaseDatabase.getInstance().getReference("recipes");

        if (currentUser != null) {
            loadRecipes(currentUser.getEmail());
        }
    }

    private void loadRecipes(final String userEmail) {
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                recipeList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Mireceta.Recipe recipe = dataSnapshot.getValue(Mireceta.Recipe.class);
                    if (recipe != null && recipe.userEmail != null && recipe.userEmail.equals(userEmail)) {
                        recipe.recipeId = dataSnapshot.getKey(); // Asignar el ID de la receta
                        recipeList.add(recipe);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle possible errors
            }
        });
    }
}
