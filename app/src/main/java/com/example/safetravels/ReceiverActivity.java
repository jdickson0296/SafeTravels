package com.example.safetravels;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class ReceiverActivity extends AppCompatActivity {

    // Initialze the buttons
    Button select;
    Button Stegan_key_R;
    Button AES_key_R;
    Button decrypt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver1);

        // This finds your button on the activity
        select = (Button) findViewById(R.id.select_button);
        Stegan_key_R = (Button) findViewById(R.id.stegan_button_r);
        AES_key_R = (Button) findViewById(R.id.aes_button_r);
        decrypt = (Button) findViewById(R.id.decrypt_button);

    }
}
