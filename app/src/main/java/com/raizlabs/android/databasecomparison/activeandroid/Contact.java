package com.raizlabs.android.databasecomparison.activeandroid;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.raizlabs.android.databasecomparison.interfaces.IContact;

/**
 * Description:
 */
@Table(name = "contact")
public class Contact extends Model implements IContact<AddressBook> {

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "addressBook")
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
    public AddressBook getAddressBookField() {
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
