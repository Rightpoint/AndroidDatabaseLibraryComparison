package com.raizlabs.android.databasecomparison.interfaces;

/**
 * Description: interface for contact objects in address book
 */
public interface IContact<AddressBook extends IAddressBook> extends ISaveable {

    public String getName();

    public void setName(String name);

    public String getEmail();

    public void setEmail(String email);

    public AddressBook getAddressBookField();

    public void setAddressBook(AddressBook addressBook);
}
