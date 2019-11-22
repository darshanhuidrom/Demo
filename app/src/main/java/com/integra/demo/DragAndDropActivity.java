package com.integra.demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.ClipData;
import android.content.ClipDescription;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class DragAndDropActivity extends AppCompatActivity implements View.OnTouchListener, View.OnDragListener {

    Button btnYes, btnNo;
    ImageView imgDestination;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drag_drop_activity);

        btnYes = findViewById(R.id.btnYes);
        btnNo = findViewById(R.id.btnNo);
        imgDestination = findViewById(R.id.imgDestination);

        btnYes.setOnTouchListener(this);
        btnNo.setOnTouchListener(this);
        imgDestination.setOnDragListener(this);
        int[] arry = {50, 30, 60, 40, 10};
        int last = arry.length - 1;
        int key = 50;
        //binarySearch(arry,0,last,key);
     //   bubbleSort(arry);


    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {


        View.DragShadowBuilder mShadow = new View.DragShadowBuilder(v);
        ClipData.Item item = new ClipData.Item(v.getTag().toString());
        String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};
        ClipData data = new ClipData(v.getTag().toString(), mimeTypes, item);

        switch (v.getId()) {
            case R.id.btnYes:


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    v.startDragAndDrop(data, mShadow, null, 0);
                } else {
                    v.startDrag(data, mShadow, null, 0);
                }

                break;
            case R.id.btnNo:

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    v.startDragAndDrop(data, mShadow, null, 0);
                } else {
                    v.startDrag(data, mShadow, null, 0);
                }
                break;

        }

        return false;
    }

    public static void binarySearch(int arr[], int first, int last, int key) {
        int mid = (first + last) / 2;
        while (first <= last) {
            if (arr[mid] < key) {
                first = mid + 1;
            } else if (arr[mid] == key) {
                System.out.println("Element is found at index: " + mid);
                break;
            } else {
                last = mid - 1;
            }
            mid = (first + last) / 2;
        }
        if (first > last) {
            System.out.println("Element is not found!");
        }
    }

    public static void bubbleSort(int[] arr) {
        printArray(arr, "Unsorted Array");
        int last = arr.length;
        for (int i = 0; i < last; i++) {

            for (int j = 0; j < last - i - 1; j++) {
                int no = arr[j];
                int adjacentNo = arr[j + 1];
                if (no > adjacentNo) {
                    int temp = no;
                    no = adjacentNo;
                    adjacentNo = temp;
                    arr[j] = no;
                    arr[j + 1] = adjacentNo;
                }
            }

        }
        printArray(arr, "Sorted Array");

    }

    void insertionSort(int arr[])
    {
        printArray(arr,"Unsorted Array");
        int i, key, j;
        for (i = 1; i < arr.length; i++)
        {
            key = arr[i];
            j = i - 1;

        /* Move elements of arr[0..i-1], that are
        greater than key, to one position ahead
        of their current position */
            while (j >= 0 && arr[j] > key)
            {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
        printArray(arr,"Sorted Array");
    }

    public static void printArray(int[] array, String msg) {
        StringBuilder builder = new StringBuilder(msg);

        for (int i = 0; i < array.length; i++) {
            if (i == 0) {
                builder.append("[");
            }
            builder.append(array[i]);
            if (i < array.length - 1) {
                builder.append(",");
            } else {
                builder.append("]");
            }
        }
        Log.d(">>>>>>>>", builder.toString());

    }

    @Override
    public boolean onDrag(View v, DragEvent event) {
        switch (event.getAction()) {
            case DragEvent.ACTION_DRAG_STARTED:
                ((ImageView) v).setColorFilter(Color.YELLOW);
                v.invalidate();
                return true;

            case DragEvent.ACTION_DRAG_ENTERED:

                String clipData = event.getClipDescription().getLabel().toString();
                switch (clipData) {
                    case "YES":
                        ((ImageView) v).setColorFilter(ContextCompat.getColor(DragAndDropActivity.this, R.color.colorPrimary), android.graphics.PorterDuff.Mode.MULTIPLY);
                        break;
                    case "NO":
                        ((ImageView) v).setColorFilter(ContextCompat.getColor(DragAndDropActivity.this, R.color.colorAccent), android.graphics.PorterDuff.Mode.MULTIPLY);
                        break;
                }

                v.invalidate();
                return true;

            case DragEvent.ACTION_DRAG_LOCATION:
                return true;

            case DragEvent.ACTION_DRAG_EXITED:

                ((ImageView) v).clearColorFilter();
                ((ImageView) v).setColorFilter(Color.YELLOW);

                v.invalidate();
                return true;

            case DragEvent.ACTION_DROP:


                clipData = event.getClipDescription().getLabel().toString();
                Toast.makeText(getApplicationContext(), clipData, Toast.LENGTH_SHORT).show();

                v.invalidate();
                return true;

            case DragEvent.ACTION_DRAG_ENDED:


                ((ImageView) v).clearColorFilter();
                if (event.getResult()) {
                    Toast.makeText(DragAndDropActivity.this, "Awesome!", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(DragAndDropActivity.this, "Aw Snap! Try dropping it again", Toast.LENGTH_SHORT).show();
                }
                return true;

            default:
                return false;
        }
    }
}
