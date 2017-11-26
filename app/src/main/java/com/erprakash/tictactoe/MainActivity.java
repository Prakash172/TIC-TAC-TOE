package com.erprakash.tictactoe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    ImageView image;
    Button btn;
    static float currentRotation = 0.0f;
    static int counter =1;
    int activePlayer = 0;
    int[] gameState={2,2,2,2,2,2,2,2,2};// 2 denotes un played state
    int winingPos[][] = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    TextView tv;
    LinearLayout layout ;
    GridLayout gridLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView)findViewById(R.id.result);
        btn=(Button)findViewById(R.id.play);
        //    grid =(ImageView)findViewById(R.id.grid);
        gridLayout =(GridLayout)findViewById(R.id.board);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter = 1;
                activePlayer =0;
                layout.setVisibility(View.INVISIBLE);
                for(int i = 0; i < gameState.length; i++ )
                    gameState[i]=2;

                for(int i = 0; i<gridLayout.getChildCount(); i++)
                {
                    ((ImageView)(gridLayout.getChildAt(i))).setImageResource(0);
                }
            }
        });
        layout = (LinearLayout)findViewById(R.id.winLayout);

    }

    public void onClick(View view) {

        image = (ImageView) view;
        int tag = Integer.parseInt(image.getTag().toString());

        if (gameState[tag] == 2) {
            counter++;
            gameState[tag] = activePlayer;
            image.setTranslationY(-1000f);
            if (activePlayer == 0) {
                image.setImageResource(R.drawable.plus);
                activePlayer = 1;
            } else {
                image.setImageResource(R.drawable.minus);
                activePlayer = 0;
            }
            image.animate().translationYBy(1000f).rotation(currentRotation+360f).setDuration(500);
            for(int win[]:winingPos){
                if((gameState[win[0]] == gameState[win[1]])&&(gameState[win[1]]==gameState[win[2]])
                        &&(gameState[win[0]]!=2)){
                    if(gameState[win[0]]==0&&gameState[win[1]]==0&&gameState[win[2]]==0)
                    {
                        Animation slideUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);

                        if(layout.getVisibility()==View.INVISIBLE){
                            layout.startAnimation(slideUp);
                            layout.setVisibility(View.VISIBLE);
                            layout.animate().setDuration(1000);
                            tv.setText(String.valueOf("Positive wins"));
                        }
                    }
                    else if(gameState[win[0]]==1&&gameState[win[1]]==1&&gameState[win[2]]==1)
                    {
                        Animation slideDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);

                        if(layout.getVisibility()==View.INVISIBLE){
                            layout.startAnimation(slideDown);
                            layout.setVisibility(View.VISIBLE);
                            layout.animate().setDuration(1000);
                            tv.setText(String.valueOf("Negative wins"));
                        }
                    }

                    for(int i =0 ; i <9;i++)
                        gameState[i]=0;
                }
            }

        }
        if (counter == 10){
            Animation slideDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
            if(layout.getVisibility()==View.INVISIBLE){
                layout.startAnimation(slideDown);
                layout.setVisibility(View.VISIBLE);
                layout.animate().setDuration(1000);
                tv.setText(String.valueOf("Match Draw"));
            }
        }

    }
}
