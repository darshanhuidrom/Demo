package com.integra.demo;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class MyJobService extends JobService {

    private static final String TAG = "MyJobService";
    private boolean isWorking = false;
    private boolean jobCancel = false;


    @Override
    public boolean onStartJob(JobParameters params) {
        Log.d(">>>>>>>>", "Job started");
        startWorkOnNewThread(params);
        return isWorking;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.d(">>>>>>>>", "Job cancelled before being completed");
        jobCancel=true;
        boolean needReschedule=true;
        jobFinished(params,needReschedule);

        return needReschedule;
    }

    private void startWorkOnNewThread(final JobParameters jobParameters) {
        new Thread(new Runnable() {
            @Override
            public void run() {

                doWork(jobParameters);
            }
        }).start();
    }

    private void doWork(JobParameters jobParameters) {
        for (int i = 0; i < 1000; i++) {
            if (jobCancel) {
                return;
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Log.d(">>>>>>>>","Job finished");
            isWorking=false;
            boolean needReschedule=false;
            jobFinished(jobParameters,needReschedule);

        }
    }
}
