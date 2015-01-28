package com.raizlabs.android.databasecomparison;

/**
 * Description:
 */
public interface IContact<AddressBook extends IAddressBook> extends ISaveable {

    public String getName();

    public void setName(String name);

    public String getEmail();

    public void setEmail(String email);

    public AddressBook getAddressBook();

    public void setAddressBook(AddressBook addressBook);
}
