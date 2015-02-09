package com.raizlabs.android.databasecomparison.sprinkles;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.raizlabs.android.databasecomparison.Generator;
import com.raizlabs.android.databasecomparison.MainActivity;
import com.raizlabs.android.databasecomparison.Saver;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.structure.Model;

import java.util.List;

import se.emilsjolander.sprinkles.Query;
import se.emilsjolander.sprinkles.Transaction;

/**
 * Description:
 */
public class SprinklesTester {
    public static void testSprinklesAddressItems(Context context) {
        SQLiteOpenHelper openHelper = new SQLiteOpenHelper(context, "sprinkles.db", null, 2) {
            @Override
            public void onCreate(SQLiteDatabase db) {
            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            }
        };
        openHelper.getWritableDatabase().execSQL("CREATE TABLE IF NOT EXISTS AddressItem(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, " +
                "address TEXT, " +
                "city TEXT, " +
                "state TEXT, " +
                "phone INTEGER, " +
                "addressBook INTEGER)");
        deleteSprinklesTables(openHelper, "AddressItem");

        List<AddressItem> sprinkleModels =
                Generator.getAddresses(AddressItem.class, MainActivity.LOOP_COUNT);

        long startTime = System.currentTimeMillis();
        // Reuse method so we don't have to write
        Transaction transaction = new Transaction();
        try {
            Saver.saveAll(sprinkleModels);
            transaction.setSuccessful(true);
        } finally {
            transaction.finish();
        }
        MainActivity.logTime(startTime, "Sprinkles");

        startTime = System.currentTimeMillis();
        sprinkleModels = Query.all(AddressItem.class).get().asList();
        MainActivity.logTime(startTime, "Sprinkles Load");

        deleteSprinklesTables(openHelper, "AddressItem");
    }


    private static void deleteSprinklesTables(SQLiteOpenHelper openHelper, String...tables) {
        for(String table: tables) {
            openHelper.getWritableDatabase().delete(table, null, null);
        }
    }
}
