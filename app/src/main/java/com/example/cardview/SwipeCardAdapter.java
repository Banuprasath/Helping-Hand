package com.example.cardview;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import java.util.List;

public class SwipeCardAdapter extends RecyclerView.Adapter<SwipeCardAdapter.ViewHolder> {
    private List<CardItem> cardList;

    public SwipeCardAdapter(List<CardItem> cardList) {
        this.cardList = cardList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CardItem card = cardList.get(position);
        holder.imageView.setImageResource(card.getImageResource());
        holder.textView.setText(card.getTitle());

        holder.itemView.setOnClickListener(view -> {
            Context context = view.getContext();
            if (card.getActivityClass() != null) {
                Toast.makeText(context, "Opening " + card.getTitle(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, card.getActivityClass());
                context.startActivity(intent);
            } else {
                Toast.makeText(context, "No activity assigned for this card", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
        }
    }
}
