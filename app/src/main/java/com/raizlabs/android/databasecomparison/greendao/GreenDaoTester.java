package com.raizlabs.android.databasecomparison.greendao;

import android.content.Context;

import com.raizlabs.android.databasecomparison.greendao.gen.DaoMaster;
import com.raizlabs.android.databasecomparison.greendao.gen.DaoSession;
import com.raizlabs.android.databasecomparison.greendao.gen.SimpleAddressItemDao;

/**
 * Description:
 */
public class GreenDaoTester {

    public static void testAddressItems(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "notes-db", null);
        DaoMaster daoMaster = new DaoMaster(helper.getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        SimpleAddressItemDao simpleAddressItemDao = daoSession.getSimpleAddressItemDao();
        
    }
}
