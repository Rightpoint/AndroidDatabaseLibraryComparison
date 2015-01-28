package com.raizlabs.android.databasecomparison;

import java.util.List;

/**
 * Description:
 */
public interface IAddressBook<AddressItem extends IAddressItem, Contact extends IContact> extends ISaveable {

    public void setName(String name);

    public void setAuthor(String author);

    public void setAddresses(List<AddressItem> addresses);

    public List<AddressItem> getAddresses();

    public List<Contact> getContacts();

    public void setContacts(List<Contact> contacts);
}
