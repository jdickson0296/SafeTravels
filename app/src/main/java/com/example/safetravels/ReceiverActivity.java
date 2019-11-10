package com.example.safetravels;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Spinner;
import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

public class ReceiverActivity extends AppCompatActivity {

    // Initialize the buttons
    Button select;
    TextView enter_stegan_r;
    TextView enter_aes_r;
    EditText Stegan_key_text_R;
    EditText AES_key_text_R;
    Button decrypt;
    Spinner spinner1;

    public static String filename;
    public static String extension_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver1);

        // Request permission access
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1001);
        }

        // This finds your button on the activity
        select = (Button) findViewById(R.id.select_button);
        Stegan_key_text_R = (EditText) findViewById(R.id.stegan_key_r);
        AES_key_text_R = (EditText) findViewById(R.id.aes_key_r);
        enter_stegan_r = (TextView) findViewById(R.id.enter_stegan_r);
        enter_aes_r = (TextView) findViewById(R.id.enter_aes_r);
        decrypt = (Button) findViewById(R.id.decrypt_button);
        spinner1 = (Spinner) findViewById(R.id.spinner1);


        // get the stegan key from the text box
        String stegan_key_r = String.valueOf(Stegan_key_text_R.getText());

        // function for when select is clicked
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialFilePicker()
                        .withActivity(ReceiverActivity.this)
                        .withRequestCode(1000)
                        .withHiddenFiles(true) // Show hidden files and folders
                        .start();

            }
        });


        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                extension_text = spinner1.getSelectedItem().toString();
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        // function for when decrypt is clicked
        decrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // get the aes key from the text box
                final String AES_key_r = String.valueOf(AES_key_text_R.getText());

                try {
                    AESDecrypt.Decrypt(extension_text, AES_key_r, ReceiverActivity.this);
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
            String filePath = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);
            String[] arrayPath = filePath.split("/",9);
            filename = arrayPath[arrayPath.length-1];

        }
    }

    // Sends alert for permission to go through files
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1001: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(ReceiverActivity.this, "Permission Granted", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(ReceiverActivity.this, "Permission not granted", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }
    }

}
