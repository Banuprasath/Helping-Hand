package com.example.cardview;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.cardview.Pg1.pg1page1;
import com.example.cardview.Pg3.Pg3page1;
import com.example.cardview.Pg4.pg4Page1;
import com.example.cardview.pg2.pg2Page1;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize ViewPager2
        ViewPager2 viewPager = findViewById(R.id.viewPager);

        // List of 9 cards
        List<CardItem> cardList = new ArrayList<>();
        cardList.add(new CardItem(R.drawable.pg1, "How to Install JDK", pg1page1.class));
        cardList.add(new CardItem(R.drawable.pg2, "Page 2", pg2Page1.class));
        cardList.add(new CardItem(R.drawable.pg3, "Motivation Quotes", Pg3page1.class));
        cardList.add(new CardItem(R.drawable.pg4, "Page 4", pg4Page1.class));
        cardList.add(new CardItem(R.drawable.pg5, "GUI Components", pg4Page1.class));
        cardList.add(new CardItem(R.drawable.pg6, "Page 6", null));
        cardList.add(new CardItem(R.drawable.pg7, "Page 7", null));
        cardList.add(new CardItem(R.drawable.pg8, "Page 8", null));
        cardList.add(new CardItem(R.drawable.pg9, "Page 9", null));

        // Set the adapter
        SwipeCardAdapter adapter = new SwipeCardAdapter(cardList);
        viewPager.setAdapter(adapter);
    }
}
