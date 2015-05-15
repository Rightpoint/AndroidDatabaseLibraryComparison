package com.raizlabs.android.databasecomparison.activeandroid;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.raizlabs.android.databasecomparison.Generator;
import com.raizlabs.android.databasecomparison.Loader;
import com.raizlabs.android.databasecomparison.MainActivity;
import com.raizlabs.android.databasecomparison.Saver;
import com.raizlabs.android.dbflow.runtime.TransactionManager;

import java.util.Collection;

/**
 * Description:
 */
public class AATester {
    public static final String FRAMEWORK_NAME = "ActiveAndroid";

    public static void testAddressBooks(MainActivity mainActivity) {
        new Delete().from(AddressItem.class).execute();
        new Delete().from(Contact.class).execute();
        new Delete().from(AddressBook.class).execute();

        Collection<AddressBook> addressBooks =
                Generator.createAddressBooks(AddressBook.class,
                        Contact.class,
                        AddressItem.class,
                        MainActivity.ADDRESS_BOOK_COUNT);
        long startTime = System.currentTimeMillis();
        final Collection<AddressBook> finalAddressBooks = addressBooks;
        TransactionManager.transact(ActiveAndroid.getDatabase(), new Runnable() {
            @Override
            public void run() {
                Saver.saveAll(finalAddressBooks);
            }
        });
        mainActivity.logTime(startTime, FRAMEWORK_NAME, MainActivity.SAVE_TIME);

        startTime = System.currentTimeMillis();
        addressBooks = new Select().from(AddressBook.class).execute();
        Loader.loadAllInnerData(addressBooks);
        mainActivity.logTime(startTime, FRAMEWORK_NAME, MainActivity.LOAD_TIME);

        new Delete().from(AddressItem.class).execute();
        new Delete().from(Contact.class).execute();
        new Delete().from(AddressBook.class).execute();
    }

    public static void testAddressItems(MainActivity mainActivity) {
        new Delete().from(SimpleAddressItem.class).execute();

        final Collection<SimpleAddressItem> activeAndroidModels =
                Generator.getAddresses(SimpleAddressItem.class, MainActivity.LOOP_COUNT);

        long startTime = System.currentTimeMillis();
        // Reuse method so we don't have to write
        TransactionManager.transact(ActiveAndroid.getDatabase(), new Runnable() {
            @Override
            public void run() {
                Saver.saveAll(activeAndroidModels);
            }
        });
        mainActivity.logTime(startTime, FRAMEWORK_NAME, MainActivity.SAVE_TIME);

        startTime = System.currentTimeMillis();
        Collection<SimpleAddressItem> activeAndroidModelLoad =
                new Select().from(SimpleAddressItem.class).execute();
        mainActivity.logTime(startTime, FRAMEWORK_NAME, MainActivity.LOAD_TIME);

        new Delete().from(SimpleAddressItem.class).execute();
    }
}
