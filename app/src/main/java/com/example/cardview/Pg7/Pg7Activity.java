package com.example.cardview.Pg7;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cardview.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Pg7Activity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<RecyclerData> recyclerDataList;
    RecyclerAdapter recyclerAdapter;
    FloatingActionButton floatingActionButton;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pg7);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.recyclerLayout);
        floatingActionButton = findViewById(R.id.floatingActionButton);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        recyclerDataList = new ArrayList<>();
        recyclerAdapter = new RecyclerAdapter(this, recyclerDataList);
        recyclerView.setAdapter(recyclerAdapter);

        // Initialize Firebase Database Reference
        databaseReference = FirebaseDatabase.getInstance().getReference("Folders");

        // Load Data from Firebase
        loadFoldersFromDatabase();

        floatingActionButton.setOnClickListener(view -> {
            Create_Bottom_Sheet create_bottom_sheet = new Create_Bottom_Sheet();
            create_bottom_sheet.show(getSupportFragmentManager(), "Create Folder");
        });
    }

    private void loadFoldersFromDatabase() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                recyclerDataList.clear(); // Clear old data before adding new data

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String folderName = dataSnapshot.getValue(String.class);
                    recyclerDataList.add(new RecyclerData(recyclerDataList.size() + 1, folderName));
                }

                recyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle error
            }
        });
    }
}
