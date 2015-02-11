package com.raizlabs.android.databasecomparison.greendao;

import android.content.Context;

import com.raizlabs.android.databasecomparison.MainActivity;
import com.raizlabs.android.databasecomparison.greendao.gen.AddressBook;
import com.raizlabs.android.databasecomparison.greendao.gen.AddressBookDao;
import com.raizlabs.android.databasecomparison.greendao.gen.Contact;
import com.raizlabs.android.databasecomparison.greendao.gen.DaoMaster;
import com.raizlabs.android.databasecomparison.greendao.gen.DaoSession;
import com.raizlabs.android.databasecomparison.greendao.gen.SimpleAddressItem;
import com.raizlabs.android.databasecomparison.greendao.gen.SimpleAddressItemDao;

import java.util.List;

/**
 * Description:
 */
public class GreenDaoTester {

    public static void testAddressItems(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "notes-db", null);
        DaoMaster daoMaster = new DaoMaster(helper.getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        final SimpleAddressItemDao simpleAddressItemDao = daoSession.getSimpleAddressItemDao();
        simpleAddressItemDao.deleteAll();

        final List<SimpleAddressItem> addressItemList = GreenDaoGenerator.getSimpleAddressItems(MainActivity.LOOP_COUNT);

        long startTime = System.currentTimeMillis();
        simpleAddressItemDao.insertOrReplaceInTx(addressItemList);
        MainActivity.logTime(startTime, "GreenDao save addresses items");

        startTime = System.currentTimeMillis();
        simpleAddressItemDao.loadAll();
        MainActivity.logTime(startTime, "GreenDao load address items");

        simpleAddressItemDao.deleteAll();

    }

    public static void testAddressBooks(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "notes-db", null);
        DaoMaster daoMaster = new DaoMaster(helper.getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        AddressBookDao addressBookDao = daoSession.getAddressBookDao();
        addressBookDao.deleteAll();
        daoSession.getAddressItemDao().deleteAll();
        daoSession.getContactDao().deleteAll();

        List<AddressBook> addressBooks = GreenDaoGenerator.createAddressBooks(MainActivity.ADDRESS_BOOK_COUNT);

        long startTime = System.currentTimeMillis();
        addressBookDao.insertInTx(addressBooks);
        for(AddressBook addressBook: addressBooks) {
            daoSession.getContactDao().insertInTx(addressBook.getContactList());
            daoSession.getAddressItemDao().insertInTx(addressBook.getAddressItemList());
        }
        MainActivity.logTime(startTime, "greenDAO save addresses");

        startTime = System.currentTimeMillis();
        addressBooks = addressBookDao.loadAll();
        for (AddressBook addressBook : addressBooks) {
            addressBook.getAddressItemList();
            List<Contact> contactList = addressBook.getContactList();
            for(Contact contact: contactList) {
                contact.getAddressBook();
            }
        }
        MainActivity.logTime(startTime, "greenDAO load addresses");

        addressBookDao.deleteAll();
        daoSession.getAddressItemDao().deleteAll();
        daoSession.getContactDao().deleteAll();
    }
}
