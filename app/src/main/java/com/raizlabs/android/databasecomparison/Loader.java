package com.raizlabs.android.databasecomparison;

import java.util.List;

/**
 * Description:
 */
public class Loader {

    /**
     * Trigger a load of all inner data to truly test speed of loading
     *
     * @param addressBooks
     */
    public static void loadAllInnerData(List<? extends IAddressBook> addressBooks) {
        for (IAddressBook addressBook : addressBooks) {
            addressBook.getAddresses();

            List<IContact> contacts = addressBook.getContacts();

            for (IContact contact : contacts) {
                contact.getAddressBookField();
            }
        }
    }
}
