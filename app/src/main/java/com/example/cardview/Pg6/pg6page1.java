package com.example.cardview.Pg6;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.cardview.Pg4.Calculator;
import com.example.cardview.Pg4.pg4Page1;
import com.example.cardview.R;

public class pg6page1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pg6page1);


        @SuppressLint("WrongViewCast") AppCompatTextView textView = findViewById(R.id.textView2);

        // Ensure the ID exists in XML

        // Set the HTML content using Html.fromHtml()
        String htmlContent = " <h2><b>Simple Procedure to Create a To-Do List App using RecyclerView & SQLite in Android Studio:</b></h2> <ul> <li>Open <b>Android Studio</b> and create a new project.</li> <li>Select <b>Empty Activity</b> and choose <b>Java</b> as the language.</li> <li>Create an <b>XML layout</b> file for displaying tasks using <b>RecyclerView</b> and buttons.</li> <li>Create a layout file <b>activity_task_adapter.xml</b> for each task row (contains task name, edit and delete icons).</li> <li>Create a model class named <b>Task.java</b> with fields like <i>id</i> and <i>task</i>.</li> <li>Create <b>TaskAdapter.java</b> to handle binding of data with RecyclerView.</li> <li>Use <b>OnClickListener</b> in the adapter to handle edit and delete actions.</li> <li>Create <b>DatabaseHelper.java</b> using <b>SQLiteOpenHelper</b> to manage database operations (insert, update, delete, fetch).</li> <li>In your main activity, initialize the RecyclerView, load tasks from the database and update the adapter.</li> <li><b>Test</b> the app to ensure adding, editing, and deleting tasks works as expected.</li> </ul><br> <h2><b>Algorithm to Perform Task Operations:</b></h2> <ol> <li>Create the SQLite table with <b>id</b> (auto-increment) and <b>task</b> (text).</li> <li>To <b>insert</b> a new task: <ul> <li>Get user input and pass it to `insertTask()` method.</li> <li>Refresh the RecyclerView after inserting.</li> </ul> </li> <li>To <b>fetch</b> all tasks: <ul> <li>Call `getAllTasks()` using a `Cursor` and build a list to display in RecyclerView.</li> </ul> </li> <li>To <b>update</b> a task: <ul> <li>Use `updateTask(id, newTask)` method from `DatabaseHelper`.</li> <li>Update the list and notify the adapter.</li> </ul> </li> <li>To <b>delete</b> a task: <ul> <li>Call `deleteTask(id)` and remove the item from the list.</li> <li>Notify the adapter to update the view.</li> </ul> </li> </ol><br> <h2><b>Dependencies Required:</b></h2> <ul> <li>No external libraries are required — only built-in Android libraries like <b>SQLiteOpenHelper</b>, <b>RecyclerView</b>, etc.</li> <li>Make sure to include RecyclerView dependency in <b>build.gradle (app level)</b> if not already added: <pre><code>implementation 'androidx.recyclerview:recyclerview:1.2.1'</code></pre></li> </ul><br> <h2><b>References:</b></h2> <ul> <li><a href='https://developer.android.com/reference/androidx/recyclerview/widget/RecyclerView'><i>Android RecyclerView Documentation</i></a></li> <li><a href='https://developer.android.com/training/data-storage/sqlite'><i>SQLite in Android</i></a></li> <li><a href='YOUR_REFERENCE_LINK'><i>Your Additional Reference Link</i></a></li> </ul>\n";

        // Apply the HTML content
        textView.setText(Html.fromHtml(htmlContent, Html.FROM_HTML_MODE_LEGACY));

        // Set text color to white
        textView.setTextColor(Color.WHITE);

        // Set text size
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);

        // Set background color
        textView.setBackgroundColor(ContextCompat.getColor(this, R.color.primaryColor));

        // Set padding
        textView.setPadding(16, 16, 16, 16);

        // Enable scrolling
        textView.setMovementMethod(android.text.method.ScrollingMovementMethod.getInstance());

        // Enable clickable links
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        Button b1 = findViewById(R.id.button);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(pg6page1.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}