package com.raizlabs.android.databasecomparison.dbflow;

import com.raizlabs.android.databasecomparison.Generator;
import com.raizlabs.android.databasecomparison.Loader;
import com.raizlabs.android.databasecomparison.MainActivity;
import com.raizlabs.android.databasecomparison.Saver;
import com.raizlabs.android.dbflow.runtime.TransactionManager;

import java.util.List;

/**
 * Description:
 */
public class DBFlowTester {

    public static void testDBFlowAddressBooks() {
        com.raizlabs.android.dbflow.sql.language.Delete.tables(AddressItem.class,
                Contact.class, AddressBook.class);

        List<AddressBook> addressBooks = Generator.createAddressBooks(AddressBook.class,
                Contact.class, AddressItem.class,
                MainActivity.ADDRESS_BOOK_COUNT);

        long startTime = System.currentTimeMillis();
        final List<AddressBook> finalAddressBooks = addressBooks;
        TransactionManager.transact(DBFlowDatabase.NAME, new Runnable() {
            @Override
            public void run() {
                Saver.saveAll(finalAddressBooks);
            }
        });
        MainActivity.logTime(startTime, "DBFlow addresses");

        startTime = System.currentTimeMillis();
        addressBooks = com.raizlabs.android.dbflow.sql.language.Select.all(AddressBook.class);
        Loader.loadAllInnerData(addressBooks);
        MainActivity.logTime(startTime, "DBFlow load addresses");


        com.raizlabs.android.dbflow.sql.language.Delete.tables(AddressItem.class,
                Contact.class, AddressBook.class);
    }

    public static void testDBFlowAddressItems() {
        com.raizlabs.android.dbflow.sql.language.Delete.table(AddressItem.class);
        List<AddressItem> dbFlowModels =
                Generator.getAddresses(AddressItem.class, MainActivity.LOOP_COUNT);
        long startTime = System.currentTimeMillis();
        final List<AddressItem> finalDbFlowModels = dbFlowModels;
        TransactionManager.transact(DBFlowDatabase.NAME, new Runnable() {
            @Override
            public void run() {
                Saver.saveAll(finalDbFlowModels);
            }
        });
        MainActivity.logTime(startTime, "DBFlow");

        startTime = System.currentTimeMillis();
        dbFlowModels = com.raizlabs.android.dbflow.sql.language.Select.all(AddressItem.class);
        MainActivity.logTime(startTime, "DBFlow load");

        com.raizlabs.android.dbflow.sql.language.Delete.table(AddressItem.class);
    }
}
