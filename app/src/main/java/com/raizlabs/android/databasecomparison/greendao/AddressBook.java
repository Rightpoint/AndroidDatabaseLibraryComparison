package com.raizlabs.android.databasecomparison.greendao;

import com.raizlabs.android.databasecomparison.IAddressBook;

import java.util.List;

/**
 * Description:
 */
public class AddressBook extends com.raizlabs.android.databasecomparison.greendao.gen.AddressBook implements IAddressBook<AddressItem, Contact> {
    @Override
    public void setAddresses(List<? extends AddressItem> addresses) {
        addressItemList = addresses;
    }

    @Override
    public List<AddressItem> getAddresses() {
        return addressItemList;
    }

    @Override
    public List<Contact> getContacts() {
        return contactList;
    }

    @Override
    public void setContacts(List<Contact> contacts) {
        contactList = contacts;
    }

    @Override
    public void saveAll() {

    }
}
