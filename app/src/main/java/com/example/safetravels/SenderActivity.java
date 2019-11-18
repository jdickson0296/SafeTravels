package com.example.safetravels;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;
import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

public class SenderActivity extends AppCompatActivity {

    // Initialize the buttons
    TextView enter_aes_s;
    EditText AES_key_text_S;
    Button Encrypt;
    Button upload;
    Button select_s;

    public static String filename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sender1);

        // Request permission access
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1001);
        }

        enter_aes_s = (TextView) findViewById(R.id.enter_aes_s);
        AES_key_text_S = (EditText) findViewById(R.id.aes_key_s);
        Encrypt = (Button) findViewById(R.id.encrypt_button);
        select_s =(Button) findViewById(R.id.select_button_s);

        // function for when select is clicked
        select_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialFilePicker()
                        .withActivity(SenderActivity.this)
                        .withRequestCode(1000)
                        .withHiddenFiles(true) // Show hidden files and folders
                        .start();
            }
        });

        // function for when encrypt is clicked
        Encrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get the aes key from the text box
                final String AES_key_s = String.valueOf(AES_key_text_S.getText());

                try {
                    AESEncrypt.Encrypt(filename, AES_key_s, SenderActivity.this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    // Navigating through phones files
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == 1000 && resultCode == RESULT_OK) {
            filename = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);
            System.out.println(filename);

        }
    }

    // Sends alert for permission to go through files
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1001: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(SenderActivity.this, "Permission Granted", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(SenderActivity.this, "Permission not granted", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }
    }
}
