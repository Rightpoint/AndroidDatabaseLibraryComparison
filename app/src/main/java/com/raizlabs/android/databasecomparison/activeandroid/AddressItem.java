package com.raizlabs.android.databasecomparison.activeandroid;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Description:
 */
@Table(name = "AddressItem")
public class AddressItem extends SimpleAddressItem {

    @Column(name = "addressBook")
    private AddressBook addressBook;

    @Override
    public void setAddressBook(AddressBook addressBook) {
        this.addressBook = addressBook;
    }

}
