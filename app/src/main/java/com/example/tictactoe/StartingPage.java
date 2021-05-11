package com.example.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class StartingPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_page);

        SinglePlayer();
        MultiPlayer();

        ImageView myImageView = (ImageView) findViewById(R.id.imageView);
        myImageView.setImageResource(R.drawable.tictactoetitle);

    }

    private void SinglePlayer(){
        Button SinglePlayerButton = (Button) findViewById(R.id.SinglePlayer);
        SinglePlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartingPage.this, SinglePlayer.class));
            }
        });
    }

    private void MultiPlayer(){
        Button MultiPlayerButton = (Button) findViewById(R.id.MultiPlayer);
        MultiPlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartingPage.this, MainActivity.class));
            }
        });
    }


}