package com.example.cardview.Pg7;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.example.cardview.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Create_Bottom_Sheet extends BottomSheetDialogFragment {
    CardView create;
    EditText text;
    DatabaseReference databaseReference;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.create_bottom_sheet, container, false);

        create = view.findViewById(R.id.createFolder);
        text = view.findViewById(R.id.editText);

        // Initialize Firebase Database
        databaseReference = FirebaseDatabase.getInstance().getReference("Folders");

        create.setOnClickListener(v -> {
            String folderName = text.getText().toString().trim();

            if (!folderName.isEmpty()) {
                createFolder(folderName);
            } else {
                Toast.makeText(getContext(), "Enter folder name", Toast.LENGTH_SHORT).show();
            }
            dismiss();
        });

        return view;
    }

    private void createFolder(String folderName) {
        String folderId = databaseReference.push().getKey(); // Generate unique ID

        if (folderId != null) {
            databaseReference.child(folderId).setValue(folderName)
                    .addOnSuccessListener(aVoid -> {
                        Toast.makeText(getContext(), "Folder Created", Toast.LENGTH_SHORT).show();
                        dismiss(); // Close bottom sheet after creation
                    })
                    .addOnFailureListener(e -> Toast.makeText(getContext(), "Failed to Create Folder", Toast.LENGTH_SHORT).show());
        }
    }
}
