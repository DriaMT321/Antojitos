package com.example.antojitos.Recetas;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.antojitos.R;
import com.example.antojitos.Recetas.Mireceta.Recipe;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.io.IOException;

public class Mireceta2 extends AppCompatActivity {

    private static final int STORAGE_PERMISSION_CODE = 1;
    private static final int PICK_IMAGE_REQUEST = 2;

    private EditText uploadTitles, uploadIngredientss, uploadDescriptions;
    private ImageView uploadImages;
    private Button saveButton;
    private DatabaseReference mDatabase;
    private Uri imageUri;
    private String recipeId;
    private String currentImageUrl; // Variable para almacenar la URL de la imagen actual

    private FirebaseAuth mAuth;
    private FirebaseStorage mStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mireceta2);

        mAuth = FirebaseAuth.getInstance();
        mStorage = FirebaseStorage.getInstance();

        uploadTitles = findViewById(R.id.uploadTitles);
        uploadIngredientss = findViewById(R.id.uploadIngredientss);
        uploadDescriptions = findViewById(R.id.uploadDescriptions);
        uploadImages = findViewById(R.id.uploadImages);
        saveButton = findViewById(R.id.Saves);

        // Get the data from the intent
        Intent intent = getIntent();
        recipeId = intent.getStringExtra("recipeId");
        String title = intent.getStringExtra("title");
        String ingredients = intent.getStringExtra("ingredients");
        String description = intent.getStringExtra("description");
        currentImageUrl = intent.getStringExtra("imageUrl"); // Obtener la URL de la imagen actual

        // Set the data to the views
        uploadTitles.setText(title);
        uploadIngredientss.setText(ingredients);
        uploadDescriptions.setText(description);
        if (currentImageUrl != null && !currentImageUrl.isEmpty()) {
            Picasso.get().load(currentImageUrl).into(uploadImages);
        }

        uploadImages.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(Mireceta2.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                openImagePicker();
            } else {
                requestStoragePermission();
            }
        });

        saveButton.setOnClickListener(v -> {
            saveButton.setEnabled(false); // Deshabilitar el botón
            updateRecipe();
        });

        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    private void requestStoragePermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                STORAGE_PERMISSION_CODE);
    }

    private void openImagePicker() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openImagePicker();
            } else {
                Toast.makeText(this, "Permiso denegado", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                uploadImages.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateRecipe() {
        String title = uploadTitles.getText().toString().trim();
        String ingredients = uploadIngredientss.getText().toString().trim();
        String description = uploadDescriptions.getText().toString().trim();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (title.isEmpty() || ingredients.isEmpty() || description.isEmpty()) {
            Toast.makeText(Mireceta2.this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
            saveButton.setEnabled(true); // Rehabilitar el botón
            return;
        }

        if (currentUser == null) {
            Toast.makeText(Mireceta2.this, "Error: Usuario no autenticado", Toast.LENGTH_SHORT).show();
            saveButton.setEnabled(true); // Rehabilitar el botón
            return;
        }

        String userEmail = currentUser.getEmail();
        if (recipeId != null) {
            if (imageUri != null) {
                StorageReference storageRef = mStorage.getReference().child("images/" + recipeId);
                storageRef.putFile(imageUri)
                        .addOnSuccessListener(taskSnapshot -> storageRef.getDownloadUrl()
                                .addOnSuccessListener(uri -> {
                                    String imageUrl = uri.toString();
                                    Recipe updatedRecipe = new Recipe(title, ingredients, description, imageUrl, userEmail, recipeId);
                                    updateRecipeInDatabase(updatedRecipe);
                                }))
                        .addOnFailureListener(e -> {
                            Toast.makeText(Mireceta2.this, "Error al subir la imagen: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            saveButton.setEnabled(true); // Rehabilitar el botón
                        });
            } else {
                // Si no se seleccionó una nueva imagen, mantener la URL de la imagen actual
                Recipe updatedRecipe = new Recipe(title, ingredients, description, currentImageUrl, userEmail, recipeId);
                updateRecipeInDatabase(updatedRecipe);
            }
        } else {
            Toast.makeText(Mireceta2.this, "Error al obtener el ID de la receta", Toast.LENGTH_SHORT).show();
            saveButton.setEnabled(true); // Rehabilitar el botón
        }
    }

    private void updateRecipeInDatabase(Recipe recipe) {
        mDatabase.child("recipes").child(recipeId).setValue(recipe)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(Mireceta2.this, "Receta actualizada exitosamente", Toast.LENGTH_SHORT).show();
                        // Ir a la pantalla activity_guardar_receta.xml
                        Intent intent = new Intent(Mireceta2.this, Guardar_Receta.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(Mireceta2.this, "Error al actualizar la receta: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        saveButton.setEnabled(true); // Rehabilitar el botón
                    }
                });
    }
}
