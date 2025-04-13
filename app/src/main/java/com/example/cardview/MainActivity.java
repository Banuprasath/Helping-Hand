package com.example.cardview;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import com.example.cardview.Pg3.MotivationQuote;
import com.example.cardview.login.LoginActivity;
import com.example.cardview.login.SignupActivity;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            //Schedule Notifiction Pg3
       // scheduleDailyNotification();
        //Schedule Notifiction Pg3
        TextView logout=findViewById(R.id.logout);
        FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
        logout.setOnClickListener(view->{

            firebaseAuth.signOut();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));

        });
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        List<CardItem> cardList = new ArrayList<>();
        int[] images = {R.drawable.pg2, R.drawable.pg2, R.drawable.pg2,
                R.drawable.pg2, R.drawable.pg2, R.drawable.pg2,
                R.drawable.pg2, R.drawable.pg2, R.drawable.pg2};

        for (int i = 0; i < 9; i++) {
            cardList.add(new CardItem(images[i], "Card " + (i + 1)));

        }

        recyclerView.setAdapter(new CardAdapter(cardList));
    }

}
