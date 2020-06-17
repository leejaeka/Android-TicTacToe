package com.jaekanglee.tictacktoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    // 0 = dice 1 = pattern
    int activePlayer = 0;
    int[] gameState = {-1,-1,-1,-1,-1,-1,-1,-1,-1};
    int[][] winningPos = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    public void dropIn(View view){
        //drop animation
        ImageView counter = (ImageView)view;
        String tapPosition = counter.getTag().toString();
        if (gameState[Integer.parseInt(tapPosition)]== -1){
            counter.setTranslationY(-1000f);
            if (activePlayer==0){
                counter.setImageResource(R.drawable.blackchip1);
                activePlayer = 1;
                gameState[Integer.parseInt(tapPosition)] = 0;
            }else{
                counter.setImageResource(R.drawable.blackchip2);
                activePlayer = 0;
                gameState[Integer.parseInt(tapPosition)] = 1;
            }
            counter.animate().translationYBy(1000f).rotation(180).setDuration(300);
            for (int[] winPos:winningPos){
                if (gameState[winPos[0]]!= -1&&gameState[winPos[0]]==gameState[winPos[1]]&&gameState[winPos[1]]==gameState[winPos[2]]){
                    gameEnd(gameState[winPos[0]]);
                }
            }
        } else {
            boolean boolie = false;
            for (int i : gameState){
                if (i==-1){
                    boolie = true;
                    break;
                }
            }
            if (!boolie){
                System.out.println("DRAW");

            }
        }
    }
    public void gameEnd(int numb){
        System.out.println("PLAYER "+(numb+1)+" WINS");
    }
    public void resetBoard(View view){
        activePlayer = 0;
        androidx.gridlayout.widget.GridLayout grid = findViewById(R.id.gridLayout);
        for (int i=0;i<=8;i++ ){
            gameState[i] = -1;
            ((ImageView) grid.getChildAt(i)).setImageResource(0);
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}