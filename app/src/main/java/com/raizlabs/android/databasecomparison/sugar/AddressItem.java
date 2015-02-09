package com.raizlabs.android.databasecomparison.sugar;

/**
 * Description:
 */
public class AddressItem extends SimpleAddressItem {

    private AddressBook addressBook;

    @Override
    public void setAddressBook(AddressBook addressBook) {
        this.addressBook = addressBook;
    }
}
