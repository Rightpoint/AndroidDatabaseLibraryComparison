package com.raizlabs.android.databasecomparison;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.raizlabs.android.databasecomparison.activeandroid.AATester;
import com.raizlabs.android.databasecomparison.dbflow.DBFlowTester;
import com.raizlabs.android.databasecomparison.greendao.GreenDaoTester;
import com.raizlabs.android.databasecomparison.ormlite.OrmLiteTester;
import com.raizlabs.android.databasecomparison.sprinkles.SprinklesTester;
import com.raizlabs.android.databasecomparison.sugar.SugarTester;


public class MainActivity extends Activity {

    public static final int LOOP_COUNT = 25000;

    public static final int ADDRESS_BOOK_COUNT = 50;

    private Button simpleTrialButton;
    private Button complexTrialButton;
    private TextView resultsLabel;
    private TextView resultsTextView;
    private static StringBuilder resultsStringBuilder = new StringBuilder();
    private static ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        simpleTrialButton = (Button) findViewById(R.id.simple);
        complexTrialButton = (Button) findViewById(R.id.complex);
        resultsLabel = (TextView) findViewById(R.id.resultsLabel);
        resultsTextView = (TextView) findViewById(R.id.results);
        progressBar = (ProgressBar) findViewById(R.id.progress);
        progressBar.setIndeterminate(true);
    }

    /**
     * Logs msec between start time and now
     * @param startTime relative to start time in msec
     * @param name string to log for event
     */
    public void logTime(long startTime, String name) {
        Log.e(MainActivity.class.getSimpleName(), name + " took: " + (System.currentTimeMillis() - startTime));
        resultsStringBuilder.append(name)
                .append(" took: ")
                .append(System.currentTimeMillis() - startTime)
                .append(" msec\n");
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                resultsTextView.setText(resultsStringBuilder.toString());
            }
        });
    }

    private void setBusyUI(boolean enabled) {
        if (enabled) {
            resultsStringBuilder.setLength(0);
            resultsLabel.setVisibility(View.VISIBLE);
            enableButtons(false);
            progressBar.setVisibility(View.VISIBLE);
        } else {
            enableButtons(true);
            progressBar.setVisibility(View.GONE);
        }
    }

    private void enableButtons(boolean enabled) {
        simpleTrialButton.setEnabled(enabled);
        complexTrialButton.setEnabled(enabled);
    }

    /**
     * runs simple benchmarks (onClick from R.id.simple)
     * @param v button view
     */
    public void runSimpleTrial(View v) {
        setBusyUI(true);
        final MainActivity mainActivity = this;
        new Thread(new Runnable() {
            @Override
            public void run() {
                OrmLiteTester.testAddressItems(mainActivity);
                GreenDaoTester.testAddressItems(mainActivity);
                DBFlowTester.testAddressItems(mainActivity);
                SprinklesTester.testAddressItems(mainActivity);
                AATester.testAddressItems(mainActivity);
                SugarTester.testAddressItems(mainActivity);
                mainActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mainActivity.setBusyUI(false);
                    }
                });
            }
        }).start();
    }

    /**
     * runs complex benchmarks (onClick from R.id.complex)
     * @param v button view
     */
    public void runComplexTrial(View v) {
        setBusyUI(true);
        final MainActivity mainActivity = this;
        new Thread(new Runnable() {
            @Override
            public void run() {
                OrmLiteTester.testAddressBooks(mainActivity);
                GreenDaoTester.testAddressBooks(mainActivity);
                DBFlowTester.testAddressBooks(mainActivity);
                SugarTester.testAddressBooks(mainActivity);
                AATester.testAddressBooks(mainActivity);
                mainActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mainActivity.setBusyUI(false);
                    }
                });
            }
        }).start();
    }
}
