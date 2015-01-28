package com.raizlabs.android.databasecomparison.sugar;

import com.orm.SugarApp;
import com.orm.SugarConfig;
import com.raizlabs.android.databasecomparison.Generator;
import com.raizlabs.android.databasecomparison.Loader;
import com.raizlabs.android.databasecomparison.MainActivity;
import com.raizlabs.android.databasecomparison.MainApplication;
import com.raizlabs.android.databasecomparison.Saver;
import com.raizlabs.android.dbflow.runtime.TransactionManager;

import java.util.List;

/**
 * Description:
 */
public class SugarTester {

    public static void testSugarAddressBooks() {
        AddressItem.deleteAll(AddressItem.class);
        AddressBook.deleteAll(AddressBook.class);
        Contact.deleteAll(Contact.class);

        List<AddressBook> addressBooks = Generator.createAddressBooks(AddressBook.class,
                Contact.class, AddressItem.class, MainActivity.ADDRESS_BOOK_COUNT);
        long startTime = System.currentTimeMillis();
        final List<AddressBook> finalAddressBooks = addressBooks;
        TransactionManager.transact(MainApplication.getSugarDatabase().getDB(), new Runnable() {
            @Override
            public void run() {
                Saver.saveAll(finalAddressBooks);
            }
        });
        MainActivity.logTime(startTime, "Sugar save addresses");

        startTime = System.currentTimeMillis();
        addressBooks = AddressBook.listAll(AddressBook.class);
        Loader.loadAllInnerData(addressBooks);
        MainActivity.logTime(startTime, "Sugar load addresses");

        AddressItem.deleteAll(AddressItem.class);
        AddressBook.deleteAll(AddressBook.class);
        Contact.deleteAll(Contact.class);
    }

    public static void testSugarAddressItems() {
        AddressItem.deleteAll(AddressItem.class);

        List<AddressItem> sugarModelList = Generator.getAddresses(AddressItem.class, MainActivity.LOOP_COUNT);
        long startTime = System.currentTimeMillis();
        AddressItem.saveInTx(sugarModelList);
        MainActivity.logTime(startTime, "Sugar");

        startTime = System.currentTimeMillis();
        sugarModelList = AddressItem.listAll(AddressItem.class);
        MainActivity.logTime(startTime, "Sugar load");

        AddressItem.deleteAll(AddressItem.class);
    }
}
