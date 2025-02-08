package com.example.cardview.pg2;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cardview.R;

public class pg2Page2 extends AppCompatActivity {

    EditText editText;
    Button btnPickColor, btnPickFont, btnIncreaseSize, btnDecreaseSize;
    TextView textView;
    float textSize = 24f; // Default size
    int fontIndex = 0; // Track font style
    int[] fontStyles = {Typeface.NORMAL, Typeface.BOLD, Typeface.ITALIC, Typeface.BOLD_ITALIC};
    String[] fontNames = {"Normal", "Bold", "Italic", "Bold Italic"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pg2_page2);

        editText = findViewById(R.id.editText);
        btnPickColor = findViewById(R.id.btnPickColor);
        btnPickFont = findViewById(R.id.btnPickFont);
        btnIncreaseSize = findViewById(R.id.btnIncreaseSize);
        btnDecreaseSize = findViewById(R.id.btnDecreaseSize);
        textView = findViewById(R.id.textView);

        btnPickColor.setOnClickListener(v -> showColorPicker());
        btnPickFont.setOnClickListener(v -> showFontPicker());
        btnIncreaseSize.setOnClickListener(v -> changeTextSize(true));
        btnDecreaseSize.setOnClickListener(v -> changeTextSize(false));
    }

    private void showColorPicker() {
        final String[] colors = {"Red", "Blue", "Green", "Yellow", "Black"};
        final int[] colorValues = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.BLACK};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Pick a Color");
        builder.setItems(colors, (dialog, which) -> {
            textView.setTextColor(colorValues[which]);
            textView.setText(editText.getText().toString());
        });

        builder.show();
    }

    private void showFontPicker() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose Font Style");
        builder.setItems(fontNames, (dialog, which) -> {
            textView.setTypeface(Typeface.DEFAULT, fontStyles[which]);
        });

        builder.show();
    }

    private void changeTextSize(boolean increase) {
        textSize += (increase) ? 2f : -2f;
        textView.setTextSize(textSize);
    }
}
