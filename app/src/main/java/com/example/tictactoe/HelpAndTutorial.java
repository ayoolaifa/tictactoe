package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HelpAndTutorial extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_and_tutorial);

        SinglePlayer();
        MultiPlayer();

        String htmlString =
                "<b><li>The game is played on a grid that's 3 squares by 3 squares.</li></b>\n" +
                "<b><li>You are X, your friend or the Computer is O.</li></b>\n" +
                "<b><li>Players take turns putting their marks in empty squares.</li></b>\n" +
                "<b><li>The first player to get 3 of their marks in a row (up, down, across, or diagonally) is the winner.</li></b>\n" +
                "<b><li>When all 9 squares are full, the game is over.</li></b>\n" +
                "<b><li>If no player has 3 marks in a row, the game ends in a tie.</li></b>";

        TextView TextTutorial = findViewById(R.id.tv_tutorial);
        TextTutorial.setText(Html.fromHtml(htmlString));

        TextView TextTutorialTitle = findViewById(R.id.TitleTutorialText);
        String TutorialTitle = "<u>How To Play</u>";
        TextTutorialTitle.setText(Html.fromHtml(TutorialTitle));


    }

    private void SinglePlayer(){
        Button SinglePlayerButton = findViewById(R.id.SinglePlayer_2);
        SinglePlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HelpAndTutorial.this, SinglePlayer.class));
            }
        });
    }

    private void MultiPlayer(){
        Button MultiPlayerButton = findViewById(R.id.MultiPlayer_2);
        MultiPlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HelpAndTutorial.this, MainActivity.class));
            }
        });
    }
}