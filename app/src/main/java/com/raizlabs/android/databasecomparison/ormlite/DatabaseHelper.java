package com.raizlabs.android.databasecomparison.ormlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.raizlabs.android.databasecomparison.R;

import java.sql.SQLException;

/**
 * Database helper class used to manage the creation and upgrading of your database. This class also usually provides
 * the DAOs used by the other classes.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    // name of the database file for your application -- change to something appropriate for your app
    private static final String DATABASE_NAME = "ormlite.db";
    // any time you make changes to your database objects, you may have to increase the database version
    private static final int DATABASE_VERSION = 1;

    // the DAO objects
    private Dao<AddressBook, Integer> addressBookDao = null;
    private Dao<AddressItem, Integer> addressItemDao = null;
    private Dao<Contact, Integer> contactDao = null;
    private Dao<SimpleAddressItem, Integer> simpleAddressDao = null;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
    }

    /**
     * This is called when the database is first created. Usually you should call createTable statements here to create
     * the tables that will store your data.
     */
    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            Log.i(DatabaseHelper.class.getName(), "onCreate");
            TableUtils.createTable(connectionSource, AddressBook.class);
            TableUtils.createTable(connectionSource, AddressItem.class);
            TableUtils.createTable(connectionSource, Contact.class);
            TableUtils.createTable(connectionSource, SimpleAddressItem.class);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Can't create database", e);
            throw new RuntimeException(e);
        }

        // here we try inserting data in the on-create as a test
        /*
        RuntimeExceptionDao<SimpleData, Integer> dao = getSimpleDataDao();
        long millis = System.currentTimeMillis();
        // create some entries in the onCreate
        SimpleData simple = new SimpleData(millis);
        dao.create(simple);
        simple = new SimpleData(millis + 1);
        dao.create(simple);
        Log.i(DatabaseHelper.class.getName(), "created new entries in onCreate: " + millis);
        */
    }

    /**
     * This is called when your application is upgraded and it has a higher version number. This allows you to adjust
     * the various data to match the new version number.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            Log.i(DatabaseHelper.class.getName(), "onUpgrade");
            TableUtils.dropTable(connectionSource, AddressBook.class, true);
            TableUtils.dropTable(connectionSource, AddressItem.class, true);
            TableUtils.dropTable(connectionSource, Contact.class, true);
            TableUtils.dropTable(connectionSource, SimpleAddressItem.class, true);
            // after we drop the old databases, we create the new ones
            onCreate(db, connectionSource);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Can't drop databases", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns the Database Access Object (DAO) for our AddressBook class
     */
    public Dao<AddressBook, Integer> getAddressBookDao() throws SQLException {
        if (addressBookDao == null) {
            addressBookDao = getDao(AddressBook.class);
        }
        return addressBookDao;
    }

    /**
     * Returns the Database Access Object (DAO) for our AddressItem class
     */
    public Dao<AddressItem, Integer> getAddressItemDao() throws SQLException {
        if (addressItemDao == null) {
            addressItemDao = getDao(AddressItem.class);
        }
        return addressItemDao;
    }

    /**
     * Returns the Database Access Object (DAO) for our Contact class
     */
    public Dao<Contact, Integer> getContactDao() throws SQLException {
        if (contactDao == null) {
            contactDao = getDao(Contact.class);
        }
        return contactDao;
    }

    /**
     * Returns the Database Access Object (DAO) for our SimpleAddressItem class
     */
    public Dao<SimpleAddressItem, Integer> getSimpleAddressItemDao() throws SQLException {
        if (simpleAddressDao == null) {
            simpleAddressDao = getDao(SimpleAddressItem.class);
        }
        return simpleAddressDao;
    }

    /**
     * Close the database connections and clear any cached DAOs.
     */
    @Override
    public void close() {
        super.close();
        addressBookDao = null;
        addressItemDao = null;
        contactDao = null;
        simpleAddressDao = null;
   }
}