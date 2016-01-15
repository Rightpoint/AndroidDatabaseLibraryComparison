package com.raizlabs.android.databasecomparison.ollie;

import com.raizlabs.android.databasecomparison.interfaces.IContact;

import ollie.Model;
import ollie.annotation.Column;
import ollie.annotation.Table;

/**
 * Description:
 */
@Table("contact")
public class Contact extends Model implements IContact<AddressBook> {

    @Column("name")
    public String name;

    @Column("email")
    public String email;

    @Column("addressBook")
    public AddressBook addressBook;

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
