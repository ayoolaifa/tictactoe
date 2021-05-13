package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

public class HelpAndTutorial extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_and_tutorial);

        String htmlString =
                "        <li>The game is played on a grid that's 3 squares by 3 squares.</li>\n" +
                "        <li>You are X, your friend or the Computer is O.</li>\n" +
                "        <li>Players take turns putting their marks in empty squares.</li>\n" +
                "        <li>The first player to get 3 of her marks in a row (up, down, across, or diagonally) is the winner.</li>\n" +
                "        <li>When all 9 squares are full, the game is over.</li>\n" +
                "        <li>If no player has 3 marks in a row, the game ends in a tie.</li>";

        TextView TextTutorial = findViewById(R.id.tv_tutorial);
        TextTutorial.setText(Html.fromHtml(htmlString));

        TextView TextTutorialTitle = findViewById(R.id.TitleTutorialText);
        String TutorialTitle = "<u>How To Play</u>";
        TextTutorialTitle.setText(Html.fromHtml(TutorialTitle));
    }
}