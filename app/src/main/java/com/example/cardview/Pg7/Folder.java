package com.example.cardview.Pg7;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Insets;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cardview.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;

public class Folder extends AppCompatActivity {
    private static final int PICK_FILE_REQUEST = 1;
    private String folderName;
    private RecyclerView recyclerView;
    private FileAdapter fileAdapter;
    private List<String> fileList;
    private DatabaseReference databaseReference;
    private StorageReference storageReference;
    private Button uploadButton;
    private Uri fileUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_folder);
        recyclerView = findViewById(R.id.recyclerViewFiles);
        uploadButton = findViewById(R.id.uploadButton);

        folderName = getIntent().getStringExtra("folderName");
        databaseReference = FirebaseDatabase.getInstance().getReference("Folders").child(folderName).child("Files");
        storageReference = FirebaseStorage.getInstance().getReference("Files").child(folderName);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        fileList = new ArrayList<>();
        fileAdapter = new FileAdapter(this, fileList);
        recyclerView.setAdapter(fileAdapter);

        loadFiles();

        uploadButton.setOnClickListener(v -> selectFile());
    }
    private void loadFiles() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                fileList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String fileName = dataSnapshot.getValue(String.class);
                    fileList.add(fileName);
                }
                fileAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Folder.this, "Failed to load files", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void selectFile() {
        Intent intent = new Intent();
        intent.setType("*/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select File"), PICK_FILE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_FILE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            fileUri = data.getData();
            uploadFile();
        }
    }

    private void uploadFile() {
        if (fileUri != null) {
            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            String fileName = System.currentTimeMillis() + ".pdf"; // Example for PDFs
            StorageReference fileRef = storageReference.child(fileName);
            storageReference = FirebaseStorage.getInstance().getReferenceFromUrl("gs://jamcat-3e015.appspot.com/Files").child(folderName);

            fileRef.putFile(fileUri)
                    .addOnSuccessListener(taskSnapshot -> fileRef.getDownloadUrl().addOnSuccessListener(uri -> {
                        databaseReference.child(fileName).setValue(uri.toString());
                        progressDialog.dismiss();
                        Toast.makeText(Folder.this, "File Uploaded", Toast.LENGTH_SHORT).show();
                    }))
                    .addOnFailureListener(e -> {
                        progressDialog.dismiss();
                        Toast.makeText(Folder.this, "Upload Failed", Toast.LENGTH_SHORT).show();
                    });
        }
    }
}