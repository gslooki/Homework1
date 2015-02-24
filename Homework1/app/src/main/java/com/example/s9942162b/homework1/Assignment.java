package com.example.s9942162b.homework1;

import android.widget.TextView;

/**
 * Created by S9942162B on 2/24/2015.
 */


public class Assignment {
    public int getmAMarks() {
        return mAMarks;
    }

    private int mAMarks;
    private TextView mAName;
    public Assignment(TextView name, int marks, int total, int percentage){
        mAMarks = marks/total *percentage;
        mAName = name;

    }
}
