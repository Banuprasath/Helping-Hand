package com.example.cardview;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cardview.Pg3.MotivationQuote;
import com.example.cardview.Pg3.Pg3page1;
import com.example.cardview.Pg4.Calculator;
import com.example.cardview.Pg1.pg1page1;
import com.example.cardview.Pg4.pg4Page1;
import com.example.cardview.Pg5.QuizActivity;
import com.example.cardview.Pg6.MainActivity;
import com.example.cardview.Pg7.Pg7Activity;
import com.example.cardview.pg2.pg2Page1;
import com.example.cardview.pg2.pg2Page2;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {
    private List<CardItem> cardList;

    public CardAdapter(List<CardItem> cardList) {
        this.cardList = cardList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText("Card " + (position + 1));

        // Set image using switch-case
        switch (position) {
            case 0:
                holder.imageView.setImageResource(R.drawable.pg1);


                holder.itemView.setOnClickListener(view -> {
                    // Navigate to the corresponding activity
                    Toast.makeText(holder.itemView.getContext(), "Opening How to Install JDK", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(view.getContext(), pg1page1.class);
                    view.getContext().startActivity(intent);
                });

                break;
            case 1:
                holder.imageView.setImageResource(R.drawable.pg2);
                holder.itemView.setOnClickListener(view -> {
                    // Navigate to the corresponding activity
                    Toast.makeText(holder.itemView.getContext(), "Opening How to Install JDK", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(view.getContext(), pg2Page1.class);
                    view.getContext().startActivity(intent);
                });

                break;
            case 2:
                holder.imageView.setImageResource(R.drawable.pg3);

                holder.itemView.setOnClickListener(view -> {
                    // Navigate to the corresponding activity
                    Toast.makeText(holder.itemView.getContext(), "Opening How to Install JDK", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(view.getContext(), Pg3page1.class);
                    view.getContext().startActivity(intent);
                });

                break;
            case 3:
                holder.imageView.setImageResource(R.drawable.pg4);

                holder.itemView.setOnClickListener(view -> {
                    // Navigate to the corresponding activity
                    Toast.makeText(holder.itemView.getContext(), " Calculator", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(view.getContext(), pg4Page1.class);
                    view.getContext().startActivity(intent);
                });
                break;
            case 4:
                holder.imageView.setImageResource(R.drawable.pg5);

                holder.itemView.setOnClickListener(view -> {
                    // Navigate to the corresponding activity
                    Toast.makeText(holder.itemView.getContext(), " To-Do List", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(view.getContext(), QuizActivity.class);
                    view.getContext().startActivity(intent);
                });


                break;
            case 5:
                holder.imageView.setImageResource(R.drawable.pg6);

                holder.itemView.setOnClickListener(view -> {
                    // Navigate to the corresponding activity
                    Toast.makeText(holder.itemView.getContext(), " To-Do List", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(view.getContext(), MainActivity.class);
                    view.getContext().startActivity(intent);
                });

                break;
            case 6:
                holder.imageView.setImageResource(R.drawable.pg7);
                holder.itemView.setOnClickListener(view -> {
                    // Navigate to the corresponding activity
                    Toast.makeText(holder.itemView.getContext(), " To-Do List", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(view.getContext(), Pg7Activity.class);
                    view.getContext().startActivity(intent);
                });

                break;
            case 7:
                holder.imageView.setImageResource(R.drawable.pg8);
                break;
            case 8:
                holder.imageView.setImageResource(R.drawable.pg9);
                break;
            default:
                holder.imageView.setImageResource(R.mipmap.ic_launcher); // Default image
        }
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
