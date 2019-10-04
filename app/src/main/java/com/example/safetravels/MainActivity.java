package com.example.safetravels;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    // Initialize the buttons
    Button sendDataButton;
    Button receieveDataButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // This finds your button on the main activity
        sendDataButton = (Button) findViewById(R.id.send_button);
        receieveDataButton = (Button) findViewById(R.id.receieve_button);


        // This creates a action when button is pressed
        sendDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // This intent moves you from one activity to the next one
                Intent goToSendActivity = new Intent(getApplicationContext(), SenderActivity.class);
                // Starts up the activity
                startActivity(goToSendActivity);
            }
        });


        // This creates a action when button is pressed
        receieveDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // This intent moves you from one activity to the next one
                Intent goToReceiveActivity = new Intent(getApplicationContext(), ReceiverActivity.class);
                // Starts up the activity
                startActivity(goToReceiveActivity);
            }
        });
    }
}
