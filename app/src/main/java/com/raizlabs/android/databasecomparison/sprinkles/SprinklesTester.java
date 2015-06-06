package com.raizlabs.android.databasecomparison.sprinkles;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.raizlabs.android.databasecomparison.Generator;
import com.raizlabs.android.databasecomparison.MainActivity;
import com.raizlabs.android.databasecomparison.Saver;
import com.raizlabs.android.databasecomparison.events.LogTestDataEvent;

import java.util.Collection;
import java.util.List;

import de.greenrobot.event.EventBus;
import se.emilsjolander.sprinkles.ModelList;
import se.emilsjolander.sprinkles.Query;
import se.emilsjolander.sprinkles.Transaction;

/**
 * Description:
 */
public class SprinklesTester {
    public static final String FRAMEWORK_NAME = "Sprinkles";

    public static void testAddressItems(Context context) {
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

        Collection<AddressItem> sprinkleModels =
                Generator.getAddresses(AddressItem.class, MainActivity.LOOP_COUNT);

        long startTime = System.currentTimeMillis();
        // first copy everything into a model list so we can do a saveAll on it
        //NOTE: you'd think this would be faster than calling .save(trans) on each object, but it's not :-P
        ModelList<AddressItem> addressItemModelList = new ModelList<>();
        for (AddressItem addressItem : sprinkleModels) {
            addressItemModelList.add(addressItem);
        }
        // save everything in one transaction for best speed
        Transaction transaction = new Transaction();
        try {
            addressItemModelList.saveAll(transaction);
            transaction.setSuccessful(true);
        } finally {
            transaction.finish();
        }
        EventBus.getDefault().post(new LogTestDataEvent(startTime, FRAMEWORK_NAME, MainActivity.SAVE_TIME));

        startTime = System.currentTimeMillis();
        sprinkleModels = Query.all(AddressItem.class).get().asList();
        EventBus.getDefault().post(new LogTestDataEvent(startTime, FRAMEWORK_NAME, MainActivity.LOAD_TIME));

        deleteSprinklesTables(openHelper, "AddressItem");
    }


    private static void deleteSprinklesTables(SQLiteOpenHelper openHelper, String...tables) {
        for(String table: tables) {
            openHelper.getWritableDatabase().delete(table, null, null);
        }
    }

    /**
     * Not currently implemented but we need values to graph
     * @param context main app context
     */
    public static void testAddressBooks(Context context) {
        long startTime = System.currentTimeMillis();
        EventBus.getDefault().post(new LogTestDataEvent(-1, FRAMEWORK_NAME, MainActivity.SAVE_TIME));
        EventBus.getDefault().post(new LogTestDataEvent(-1, FRAMEWORK_NAME, MainActivity.LOAD_TIME));
    }
}
