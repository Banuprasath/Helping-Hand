package com.example.cardview;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        List<CardItem> cardList = new ArrayList<>();
        int[] images = {R.drawable.pg1, R.drawable.pg2, R.drawable.pg3,
                R.drawable.pg4, R.drawable.pg5, R.drawable.pg6,
                R.drawable.pg7, R.drawable.pg8, R.drawable.pg9};

        for (int i = 0; i < 9; i++) {
            cardList.add(new CardItem(images[i], "Card " + (i + 1)));

        }

        recyclerView.setAdapter(new CardAdapter(cardList));
    }
}
