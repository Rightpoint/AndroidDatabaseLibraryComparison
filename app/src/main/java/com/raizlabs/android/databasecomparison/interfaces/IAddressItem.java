package com.raizlabs.android.databasecomparison.interfaces;

/**
 * Description: interface for address book items objects in address book
 */
public interface IAddressItem<AddressBook extends IAddressBook> extends ISaveable {

    public void setName(String name);

    public void setAddress(String address);

    public void setCity(String city);

    public void setState(String state);

    public void setPhone(long phone);

    public void setAddressBook(AddressBook addressBook);
}
