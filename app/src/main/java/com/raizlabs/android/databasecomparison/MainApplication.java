package com.raizlabs.android.databasecomparison;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Configuration;
import com.orm.Database;
import com.orm.SugarApp;
import com.raizlabs.android.databasecomparison.activeandroid.AddressBook;
import com.raizlabs.android.databasecomparison.activeandroid.AddressItem;
import com.raizlabs.android.databasecomparison.activeandroid.Contact;
import com.raizlabs.android.databasecomparison.activeandroid.SimpleAddressItem;
import com.raizlabs.android.dbflow.config.FlowManager;

import se.emilsjolander.sprinkles.Sprinkles;

/**
 * Description:
 */
public class MainApplication extends SugarApp {

    private static Database mDatabase;

    @Override
    public void onCreate() {
        super.onCreate();

        ActiveAndroid.initialize(new Configuration.Builder(this)
                .setDatabaseName("activeandroid")
                .setDatabaseVersion(1)
                .setModelClasses(SimpleAddressItem.class, AddressItem.class,
                        AddressBook.class, Contact.class).create());

        FlowManager.init(this);

        Sprinkles.init(this, "sprinkles.db", 2);

        mDatabase = getDatabase();
    }

    public static Database getSugarDatabase() {
        return mDatabase;
    }
}
