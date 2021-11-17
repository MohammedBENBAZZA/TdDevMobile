package com.example.diceroller;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Random;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button rollButton = (Button) findViewById(R.id.button);
        final TextView Number1=(TextView) findViewById(R.id.number1);
        final TextView Number2=(TextView) findViewById(R.id.number2);
       final EditText choice    = (EditText)findViewById(R.id.input);
        rollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //generate random number
                final int min = 1;
                final int max = Integer.valueOf(String.valueOf(choice.getText()));
                final int random1 = new Random().nextInt((max - min) + 1) + min;
                final int random2 = new Random().nextInt((max - min) + 1) + min;
                //change the text values
                Number1.setText(String.valueOf(random1));
                Number2.setText(String.valueOf(random2));

            }
        });
    }
}
