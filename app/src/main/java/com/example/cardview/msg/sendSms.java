package com.example.cardview.msg;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.example.cardview.R;

public class sendSms extends AppCompatActivity {

    private EditText phoneNumberInput, messageInput;
    private Button sendSmsBtn;

    private static final int SMS_PERMISSION_CODE = 1; // Permission code

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_sms);

        phoneNumberInput = findViewById(R.id.phoneNumberInput);
        messageInput = findViewById(R.id.messageInput);
        sendSmsBtn = findViewById(R.id.sendSmsBtn);

        sendSmsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = phoneNumberInput.getText().toString().trim();
                String message = messageInput.getText().toString().trim();

                if (!phoneNumber.isEmpty() && !message.isEmpty()) {
                    if (ContextCompat.checkSelfPermission(sendSms.this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                        sendLocalSMS(phoneNumber, message);
                    } else {
                        ActivityCompat.requestPermissions(sendSms.this, new String[]{Manifest.permission.SEND_SMS}, SMS_PERMISSION_CODE);
                    }
                } else {
                    Toast.makeText(sendSms.this, "Enter both number and message", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Sends SMS using the local device
    private void sendLocalSMS(String phoneNumber, String message) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, message, null, null);
            Toast.makeText(this, "SMS Sent to " + phoneNumber, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "SMS Failed to Send!", Toast.LENGTH_SHORT).show();
        }
    }

    // Ask for SMS permission
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == SMS_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                sendLocalSMS(phoneNumberInput.getText().toString().trim(), messageInput.getText().toString().trim());
            } else {
                Toast.makeText(this, "Permission Denied!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
