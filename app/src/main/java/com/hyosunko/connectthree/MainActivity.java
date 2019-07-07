package com.hyosunko.connectthree;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.GridLayout;

public class MainActivity extends AppCompatActivity {
    // 0: yellow, 1: red
    int activePlayer = 0;
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6},{1, 4, 7}, {2, 5, 8}, {0, 4, 8},{2, 4, 6}};
    boolean gameActive = true;
    int gameCounter = 0;
    int currentPlayer= 0;
    int yellowScore = 0;
    int redScore = 0;

    public void dropIn (View view){
        ImageView counter = (ImageView) view;
        final ImageView yellowTurnImage = (ImageView) findViewById(R.id.yellowTurnImage);
        final ImageView redTurnImage = (ImageView) findViewById(R.id.redTurnImage);
        TextView yellowScoreText = (TextView) findViewById(R.id.yellowScoreText);
        TextView redScoreText = (TextView) findViewById(R.id.redScoreText);

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if(gameState[tappedCounter]==2 && gameActive && gameCounter <= 9) {

            gameState[tappedCounter] = activePlayer;

            counter.setTranslationY(-1500);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                yellowTurnImage.animate().alpha(0).setDuration(0);
                redTurnImage.animate().alpha(1).setDuration(3000);
//                yellowTurnImage.setVisibility(View.INVISIBLE);
//                redTurnImage.setVisibility(View.VISIBLE);
//                redTurnImage.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        redTurnImage.setVisibility(View.VISIBLE);
//                    }
//                }, 1000);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                yellowTurnImage.animate().alpha(1).setDuration(3000);
                redTurnImage.animate().alpha(0).setDuration(0);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1500).rotation(3600).setDuration(1000);
            gameCounter ++;
            currentPlayer = activePlayer;
            String winner = "";
            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {

                    gameActive = false;
                    if (activePlayer == 1) {
                        winner = "Yellow has won";
                        yellowTurnImage.animate().alpha(1).setDuration(0);
                        redTurnImage.animate().alpha(0).setDuration(0);
                        yellowScore++;
                        yellowScoreText.setText(String.valueOf(yellowScore));
                        currentPlayer = 1;
                    } else {
                        winner = "Red has won";
                        yellowTurnImage.animate().alpha(0).setDuration(0);
                        redTurnImage.animate().alpha(1).setDuration(0);
                        redScore++;
                        redScoreText.setText(String.valueOf(redScore));
                        currentPlayer= 0;
                    }

                    Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
                    TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
                    winnerTextView.setText(winner);

                    playAgainButton.setVisibility(View.VISIBLE);
                    winnerTextView.setVisibility(View.VISIBLE);
                } else if (gameCounter >=9 && gameActive){
                    winner = "Draw";
                    Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
                    TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
                    winnerTextView.setText(winner);

                    playAgainButton.setVisibility(View.VISIBLE);
                    winnerTextView.setVisibility(View.VISIBLE);
                    yellowTurnImage.animate().alpha(0).setDuration(0);
                    redTurnImage.animate().alpha(0).setDuration(0);
                }
            }
        }
    }

    public void playAgain(View view){
        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);

        ImageView yellowTurnImage = (ImageView) findViewById(R.id.yellowTurnImage);
        ImageView redTurnImage = (ImageView) findViewById(R.id.redTurnImage);

        playAgainButton.setVisibility(View.INVISIBLE);
        winnerTextView.setVisibility(View.INVISIBLE);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        for(int i=0; i<gridLayout.getChildCount(); i++){
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }

        for (int i=0; i<gameState.length; i++){
            gameState[i] = 2;
        }

        if(redTurnImage.getAlpha() == 1){
            activePlayer= 0;
                yellowTurnImage.animate().alpha(1).setDuration(0);
                redTurnImage.animate().alpha(0).setDuration(0);
            } else if (yellowTurnImage.getAlpha() == 1){
            activePlayer = 1;
                yellowTurnImage.animate().alpha(0).setDuration(0);
                redTurnImage.animate().alpha(1).setDuration(0);
        } else {
            activePlayer = currentPlayer;
            if(activePlayer == 0){
                yellowTurnImage.animate().alpha(0).setDuration(0);
                redTurnImage.animate().alpha(1).setDuration(0);
            } else {
                yellowTurnImage.animate().alpha(1).setDuration(0);
                redTurnImage.animate().alpha(0).setDuration(0);
            }
        }
        gameActive = true;
        gameCounter = 0;
    }

    public void resetScore(View view){
        TextView yellowScoreText = (TextView) findViewById(R.id.yellowScoreText);
        TextView redScoreText = (TextView) findViewById(R.id.redScoreText);
        yellowScore = 0;
        redScore = 0;
        yellowScoreText.setText(String.valueOf(yellowScore));
        redScoreText.setText(String.valueOf(redScore));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    ImageView yellowTurnImage = (ImageView) findViewById(R.id.yellowTurnImage);
    ImageView redTurnImage = (ImageView) findViewById(R.id.redTurnImage);

    redTurnImage.animate().alpha(0).setDuration(0);
    yellowTurnImage.animate().alpha(1).setDuration(0);
    }
}
