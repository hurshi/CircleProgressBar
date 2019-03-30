package io.github.hurshi.CircleProgressBar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;

import io.github.hurshi.circleprogressbar.core.CircleProgressBar;

public class MainActivity extends AppCompatActivity {

    private SeekBar seekBar;
    private CircleProgressBar circleProgressBar1;
    private CircleProgressBar circleProgressBar2;
    private CircleProgressBar circleProgressBar3;
    private CircleProgressBar circleProgressBar4;
    private CircleProgressBar circleProgressBar5;
    private CircleProgressBar circleProgressBar6;
    private CircleProgressBar circleProgressBar7;
    private CircleProgressBar circleProgressBar8;
    private CircleProgressBar circleProgressBar9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        initViews();


    }


    private void initViews() {
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                circleProgressBar1.setProgress(progress);
                circleProgressBar2.setProgress(progress);
                circleProgressBar3.setProgress(progress);
                circleProgressBar4.setProgress(progress);
                circleProgressBar5.setProgress(progress);
                circleProgressBar6.setProgress(progress);
                circleProgressBar7.setProgress(progress);
                circleProgressBar8.setProgress(progress);
                circleProgressBar9.setProgress(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void findViews() {
        seekBar = (SeekBar) findViewById(R.id.seekbar);
        circleProgressBar1 = (CircleProgressBar) findViewById(R.id.progressbar1);
        circleProgressBar2 = (CircleProgressBar) findViewById(R.id.progressbar2);
        circleProgressBar3 = (CircleProgressBar) findViewById(R.id.progressbar3);
        circleProgressBar4 = (CircleProgressBar) findViewById(R.id.progressbar4);
        circleProgressBar5 = (CircleProgressBar) findViewById(R.id.progressbar5);
        circleProgressBar6 = (CircleProgressBar) findViewById(R.id.progressbar6);
        circleProgressBar7 = (CircleProgressBar) findViewById(R.id.progressbar7);
        circleProgressBar8 = (CircleProgressBar) findViewById(R.id.progressbar8);
        circleProgressBar9 = (CircleProgressBar) findViewById(R.id.progressbar9);
    }
}
