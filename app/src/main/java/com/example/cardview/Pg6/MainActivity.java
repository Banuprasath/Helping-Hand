package com.example.cardview.Pg6;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cardview.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    RecyclerView recyclerView;
    TaskAdapter taskAdapter;
    List<Task> taskList = new ArrayList<>();
    FloatingActionButton addButton;
    EditText taskInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        // Set title in the Action Bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("To-Do List");
        }

        databaseHelper = new DatabaseHelper(this);
        recyclerView = findViewById(R.id.recyclerView);
        addButton = findViewById(R.id.addButton);
        taskInput = findViewById(R.id.taskInput);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        loadTasks();

        checkPendingTasks(); // Call the function to show a notification if tasks are pending

        // Add Task
        addButton.setOnClickListener(view -> {
            String task = taskInput.getText().toString();
            if (!task.isEmpty()) {
                databaseHelper.insertTask(task);
                taskInput.setText("");
                loadTasks();
                checkPendingTasks(); // Re-check pending tasks after adding a new one
            } else {
                Toast.makeText(this, "Enter a task!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadTasks() {
        taskList.clear();
        Cursor cursor = databaseHelper.getAllTasks();
        while (cursor.moveToNext()) {
            taskList.add(new Task(cursor.getInt(0), cursor.getString(1)));
        }
        taskAdapter = new TaskAdapter(taskList, new TaskAdapter.OnTaskClickListener() {
            @Override
            public void onEdit(Task task) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Edit Task");

                // Create an input field in the dialog
                final EditText input = new EditText(MainActivity.this);
                input.setText(task.getTask()); // Pre-fill with existing task
                builder.setView(input);

                builder.setPositiveButton("Update", (dialog, which) -> {
                    String updatedTask = input.getText().toString().trim();
                    if (!updatedTask.isEmpty()) {
                        databaseHelper.updateTask(task.getId(), updatedTask); // Update DB
                        loadTasks(); // Refresh RecyclerView
                    } else {
                        Toast.makeText(MainActivity.this, "Task cannot be empty!", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());

                builder.show();
            }


            @Override
            public void onDelete(Task task) {
                databaseHelper.deleteTask(task.getId());
                loadTasks();
            }
        });
        taskAdapter.notifyDataSetChanged();

        recyclerView.setAdapter(taskAdapter);




    }

    private void checkPendingTasks() {
        Cursor cursor = databaseHelper.getAllTasks();
        if (cursor.getCount() > 0) {
            // There are unfinished tasks, show a notification
            showNotification("To-Do List Reminder", "You have pending tasks to complete!");
        }
    }

    private void showNotification(String title, String message) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        String channelId = "todo_notifications";

        // Create Notification Channel (For Android 8.0+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId, "To-Do List Alerts", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        // Create Intent to Open App on Notification Click
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        // Build Notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelId)
                .setSmallIcon(com.google.android.material.R.drawable.navigation_empty_icon)  // Make sure to add a notification icon in `res/drawable`
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);

        // Show Notification
        notificationManager.notify(1, builder.build());
    }
}
