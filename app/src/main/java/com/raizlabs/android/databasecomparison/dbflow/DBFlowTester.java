package com.raizlabs.android.databasecomparison.dbflow;

import com.raizlabs.android.databasecomparison.Generator;
import com.raizlabs.android.databasecomparison.Loader;
import com.raizlabs.android.databasecomparison.MainActivity;
import com.raizlabs.android.databasecomparison.Saver;
import com.raizlabs.android.dbflow.runtime.TransactionManager;
import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.Collection;

/**
 * Description:
 */
public class DBFlowTester {
    public static final String FRAMEWORK_NAME = "DBFlow";

    public static void testAddressBooks(MainActivity mainActivity) {
        com.raizlabs.android.dbflow.sql.language.Delete.tables(AddressItem.class,
                Contact.class, AddressBook.class);

        Collection<AddressBook> addressBooks = Generator.createAddressBooks(AddressBook.class,
                Contact.class, AddressItem.class,
                MainActivity.ADDRESS_BOOK_COUNT);

        long startTime = System.currentTimeMillis();
        final Collection<AddressBook> finalAddressBooks = addressBooks;
        TransactionManager.transact(DBFlowDatabase.NAME, new Runnable() {
            @Override
            public void run() {
                Saver.saveAll(finalAddressBooks);
            }
        });
        mainActivity.logTime(startTime, FRAMEWORK_NAME, MainActivity.SAVE_TIME);

        startTime = System.currentTimeMillis();
        addressBooks = new Select().from(AddressBook.class).queryList();
        Loader.loadAllInnerData(addressBooks);
        mainActivity.logTime(startTime, FRAMEWORK_NAME, MainActivity.LOAD_TIME);


        com.raizlabs.android.dbflow.sql.language.Delete.tables(AddressItem.class,
                                                               Contact.class, AddressBook.class);
    }

    public static void testAddressItems(MainActivity mainActivity) {
        com.raizlabs.android.dbflow.sql.language.Delete.table(SimpleAddressItem.class);
        Collection<SimpleAddressItem> dbFlowModels =
                Generator.getAddresses(SimpleAddressItem.class, MainActivity.LOOP_COUNT);
        long startTime = System.currentTimeMillis();
        final Collection<SimpleAddressItem> finalDbFlowModels = dbFlowModels;
        TransactionManager.transact(DBFlowDatabase.NAME, new Runnable() {
            @Override
            public void run() {
                Saver.saveAll(finalDbFlowModels);
            }
        });
        mainActivity.logTime(startTime, FRAMEWORK_NAME, MainActivity.SAVE_TIME);

        startTime = System.currentTimeMillis();
        dbFlowModels = new Select().from(SimpleAddressItem.class).queryList();
        mainActivity.logTime(startTime, FRAMEWORK_NAME, MainActivity.LOAD_TIME);

        com.raizlabs.android.dbflow.sql.language.Delete.table(SimpleAddressItem.class);
    }
}
