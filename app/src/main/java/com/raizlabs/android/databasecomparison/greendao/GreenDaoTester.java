package com.raizlabs.android.databasecomparison.greendao;

import android.content.Context;

import com.raizlabs.android.databasecomparison.MainActivity;
import com.raizlabs.android.databasecomparison.greendao.gen.DaoMaster;
import com.raizlabs.android.databasecomparison.greendao.gen.DaoSession;
import com.raizlabs.android.databasecomparison.greendao.gen.SimpleAddressItemDao;

import java.util.ArrayList;
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

        final List<com.raizlabs.android.databasecomparison.greendao.gen.SimpleAddressItem> addressItemList = new ArrayList<>();
        for (int i = 0; i < MainActivity.LOOP_COUNT; i++) {
            com.raizlabs.android.databasecomparison.greendao.gen.SimpleAddressItem simpleAddressItem = new com.raizlabs.android.databasecomparison.greendao.gen.SimpleAddressItem();
            simpleAddressItem.setName("Test");
            simpleAddressItem.setAddress("5486 Memory Lane");
            simpleAddressItem.setCity("Bronx");
            simpleAddressItem.setState("NY");
            simpleAddressItem.setPhone(7185555555l);
            addressItemList.add(simpleAddressItem);
        }

        long startTime = System.currentTimeMillis();
        simpleAddressItemDao.insertOrReplaceInTx(addressItemList);
        MainActivity.logTime(startTime, "GreenDao save addresses items");

        startTime = System.currentTimeMillis();
        simpleAddressItemDao.loadAll();
        MainActivity.logTime(startTime, "GreenDao load address items");

        simpleAddressItemDao.deleteAll();

    }
}
