package com.example.yui06.camptapmole;

import android.media.Image;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.sql.Time;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends ActionBarActivity {

    TextView scoreText;
    TextView timeText;

    int[] imageResource={
            R.id.imageView,R.id.imageView1,R.id.imageView2,
            R.id.imageView3,R.id.imageView4,R.id.imageView5,
            R.id.imageView6,R.id.imageView7,R.id.imageView8,
            R.id.imageView9,R.id.imageView10,R.id.imageView11
    };

    Mole[] moles;

    int time;
    int score;

    Timer timer;
    TimerTask timerTask;
    Handler h;
    Random random= new Random();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoreText=(TextView)findViewById(R.id.textView);
        timeText=(TextView)findViewById(R.id.textView2);

        moles=new Mole[12];
        for (int i=0;i<12;i++){
            ImageView imageView=(ImageView)findViewById(imageResource[i]);
            moles[i]=new Mole(imageView);
        }

        h=new Handler();

    }

    public void start(View v){

        score=0;
        time=60;
        timeText.setText(String.valueOf(time));
        scoreText.setText(String.valueOf(score));

        timer=new Timer();
        timerTask=new TimerTask() {
            @Override
            public void run() {
                int r=random.nextInt(12);
                moles[r].start();

                time=time-1;
                timeText.setText(String.valueOf(time));

                if(time<=0){
                    timer.cancel();
                }

            }

        };
        timer.schedule(timerTask,0,1000);

    }

    public void tapMole(View v){
        String tag_str=(String)v.getTag();
        int tag_int=Integer.valueOf(tag_str);
        score+=moles[tag_int].tapMole();
        scoreText.setText(String.valueOf(score));


    }
}
