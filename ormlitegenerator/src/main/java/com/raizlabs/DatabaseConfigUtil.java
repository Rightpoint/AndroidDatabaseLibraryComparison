package com.raizlabs;

import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

import java.io.File;

/**
 * OrmLite generator will look for all classes underneath us for proper annotatations
 */
public class DatabaseConfigUtil extends OrmLiteConfigUtil {
  public static void main(String[] args) throws Exception {
    System.out.println("Current directory is " + new File(".").getCanonicalPath());
    writeConfigFile("ormlite_config.txt");
  }
}

