package io.github.hurshi.CircleProgressBar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
                setProcess(progress,
                        circleProgressBar1,
                        circleProgressBar2,
                        circleProgressBar3,
                        circleProgressBar4,
                        circleProgressBar5,
                        circleProgressBar6,
                        circleProgressBar7,
                        circleProgressBar8,
                        circleProgressBar9);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        circleProgressBar6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (circleProgressBar6.isLoading())
                    circleProgressBar6.stopLoading();
                else circleProgressBar6.loading();
            }
        });

        setClickLoading(circleProgressBar1,
                circleProgressBar2,
                circleProgressBar3,
                circleProgressBar4,
                circleProgressBar5,
                circleProgressBar6,
                circleProgressBar7,
                circleProgressBar8,
                circleProgressBar9);

        circleProgressBar6.loading();
    }

    private void setProcess(int progress, CircleProgressBar... circleProgressBars) {
        for (CircleProgressBar c : circleProgressBars) {
            c.setProgress(progress);
        }
    }

    private void setClickLoading(CircleProgressBar... circleProgressBars) {
        for (final CircleProgressBar c : circleProgressBars) {
            c.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (c.isLoading()) c.stopLoading();
                    else c.loading();
                }
            });
        }

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
