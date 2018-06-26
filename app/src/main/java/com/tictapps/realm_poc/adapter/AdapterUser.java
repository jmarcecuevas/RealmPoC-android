package com.tictapps.realm_poc.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.tictapps.realm_poc.R;
import com.tictapps.realm_poc.model.User;

import java.util.List;

public class AdapterUser extends RecyclerView.Adapter<AdapterUser.ViewHolder> {

    private final OnItemClickListener listener;
    private List<User> users;

    public AdapterUser(List<User> users, OnItemClickListener listener) {
        this.users = users;
        this.listener = listener;
    }

    @NonNull
    @Override
    public AdapterUser.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterUser.ViewHolder holder, int position) {
        holder.click(users.get(position), listener);
        holder.tvId.setText(String.valueOf(users.get(position).getId()));
        holder.name.setText(users.get(position).getName());
        holder.surname.setText(users.get(position).getSurname());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvId, name, surname;

        ViewHolder(View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tvId);
            name = itemView.findViewById(R.id.tvName);
            surname = itemView.findViewById(R.id.tvSurname);
        }

        void click(final User user, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(user);
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onClick(User user);
    }
} 
