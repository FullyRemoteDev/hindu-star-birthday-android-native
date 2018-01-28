package com.codablepixel.starbirthdaycalculator;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.lang.reflect.Field;
import java.util.Calendar;


public class MainActivity extends ActionBarActivity {

    private static Context appContext;
    private static final String TAG = "SBirthday";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appContext = getApplicationContext();

        new CopyAssetFiles(".*\\.se1", appContext).copy();

        Button calculate = (Button) findViewById(R.id.calculate);
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StarBirthday sBirthday = new StarBirthday();
                int sBresults[] = sBirthday.calculate();

                TextView starBirthday = (TextView) findViewById(R.id.starBirthday);
                starBirthday.setText(sBresults[0] + " - " + sBresults[1] + " - " + sBresults[2]);


            }
        });


        final EditText sbYear = (EditText) findViewById(R.id.sbYear);
        sbYear.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE) {

                    int sbYearValue = Integer.parseInt((sbYear.getText().toString()));
                    StarBirthday.setSbcYear(sbYearValue);

                    handled = true;
                }
                return handled;
            }
        });



    }

    public static Context getContext() {
        return appContext;
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


    // DatePicker Dialog Code
    public void showDatePickerDialog(View v) {
        DialogFragment newDOBFragment = new DatePickerFragment();
        newDOBFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            StarBirthday.setDobYear(year);
            StarBirthday.setDobMonth(month + 1); // the month value returns between 0 - 11 to conform with Calendar. So add 1.
            StarBirthday.setDobDay(day);
        }
    }


    // TimePicker Dialog Code
    public void showTimePickerDialog(View v) {
        DialogFragment newTOBFragment = new TimePickerFragment();
        newTOBFragment.show(getSupportFragmentManager(), "timePicker");
    }

    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            StarBirthday.setDobHours(hourOfDay);
            StarBirthday.setDobMinutes(minute);
        }
    }


}
