package com.example.safetravels;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class SenderActivity extends AppCompatActivity {

    // Initialze the buttons
    Button AES_key_S;
    Button Stegan_key_S;
    Button Encrypt;
    Button upload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sender1);

        AES_key_S = (Button) findViewById(R.id.aes_button_s);
        Stegan_key_S = (Button) findViewById(R.id.stegan_button_s);
        Encrypt = (Button) findViewById(R.id.encrypt_button);
        upload = (Button) findViewById(R.id.upload_button);


    }
}
