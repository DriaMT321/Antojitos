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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.IOException;

public class Mireceta extends AppCompatActivity {

    private static final int STORAGE_PERMISSION_CODE = 1;
    private static final int PICK_IMAGE_REQUEST = 2;

    private EditText uploadTitle, uploadIngredients, uploadDescription;
    private ImageView uploadImage;
    private Button saveButton;
    private DatabaseReference mDatabase;
    private Uri imageUri;

    private FirebaseAuth mAuth;
    private FirebaseStorage mStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mireceta);

        mAuth = FirebaseAuth.getInstance();
        mStorage = FirebaseStorage.getInstance();

        uploadTitle = findViewById(R.id.uploadTitles);
        uploadIngredients = findViewById(R.id.uploadIngredientss);
        uploadDescription = findViewById(R.id.uploadDescriptions);
        uploadImage = findViewById(R.id.uploadImages);
        saveButton = findViewById(R.id.Saves);

        uploadImage.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(Mireceta.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                openImagePicker();
            } else {
                requestStoragePermission();
            }
        });

        saveButton.setOnClickListener(v -> {
            saveButton.setEnabled(false); // Deshabilitar el botón
            saveRecipe();
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
                uploadImage.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveRecipe() {
        String title = uploadTitle.getText().toString().trim();
        String ingredients = uploadIngredients.getText().toString().trim();
        String description = uploadDescription.getText().toString().trim();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (title.isEmpty() || ingredients.isEmpty() || description.isEmpty()) {
            Toast.makeText(Mireceta.this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
            saveButton.setEnabled(true); // Rehabilitar el botón
            return;
        }

        if (currentUser == null) {
            Toast.makeText(Mireceta.this, "Error: Usuario no autenticado", Toast.LENGTH_SHORT).show();
            saveButton.setEnabled(true); // Rehabilitar el botón
            return;
        }

        String userEmail = currentUser.getEmail();
        String recipeId = mDatabase.child("recipes").push().getKey();
        if (imageUri != null) {
            StorageReference storageRef = mStorage.getReference().child("images/" + recipeId);
            storageRef.putFile(imageUri)
                    .addOnSuccessListener(taskSnapshot -> storageRef.getDownloadUrl()
                            .addOnSuccessListener(uri -> {
                                String imageUrl = uri.toString();
                                Recipe recipe = new Recipe(title, ingredients, description, imageUrl, userEmail, recipeId);
                                saveRecipeToDatabase(recipe, recipeId);
                            }))
                    .addOnFailureListener(e -> {
                        Toast.makeText(Mireceta.this, "Error al subir la imagen: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        saveButton.setEnabled(true); // Rehabilitar el botón
                    });
        } else {
            Recipe recipe = new Recipe(title, ingredients, description, null, userEmail, recipeId);
            saveRecipeToDatabase(recipe, recipeId);
        }
    }

    private void saveRecipeToDatabase(Recipe recipe, String recipeId) {
        if (recipeId != null) {
            mDatabase.child("recipes").child(recipeId).setValue(recipe)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(Mireceta.this, "Receta guardada exitosamente", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(Mireceta.this, "Error al guardar la receta: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        saveButton.setEnabled(true); // Rehabilitar el botón
                    });
        } else {
            Toast.makeText(Mireceta.this, "Error al generar el ID de la receta", Toast.LENGTH_SHORT).show();
            saveButton.setEnabled(true); // Rehabilitar el botón
        }
    }

    public static class Recipe {
        public String title;
        public String description;
        public String ingredients;
        public String imageUrl;
        public String userEmail;
        public String recipeId;

        public Recipe() {
            // Default constructor required for calls to DataSnapshot.getValue(Recipe.class)
        }

        public Recipe(String title, String description, String ingredients, String imageUrl, String userEmail, String recipeId) {
            this.title = title;
            this.description = description;
            this.ingredients = ingredients;
            this.imageUrl = imageUrl;
            this.userEmail = userEmail;
            this.recipeId = recipeId;
        }
    }
}
