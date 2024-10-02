package com.example.antojitos.Recetas;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.antojitos.R;
import com.example.antojitos.Vegano.VentanaReceta;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private Context context;
    private List<Mireceta.Recipe> recipeList;

    public RecipeAdapter(Context context, List<Mireceta.Recipe> recipeList) {
        this.context = context;
        this.recipeList = recipeList;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.items, parent, false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        Mireceta.Recipe recipe = recipeList.get(position);
        holder.recTitle.setText(recipe.title);
        holder.recDesc.setText(truncateText(recipe.description, 10)); // Limita a 10 palabras
        holder.recIngrediente.setText(truncateText(recipe.ingredients, 10)); // Limita a 10 palabras
        if (recipe.imageUrl != null && !recipe.imageUrl.isEmpty()) {
            Picasso.get().
                    load(recipe.imageUrl).
                    resize(200,200).
                    centerCrop().
                    into(holder.recImage);
        } else {
            holder.recImage.setImageResource(R.drawable.burger); // Placeholder image
        }

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, VentanaReceta.class);
            intent.putExtra("title", recipe.title);
            intent.putExtra("description", recipe.description);
            intent.putExtra("ingredients", recipe.ingredients);
            intent.putExtra("imageUrl", recipe.imageUrl);
            intent.putExtra("recipeId", recipe.recipeId); // Asegúrate de que estás pasando el recipeId
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    private String truncateText(String text, int maxWords) {
        if (text == null || text.isEmpty()) {
            return "";
        }
        String[] words = text.split("\\s+");
        if (words.length <= maxWords) {
            return text;
        }
        StringBuilder truncatedText = new StringBuilder();
        for (int i = 0; i < maxWords; i++) {
            truncatedText.append(words[i]).append(" ");
        }
        truncatedText.append("...");
        return truncatedText.toString().trim();
    }

    public static class RecipeViewHolder extends RecyclerView.ViewHolder {

        ImageView recImage;
        TextView recTitle;
        TextView recDesc;
        TextView recIngrediente;

        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            recImage = itemView.findViewById(R.id.recImage);
            recTitle = itemView.findViewById(R.id.recTitle);
            recDesc = itemView.findViewById(R.id.recDesc);
            recIngrediente = itemView.findViewById(R.id.recIngrediente);
        }
    }
}
