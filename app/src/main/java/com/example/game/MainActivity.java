package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int active=0;
    String Winner;
    int[] gamingState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPosition = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    boolean gameactive= true;
    public void fun1(View view) {

        ImageView Cross = (ImageView) view;
//        Log.i("tag", Cross.getTag().toString());


        int tappedCounter = Integer.parseInt(Cross.getTag().toString());
        if (gamingState[tappedCounter] == 2 && gameactive == true) {
            gamingState[tappedCounter] = active;
            if (active == 0) {
                Cross.setTranslationX(-1000);
                Cross.setImageResource(R.drawable.cross);
                Cross.animate().translationXBy(1000).setDuration(100);
//            Cross.animate().translationYBy(1000).setDuration(100);
                active = 1;
            } else {
                Cross.setTranslationY(-1000);
                Cross.setImageResource(R.drawable.circle);
                Cross.animate().translationYBy(1000).setDuration(100);
//            Cross.animate().translationYBy(1000).setDuration(100);
                active = 0;
            }
            for (int[] winPosition : winningPosition) {
                if (gamingState[winPosition[0]] == gamingState[winPosition[1]] &&
                        gamingState[winPosition[1]] == gamingState[winPosition[2]] &&
                        gamingState[winPosition[0]] != 2) {
                    gameactive = false;
                    if (active == 1) {
                        Winner = "Twitter";
                    } else {
                        Winner = "Instagram";
                    }
                    TextView textView = (TextView) findViewById(R.id.textView);
                    textView.setText(Winner + " has Won! ");
                    Button playAgain = (Button) findViewById(R.id.button);
                    playAgain.setVisibility(view.VISIBLE);
                    textView.setVisibility(view.VISIBLE);
//                Toast.makeText(this, Winner+" has won", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
    public void play(View view){
        Button playAgain = (Button) findViewById(R.id.button);
        TextView textView = (TextView) findViewById(R.id.textView);
        playAgain.setVisibility(view.INVISIBLE);
        textView.setVisibility(view.INVISIBLE);
        GridLayout gridLayout= (GridLayout) findViewById(R.id.gridLayout);
        for(int i=0;i<gridLayout.getChildCount();i++){
            ImageView image= (ImageView) gridLayout.getChildAt(i);
            image.setImageDrawable(null);
        }
        for (int i=0;i<gamingState.length;i++){
            gamingState[i]=2;
        }
         active=0;
         gameactive= true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}