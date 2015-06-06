package com.raizlabs.android.databasecomparison.ormlite;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.raizlabs.android.databasecomparison.interfaces.IContact;

/**
 * Description: Contact DAO
 */
@DatabaseTable(tableName = "contact")
public class Contact implements IContact<AddressBook> {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField
    private String name;

    @DatabaseField
    private String email;

    @DatabaseField(foreign=true,foreignAutoRefresh=true)
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

    }
}
