package com.example.lesson2_1jdz;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {
    private ArrayList<Model> list = new ArrayList<>();
    private OnClickListener onClickListener;

    public void adModel(Model model, OnClickListener onClickListener) {
        list.add(model);
        notifyDataSetChanged();
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        holder.onBind(list.get(position), onClickListener);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView desc;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.txt_heading);
            desc = itemView.findViewById(R.id.txt_description);
        }

        public void onBind(Model model, OnClickListener onClickListener) {
            itemView.setOnClickListener(v -> {
                onClickListener.onClick(model);
            });
            title.setText("Заголовок: " + model.getTitle());
            desc.setText("Описание: " + model.getDesc());
        }
    }

    public interface OnClickListener {
        void onClick(Model model);
    }
}
