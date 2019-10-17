package com.example.safetravels;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SenderActivity extends AppCompatActivity {

    // Initialize the buttons
    TextView enter_aes_s;
    TextView enter_stegan_s;
    EditText AES_key_text_S;
    EditText Stegan_key_text_S;
    Button Encrypt;
    Button upload;
    Button select_s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sender1);

        enter_aes_s = (TextView) findViewById(R.id.enter_aes_s);
        enter_stegan_s = (TextView) findViewById(R.id.enter_stegan_s);
        AES_key_text_S = (EditText) findViewById(R.id.aes_key_s);
        Stegan_key_text_S = (EditText) findViewById(R.id.stegan_key_s);
        Encrypt = (Button) findViewById(R.id.encrypt_button);
        upload = (Button) findViewById(R.id.upload_button);
        select_s =(Button) findViewById(R.id.select_button_s);


        // get the aes key from the text box
        String AES_key_s = String.valueOf(AES_key_text_S.getText());

        // get the stegan key from the text box
        String stegan_key_s = String.valueOf(Stegan_key_text_S.getText());


        // function for when encrypt is clicked
        Encrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });

        // function for when upload is clicked
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
