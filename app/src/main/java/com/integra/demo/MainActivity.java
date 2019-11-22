package com.integra.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String ss= "Karnataka#Bangalore~Mysore&Tamil Nadu#Chennei~Madurai";


    }

    public void show(View view) {
        CustomDialog dialog = new CustomDialog(this);
        dialog.show();

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        lp.gravity = Gravity.CENTER;
        lp.gravity = Gravity.BOTTOM;
        lp.windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setAttributes(lp);
    }

}
