package com.example.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button[][] buttons = new Button[3][3];

    private boolean XTurn = true;

    private int roundCount;

    private int Xpoints;
    private int Opoints;

    private TextView textViewPlayer1;
    private TextView textViewPlayer2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        backButton();

        textViewPlayer1 = findViewById(R.id.text_view_p1);
        textViewPlayer2 = findViewById(R.id.text_view_p2);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }

        Button buttonReset = findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });
    }
    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals("")) {
            return;
        }

        if (XTurn){
            ((Button) v).setText("X");
        }else {
            ((Button) v).setText("O");
        }

        roundCount++;

        if (checkForWin()) {
            if (XTurn) {
                Xwins();
            } else {
                Owins();
            }
        } else if (roundCount ==9) {
            draw();
        } else {
            XTurn = !XTurn;
        }

    }

    private boolean checkForWin(){
        String[][] field = new String[3][3];

        for (int i = 0; i < 3; i++){
            for (int j = 0; j< 3; j++){
                field[i][j] = (String) buttons[i][j].getText().toString();
            }
        }

        for (int i = 0; i < 3; i++){
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")) {
                return true;
            }

        }

        for (int i = 0; i < 3; i++){
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")) {
                return true;
            }

        }

        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")) {
            return true;
        }

        if (field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("")) {
            return true;
        }

        return false;
    }

    private void Xwins(){
        Xpoints++;
        Toast.makeText(this, "Player 1 Wins", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }
    private void Owins(){
        Opoints++;
        Toast.makeText(this, "Player 2 Wins", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }
    public void draw(){
        Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    private void updatePointsText(){
        textViewPlayer1.setText("Player 1: " + Xpoints);
        textViewPlayer2.setText("Player 2: " + Opoints);
    }

    private void resetBoard(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }

        roundCount = 0;
        XTurn = true;
    }

    private void resetGame(){
        Xpoints = 0;
        Opoints = 0;
        updatePointsText();
        resetBoard();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("roundCount", roundCount);
        outState.putInt("Xpoints", Xpoints);
        outState.putInt("Opoints", Opoints);
        outState.putBoolean("XTurn", XTurn);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        roundCount = savedInstanceState.getInt("roundCount");
        Xpoints = savedInstanceState.getInt("Xpoints");
        Opoints = savedInstanceState.getInt("Opoints");
        XTurn = savedInstanceState.getBoolean("XTurn");
    }

    private void backButton(){
        Button BackButton = (Button) findViewById(R.id.button_back);
        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, StartingPage.class));
            }
        });
    }
}