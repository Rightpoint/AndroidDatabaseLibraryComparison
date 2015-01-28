package com.raizlabs.android.databasecomparison;

import java.util.List;

/**
 * Description:
 */
public class Saver {

    public static void saveAll(List<? extends ISaveable> saveables) {
        for(ISaveable saveable: saveables) {
            saveable.saveAll();
        }
    }
}
