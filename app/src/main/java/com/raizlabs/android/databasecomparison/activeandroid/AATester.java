package com.raizlabs.android.databasecomparison.activeandroid;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.raizlabs.android.databasecomparison.Generator;
import com.raizlabs.android.databasecomparison.Loader;
import com.raizlabs.android.databasecomparison.MainActivity;
import com.raizlabs.android.databasecomparison.Saver;
import com.raizlabs.android.dbflow.runtime.TransactionManager;

import java.util.List;

/**
 * Description:
 */
public class AATester {

    public static void testAAAddressBooks() {
        new Delete().from(AddressItem.class).execute();
        new Delete().from(Contact.class).execute();
        new Delete().from(AddressBook.class).execute();

        List<AddressBook> addressBooks =
                Generator.createAddressBooks(AddressBook.class,
                        Contact.class,
                        AddressItem.class,
                        MainActivity.ADDRESS_BOOK_COUNT);
        long startTime = System.currentTimeMillis();
        final List<AddressBook> finalAddressBooks = addressBooks;
        TransactionManager.transact(ActiveAndroid.getDatabase(), new Runnable() {
            @Override
            public void run() {
                Saver.saveAll(finalAddressBooks);
            }
        });
        MainActivity.logTime(startTime, "AA save addresses");

        startTime = System.currentTimeMillis();
        addressBooks = new Select().from(AddressBook.class).execute();
        Loader.loadAllInnerData(addressBooks);
        MainActivity.logTime(startTime, "AA load addresses");

        new Delete().from(AddressItem.class).execute();
        new Delete().from(Contact.class).execute();
        new Delete().from(AddressBook.class).execute();
    }

    public static void testAAAddressItems() {
        new Delete().from(AddressItem.class).execute();

        final List<AddressItem> activeAndroidModels =
                Generator.getAddresses(AddressItem.class, MainActivity.LOOP_COUNT);

        long startTime = System.currentTimeMillis();
        // Reuse method so we don't have to write
        TransactionManager.transact(ActiveAndroid.getDatabase(), new Runnable() {
            @Override
            public void run() {
                Saver.saveAll(activeAndroidModels);
            }
        });
        MainActivity.logTime(startTime, "Active android");

        startTime = System.currentTimeMillis();
        List<AddressItem> activeAndroidModelLoad =
                new Select().from(AddressItem.class).execute();
        MainActivity.logTime(startTime, "AA load");

        new Delete().from(AddressItem.class).execute();
    }
}
