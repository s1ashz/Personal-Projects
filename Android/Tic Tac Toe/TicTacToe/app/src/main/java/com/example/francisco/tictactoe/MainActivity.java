package com.example.francisco.tictactoe;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    GridLayout gameGrid;

    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;

    private String button1String;
    private String button2String;
    private String button3String;
    private String button4String;
    private String button5String;
    private String button6String;
    private String button7String;
    private String button8String;
    private String button9String;

    private List<Button> buttonList;
    private Button currentButtonClick;

    private Player player1;
    private Player player2;
    private Player currentPlayer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gameGrid = findViewById(R.id.gameGrid);

        defineButtonsVariables();
        resetBoard();
        resetPlayers();

    }


    public void buttonClickAtack(View view) {

        currentButtonClick = (Button) view;
        currentButtonClick.setText(currentPlayer.getMark());
        currentButtonClick.setClickable(false);

        uploadButtonStrings();
        checkVictory();

        changePlayerTurn();
    }

    public void checkVictory() {

        if ( checkRows() )      if ( displayMessage(currentPlayer, true) ) return;
        if ( checkColumns() )   if ( displayMessage(currentPlayer, true) ) return;
        if ( checkDiagonal() )  if ( displayMessage(currentPlayer, true) ) return;
        if ( checkDraw() ) displayMessage(currentPlayer, false);

    }

    public boolean displayMessage(Player currentPlayer, boolean victory) {
        AlertDialog.Builder victoryMessage = new AlertDialog.Builder(MainActivity.this);
        if (victory) {
            victoryMessage.setMessage("Player " + currentPlayer.getMark() + " WINS!!!!");
        } else {
            victoryMessage.setMessage("DRAW GAME!! Press New Game to play again!!");
        }
        victoryMessage.setCancelable(true);

        /*
        victoryMessage.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        victoryMessage.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        */

        victoryMessage.show();
        enableAllButtons(false);

        return true;

    }

    public boolean checkRows() {
        return  !button1String.equals("") && button1String.equals(button2String) && button1String.equals(button3String) ||
                !button4String.equals("") && button4String.equals(button5String) && button4String.equals(button6String) ||
                !button7String.equals("") && button7String.equals(button8String) && button7String.equals(button9String);
    }

    public boolean checkColumns() {
        return  !button1String.equals("") && button1String.equals(button4String) && button1String.equals(button7String) ||
                !button2String.equals("") && button2String.equals(button5String) && button2String.equals(button8String) ||
                !button3String.equals("") && button3String.equals(button6String) && button3String.equals(button9String);
    }

    public boolean checkDiagonal() {
        return  !button5String.equals("") && button5String.equals(button1String) && button5String.equals(button9String) ||
                !button5String.equals("") && button5String.equals(button3String) && button5String.equals(button7String);
    }

    public boolean checkDraw() {
        for ( Button b : buttonList ) {
            if (b.getText().toString().equals("")) return false;
        }
        return true;
    }

    public void changePlayerTurn() {
        if ( currentPlayer == player1 ) {
            setCurrentPlayer(player2);
        } else {
            setCurrentPlayer(player1);
        }
    }


    public void resetPlayers() {
        player1 = new Player("O");
        player2 = new Player("X");
        setCurrentPlayer(player1);
    }

    public void resetBoard() {
        for (Button b : buttonList) {
            b.setText("");
        }
    }

    public void uploadButtonStrings() {
        button1String = button1.getText().toString();
        button2String = button2.getText().toString();
        button3String = button3.getText().toString();
        button4String = button4.getText().toString();
        button5String = button5.getText().toString();
        button6String = button6.getText().toString();
        button7String = button7.getText().toString();
        button8String = button8.getText().toString();
        button9String = button9.getText().toString();
    }

    public void defineButtonsVariables() {
        button1 = (Button) findViewById(R.id.buttonGrid1);
        button2 = (Button) findViewById(R.id.buttonGrid2);
        button3 = (Button) findViewById(R.id.buttonGrid3);
        button4 = (Button) findViewById(R.id.buttonGrid4);
        button5 = (Button) findViewById(R.id.buttonGrid5);
        button6 = (Button) findViewById(R.id.buttonGrid6);
        button7 = (Button) findViewById(R.id.buttonGrid7);
        button8 = (Button) findViewById(R.id.buttonGrid8);
        button9 = (Button) findViewById(R.id.buttonGrid9);

        buttonList = new ArrayList<>();
        buttonList.add(button1);
        buttonList.add(button2);
        buttonList.add(button3);
        buttonList.add(button4);
        buttonList.add(button5);
        buttonList.add(button6);
        buttonList.add(button7);
        buttonList.add(button8);
        buttonList.add(button9);

    }

    public void enableAllButtons(boolean victory) {
        for (Button b : buttonList) {
            b.setClickable(victory);
        }

    }

    public void newGameClick(View view) {
        resetBoard();
        resetPlayers();
        enableAllButtons(true);
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

}
