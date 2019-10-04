package com.example.safetravels;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ReceiverActivity extends AppCompatActivity {

    // Initialize the buttons
    Button select;
    TextView enter_stegan_r;
    TextView enter_aes_r;
    EditText Stegan_key_text_R;
    EditText AES_key_text_R;
    Button decrypt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver1);

        // This finds your button on the activity
        select = (Button) findViewById(R.id.select_button);
        Stegan_key_text_R = (EditText) findViewById(R.id.stegan_key_r);
        AES_key_text_R = (EditText) findViewById(R.id.aes_key_r);
        enter_stegan_r = (TextView) findViewById(R.id.enter_stegan_r);
        enter_aes_r = (TextView) findViewById(R.id.enter_aes_r);
        decrypt = (Button) findViewById(R.id.decrypt_button);

        // get the aes key from the text box
        String AES_key_r = String.valueOf(AES_key_text_R.getText());

        // get the stegan key from the text box
        String stegan_key_r = String.valueOf(Stegan_key_text_R.getText());

        // function for when select is clicked
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        // function for when decrypt is clicked
        decrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }
}
