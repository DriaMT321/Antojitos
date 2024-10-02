package com.example.antojitos.Recetas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.antojitos.R;
import com.example.antojitos.Login.Crear_cuenta.User;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private List<User> userList;

    public UserAdapter(List<User> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = userList.get(position);
        holder.emailTextView.setText(user.email);
        holder.passwordTextView.setText(user.password);
        holder.adminTextView.setText(user.admin ? "Admin" : "User");
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView emailTextView;
        TextView passwordTextView;
        TextView adminTextView;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            emailTextView = itemView.findViewById(R.id.emailTextView);
            passwordTextView = itemView.findViewById(R.id.passwordTextView);
            adminTextView = itemView.findViewById(R.id.adminTextView);
        }
    }
}
