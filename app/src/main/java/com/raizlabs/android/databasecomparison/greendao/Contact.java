package com.raizlabs.android.databasecomparison.greendao;

import com.raizlabs.android.databasecomparison.IContact;

/**
 * Description:
 */
public class Contact extends com.raizlabs.android.databasecomparison.greendao.gen.Contact implements IContact<AddressBook> {

    @Override
    public void saveAll() {
        
    }

    @Override
    public AddressBook getAddressBookField() {
        return (AddressBook) getAddressBook();
    }

    @Override
    public void setAddressBook(AddressBook addressBook) {
        super.setAddressBook(addressBook);
    }
}
