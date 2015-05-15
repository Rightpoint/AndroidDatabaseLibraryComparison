package com.raizlabs.android.databasecomparison.sugar;

import com.raizlabs.android.databasecomparison.Generator;
import com.raizlabs.android.databasecomparison.Loader;
import com.raizlabs.android.databasecomparison.MainActivity;
import com.raizlabs.android.databasecomparison.MainApplication;
import com.raizlabs.android.databasecomparison.Saver;
import com.raizlabs.android.dbflow.runtime.TransactionManager;

import java.util.Collection;

/**
 * Description:
 */
public class SugarTester {
    public static final String FRAMEWORK_NAME = "Sugar";

    public static void testAddressBooks(MainActivity mainActivity) {
        AddressItem.deleteAll(AddressItem.class);
        AddressBook.deleteAll(AddressBook.class);
        Contact.deleteAll(Contact.class);

        Collection<AddressBook> addressBooks = Generator.createAddressBooks(AddressBook.class,
                Contact.class, AddressItem.class, MainActivity.ADDRESS_BOOK_COUNT);
        long startTime = System.currentTimeMillis();
        final Collection<AddressBook> finalAddressBooks = addressBooks;
        TransactionManager.transact(MainApplication.getSugarDatabase().getDB(), new Runnable() {
            @Override
            public void run() {
                Saver.saveAll(finalAddressBooks);
            }
        });
        mainActivity.logTime(startTime, FRAMEWORK_NAME, MainActivity.SAVE_TIME);

        startTime = System.currentTimeMillis();
        addressBooks = AddressBook.listAll(AddressBook.class);
        Loader.loadAllInnerData(addressBooks);
        mainActivity.logTime(startTime, FRAMEWORK_NAME, MainActivity.LOAD_TIME);

        AddressItem.deleteAll(AddressItem.class);
        AddressBook.deleteAll(AddressBook.class);
        Contact.deleteAll(Contact.class);
    }

    public static void testAddressItems(MainActivity mainActivity) {
        SimpleAddressItem.deleteAll(SimpleAddressItem.class);

        Collection<SimpleAddressItem> sugarModelList = Generator.getAddresses(SimpleAddressItem.class, MainActivity.LOOP_COUNT);
        long startTime = System.currentTimeMillis();
        SimpleAddressItem.saveInTx(sugarModelList);
        mainActivity.logTime(startTime, FRAMEWORK_NAME, MainActivity.SAVE_TIME);

        startTime = System.currentTimeMillis();
        sugarModelList = SimpleAddressItem.listAll(SimpleAddressItem.class);
        mainActivity.logTime(startTime, FRAMEWORK_NAME, MainActivity.LOAD_TIME);

        SimpleAddressItem.deleteAll(SimpleAddressItem.class);
    }
}
