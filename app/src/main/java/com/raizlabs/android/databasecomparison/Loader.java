package com.raizlabs.android.databasecomparison;

import com.raizlabs.android.databasecomparison.interfaces.IAddressBook;
import com.raizlabs.android.databasecomparison.interfaces.IContact;

import java.util.Collection;

/**
 * Description:
 */
public class Loader {

    /**
     * Trigger a load of all inner data to truly test speed of loading
     *
     * @param addressBooks
     */
    public static void loadAllInnerData(Collection<? extends IAddressBook> addressBooks) {
        for (IAddressBook addressBook : addressBooks) {
            addressBook.getAddresses();

            Collection<IContact> contacts = addressBook.getContacts();

            for (IContact contact : contacts) {
                contact.getAddressBookField();
            }
        }
    }
}
