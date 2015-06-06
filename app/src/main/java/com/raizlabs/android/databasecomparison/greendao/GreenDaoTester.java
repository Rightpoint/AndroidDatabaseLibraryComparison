package com.raizlabs.android.databasecomparison.greendao;

import android.content.Context;

import com.raizlabs.android.databasecomparison.MainActivity;
import com.raizlabs.android.databasecomparison.events.LogTestDataEvent;
import com.raizlabs.android.databasecomparison.greendao.gen.AddressBook;
import com.raizlabs.android.databasecomparison.greendao.gen.AddressBookDao;
import com.raizlabs.android.databasecomparison.greendao.gen.Contact;
import com.raizlabs.android.databasecomparison.greendao.gen.DaoMaster;
import com.raizlabs.android.databasecomparison.greendao.gen.DaoSession;
import com.raizlabs.android.databasecomparison.greendao.gen.SimpleAddressItem;
import com.raizlabs.android.databasecomparison.greendao.gen.SimpleAddressItemDao;

import java.util.Collection;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Description:
 */
public class GreenDaoTester {
    public static final String FRAMEWORK_NAME = "GreenDAO";

    public static void testAddressItems(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "notes-db", null);
        DaoMaster daoMaster = new DaoMaster(helper.getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        final SimpleAddressItemDao simpleAddressItemDao = daoSession.getSimpleAddressItemDao();
        simpleAddressItemDao.deleteAll();

        final List<SimpleAddressItem> addressItemList = GreenDaoGenerator.getSimpleAddressItems(MainActivity.LOOP_COUNT);

        long startTime = System.currentTimeMillis();
        simpleAddressItemDao.insertOrReplaceInTx(addressItemList);
        EventBus.getDefault().post(new LogTestDataEvent(startTime, FRAMEWORK_NAME, MainActivity.SAVE_TIME));

        startTime = System.currentTimeMillis();
        simpleAddressItemDao.loadAll();
        EventBus.getDefault().post(new LogTestDataEvent(startTime, FRAMEWORK_NAME, MainActivity.LOAD_TIME));

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
        EventBus.getDefault().post(new LogTestDataEvent(startTime, FRAMEWORK_NAME, MainActivity.SAVE_TIME));

        startTime = System.currentTimeMillis();
        addressBooks = addressBookDao.loadAll();
        for (AddressBook addressBook : addressBooks) {
            addressBook.getAddressItemList();
            Collection<Contact> contactList = addressBook.getContactList();
            for(Contact contact: contactList) {
                contact.getAddressBook();
            }
        }
        EventBus.getDefault().post(new LogTestDataEvent(startTime, FRAMEWORK_NAME, MainActivity.LOAD_TIME));

                             addressBookDao.deleteAll();
        daoSession.getAddressItemDao().deleteAll();
        daoSession.getContactDao().deleteAll();
    }
}
