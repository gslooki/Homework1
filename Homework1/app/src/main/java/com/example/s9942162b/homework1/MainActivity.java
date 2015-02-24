package com.example.s9942162b.homework1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends Activity {
    private TextView mAssignmentName;
    private TextView mNAssignmentT;

    private int mMarks;
    private int mTotal;
    private int mPercentage;
    private int mNAssignments = 0;

    private EditText mMarksE;
    private EditText mTotalE;
    private EditText mPercentageE;
    private EditText mAssignmentNameE;

    private Button mButtonAdd;
    private Button mButtonCalculate;
    private int mTotalPercentage = 0;

    ArrayList<Assignment> mAssignmentArray = new ArrayList<Assignment>();

    private Toast mToast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //casting buttons
        mButtonAdd = (Button)findViewById(R.id.button_add);
        mButtonCalculate = (Button)findViewById(R.id.button_calculate);
        mButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setvars();
                //calculating total percentage
                mTotalPercentage += mPercentage;
                //mNAssignments += 1;
                //mNAssignmentT.setText(String.valueOf(mNAssignments));
                //saving important info into array
                mAssignmentArray.add(new Assignment(mAssignmentName,mMarks,mTotal,mPercentage));
                //reset edittexts
                reset_all_E();

                //increasing the number of assignments added


            }

        });
        mButtonCalculate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                setvars();
                mTotalPercentage += mPercentage;
                //mNAssignments += 1;
                //mNAssignmentT.setText(String.valueOf(mNAssignments));
                mAssignmentArray.add(new Assignment(mAssignmentName,mMarks,mTotal,mPercentage));

                if(mTotalPercentage < 100){
                    showToast("Insufficient Percentage");
                }
                else {
                    if (mTotalPercentage > 100) {
                        showToast("Larger Percentage - Please Restart App");
                    } else {
                        Intent i = new Intent(MainActivity.this, ResultsActivity.class);
                        int score = 0;
                        for (int n = 1; n - 1 < mAssignmentArray.size(); n++) {
                            Assignment tempassign = mAssignmentArray.get(n);
                            int p = tempassign.getmAMarks();
                            score += p;
                        }


                        startActivity(i);
                    }
                }

            }
        });
    }

    private int getGPA(int score){
        double GPA;
        if (score < 30){
            GPA = 0.8;
        }

        else if (score < 40){
            GPA = 1.6;
        }

        else if (score < 50){
            GPA = 2.0;
        }
        else if (score < 60){
            GPA = 2.4;
        }
        else if (score < 65){
            GPA = 2.8;
        }
        else if (score < 70){
            GPA = 3.2;
        }
        else if (score < 80){
            GPA = 3.6;
        }
        else
    }
    private void showToast(String textToShow) {
        if (mToast != null) {
            mToast.cancel();
        }
        mToast = Toast.makeText(MainActivity.this, textToShow, Toast.LENGTH_SHORT);
        mToast.show();
    }

    private void reset_all_E(){
        resetE(mMarksE);
        resetE(mTotalE);
        resetE(mPercentageE);
        resetE(mAssignmentNameE);
    }

    private void setvars(){
        //casting TextView
        mAssignmentName = mAssignmentNameE;

        //casting edittexts
        mAssignmentNameE = (EditText)findViewById(R.id.editTextAssignment);
        mMarksE = (EditText)findViewById(R.id.editTextMarks);
        mTotalE = (EditText)findViewById(R.id.editTextTotal);
        mPercentageE = (EditText)findViewById(R.id.editTextPercentage);

        //casting ints
        mMarks = makeinteger(mMarksE);
        mTotal = makeinteger(mTotalE);
        mPercentage = makeinteger(mPercentageE);
    }

    private EditText resetE(EditText text){
        text.setText("");
        return text;
    }

    private int makeinteger(EditText text){
         return Integer.parseInt(text.getText().toString());
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
