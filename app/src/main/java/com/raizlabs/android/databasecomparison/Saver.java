package com.raizlabs.android.databasecomparison;

import com.raizlabs.android.databasecomparison.interfaces.ISaveable;

import java.util.Collection;

/**
 * Description:
 */
public class Saver {

    public static void saveAll(Collection<? extends ISaveable> saveables) {
        for(ISaveable saveable: saveables) {
            saveable.saveAll();
        }
    }
}
