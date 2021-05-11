package com.example.tictactoe;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.MovementMethod;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class SinglePlayer extends AppCompatActivity implements View.OnClickListener{

    private final Button[][] buttons = new Button[3][3];

    private final Random Rand = new Random();

    private final String Computer = "O";

    public boolean playerTurn = true;

    private int roundCount;

    private int playerpoints;
    private int computerpoints;

    private TextView textViewPlayer;
    private TextView textViewComputer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_player);

        //When I Start to let user choose thier Difficulty//
        /*Spinner MySpinner = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(SinglePlayer.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.DropDown));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        MySpinner.setAdapter((myAdapter));*/

        backButton();

        textViewPlayer = findViewById(R.id.text_view_player);
        textViewComputer = findViewById(R.id.text_view_computer);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button1_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }

        //Reset game button//
        Button buttonReset = findViewById(R.id.button_reset2);
        buttonReset.setOnClickListener(v -> resetGame());

    }

    //This function allows the player to set which position they want to play at//
    @Override
    public void onClick(View v) {
        String[][] field = new String[3][3];

        if (!((Button) v).getText().toString().equals("")) {
            return;
        }

        if (playerTurn) {

            for (int i = 0; i < 3; i++){
                for (int j = 0; j < 3; j++){
                    field[i][j] = (String) buttons[i][j].getText().toString();
                }

            }
            ((Button) v).setText("X");
            roundCount++;
            WhoHasWon();
        }


        if (!playerTurn) {
            for (int i = 0; i < 3; i++){
                for (int j = 0; j < 3; j++){
                    field[i][j] = (String) buttons[i][j].getText().toString();
                }

            }
            String[] array = new String[2];
            String RowAndColumn = BestMove(field);
            array = RowAndColumn.split(",");
            int Row = Integer.valueOf(array[0]);
            int Column = Integer.valueOf(array[1]);
            buttons[Row][Column].setText(Computer);
            roundCount++;

            WhoHasWon();
        }




    }

    private String BestMove(String[][] field){
        int BestValue = -1000;
        int BestRowMove = 0;
        int BestColumnMove = 0;
        String Move = "";

        for (int i = 0; i < 3; i++){
            for (int j = 0; j< 3; j++){

                if (field[i][j].equals("")){
                    field[i][j] = Computer;

                    int MoveValue = Minimax(field, 6, Integer.MIN_VALUE, Integer.MAX_VALUE, false);

                    field[i][j] = "";

                    if (MoveValue > BestValue){
                        BestRowMove = i;
                        BestColumnMove = j;
                        Move = String.valueOf(BestRowMove) + "," + String.valueOf(BestColumnMove);
                        BestValue = MoveValue;
                    }
                }
            }
        }
        return Move;
    }

    private int Minimax (String[][] field, int depth, int alpha, int beta, Boolean isMax) {
        int Score = Evaluation(field);
        if (Math.abs(Score) == 10 || depth == 0 || !(isMoveLeft(field))){
            return Score;
        }
        if (isMax) {
            int HighestValue = Integer.MIN_VALUE;


            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (field[i][j].equals("")) {
                        field[i][j] = Computer;
                        HighestValue = Math.max(HighestValue, Minimax(field, depth - 1, alpha, beta, false));
                        field[i][j] = "";
                        int Random = Rand.nextInt(100);
                        alpha = Math.max(alpha,HighestValue);
                        if (alpha >= beta || Random < 2){
                            return HighestValue;
                        }
                    }
                }
            }

            return HighestValue;
        } else {
            int LowestValue = Integer.MAX_VALUE;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if(field[i][j].equals("")){
                        String player = "X";
                        field[i][j] = player;
                        LowestValue = Math.min(LowestValue, Minimax(field, depth - 1, alpha, beta,true));
                        field[i][j] = "";
                        int Random = Rand.nextInt(100);
                        beta = Math.min(beta, LowestValue);
                        if (beta <= alpha || Random < 2){
                            return LowestValue;
                        }
                    }

                }
            }

            return LowestValue;
        }
    }

    private void backButton(){
        Button BackButton = (Button) findViewById(R.id.button_back);
        BackButton.setOnClickListener(v -> startActivity(new Intent(SinglePlayer.this, StartingPage.class)));
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

    private boolean isMoveLeft(String[][] field){
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (field[i][j].equals(""))
                    return true;
        return false;
    }

    private int Evaluation(String[][] field){
        for (int i = 0; i < 3; i++){
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")) {
                if(field[i][0].equals("X")){
                    return -10;
                }else if (field[i][0].equals("O")){
                    return 10;
                }
            }

        }

        for (int i = 0; i < 3; i++){
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")) {
                if(field[0][i].equals("X")){
                    return -10;
                }else if (field[0][i].equals("O")){
                    return 10;
                }
            }

        }

        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")) {
            if(field[1][1].equals("X")){
                return -10;
            }else if (field[1][1].equals("O")){
                return 10;
            }
        }

        if (field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("")) {
            if(field[0][2].equals("X")){
                return -10;
            }else if (field[0][2].equals("O")){
                return 10;
            }
        }

        return 0;
    }

    private void playerwins(){
        playerpoints++;
        Toast.makeText(this, "Player  Wins", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }

    private void computerwins(){
        computerpoints++;
        Toast.makeText(this, "Computer Wins", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }

    private void draw(){
        Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    @SuppressLint("SetTextI18n")
    private void updatePointsText(){
        textViewPlayer.setText("Player: " + playerpoints);
        textViewComputer.setText("Computer: " + computerpoints);
    }

    private void resetBoard(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }

        roundCount = 0;
        playerTurn = true;
    }

    private void resetGame(){
        playerpoints = 0;
        computerpoints = 0;
        updatePointsText();
        resetBoard();
    }

    private String RandomMove(String[][] field){
        String RowAndColumn = "";
        int Row = Rand.nextInt(3);
        int Column = Rand.nextInt(3);
        if (!(field[Row][Column].equals(""))) {
            RowAndColumn = RandomMove(field);
        }
        else{
            RowAndColumn = String.valueOf(Row) + "," + String.valueOf(Column);
        }
        return RowAndColumn;
    }

    private void WhoHasWon(){
        if (checkForWin()) {
            if (playerTurn) {
                playerwins();
            }
            else {
                computerwins();
            }
        }
        else if (roundCount == 9) {
            draw();
        }
        else {
            playerTurn = !playerTurn;
        }

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("roundCount", roundCount);
        outState.putInt("playerpoints", playerpoints);
        outState.putInt("computerpoints", computerpoints);
        outState.putBoolean("playerTurn", playerTurn);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        roundCount = savedInstanceState.getInt("roundCount");
        playerpoints = savedInstanceState.getInt("playerpoints");
        computerpoints = savedInstanceState.getInt("computerpoints");
        playerTurn = savedInstanceState.getBoolean("playerTurn");
    }

}