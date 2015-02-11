package com.raizlabs.android.databasecomparison;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.raizlabs.android.databasecomparison.activeandroid.AATester;
import com.raizlabs.android.databasecomparison.dbflow.DBFlowTester;
import com.raizlabs.android.databasecomparison.greendao.GreenDaoTester;
import com.raizlabs.android.databasecomparison.sprinkles.SprinklesTester;
import com.raizlabs.android.databasecomparison.sugar.SugarTester;


public class MainActivity extends Activity {

    public static final int LOOP_COUNT = 25000;

    public static final int ADDRESS_BOOK_COUNT = 50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.simple).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GreenDaoTester.testAddressItems(MainActivity.this);
                DBFlowTester.testDBFlowAddressItems();
                SprinklesTester.testSprinklesAddressItems(MainActivity.this);
                AATester.testAAAddressItems();
                SugarTester.testSugarAddressItems();
            }

        });

        findViewById(R.id.complex).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GreenDaoTester.testAddressBooks(MainActivity.this);
                DBFlowTester.testDBFlowAddressBooks();
                SugarTester.testSugarAddressBooks();
                AATester.testAAAddressBooks();
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();

    }

    public static void logTime(long startTime, String name) {
        Log.e(MainActivity.class.getSimpleName(), name + " took: " + (System.currentTimeMillis() - startTime));
    }
}
