package com.raizlabs.android.databasecomparison.sugar;

import com.orm.SugarRecord;
import com.raizlabs.android.databasecomparison.IContact;

/**
 * Description:
 */
public class Contact extends SugarRecord<Contact> implements IContact<AddressBook> {

    private String name;

    private String email;

    private AddressBook addressBook;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public AddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public void setAddressBook(AddressBook addressBook) {
        this.addressBook = addressBook;
    }

    @Override
    public void saveAll() {
        super.save();
    }
}
