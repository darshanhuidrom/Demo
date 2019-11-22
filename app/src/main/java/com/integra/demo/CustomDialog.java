package com.integra.demo;

import android.app.Dialog;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

public class CustomDialog extends Dialog {
    public CustomDialog(@NonNull Context context) {
        super(context);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);
        Button button = findViewById(R.id.btn_start);
        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"start is clicked",Toast.LENGTH_SHORT).show();

                ComponentName componentName = new ComponentName(getContext(),MyJobService.class);
                JobInfo jobInfo = new JobInfo.Builder(12,componentName).setRequiresCharging(true).
                        setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED).build();

                JobScheduler scheduler= (JobScheduler) getContext().getSystemService(Context.JOB_SCHEDULER_SERVICE);
                int resultCode = scheduler.schedule(jobInfo);
                if(resultCode==JobScheduler.RESULT_SUCCESS){
                    Log.d(">>>>>>>>","Job Scheduled");
                }
                else{
                    Log.d(">>>>>>>>","Job not Scheduled");
                }
            }
        });
    }
}
