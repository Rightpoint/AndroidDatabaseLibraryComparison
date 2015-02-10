package com.raizlabs.android.databasecomparison.greendao;

import com.raizlabs.android.databasecomparison.IAddressBook;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 */
public class AddressBook extends com.raizlabs.android.databasecomparison.greendao.gen.AddressBook implements IAddressBook<AddressItem, Contact> {

    public AddressBook() {
    }

    public AddressBook(Long id) {
        super(id);
    }

    public AddressBook(Long id, String name, String author) {
        super(id, name, author);
    }

    public AddressBook(com.raizlabs.android.databasecomparison.greendao.gen.AddressBook addressBook) {
        super(addressBook.getId(), addressBook.getName(), addressBook.getAuthor());
    }

    @Override
    public void setAddresses(List<AddressItem> addresses) {
        List<com.raizlabs.android.databasecomparison.greendao.gen.AddressItem> items = new ArrayList<>();
        for(AddressItem addressItem: addresses) {
            items.add(addressItem);
        }
        addressItemList = items;
    }

    @Override
    public List<AddressItem> getAddresses() {
        List<AddressItem> items = new ArrayList<>();
        for(com.raizlabs.android.databasecomparison.greendao.gen.AddressItem addressItem: addressItemList) {
            items.add(new AddressItem(addressItem));
        }
        return items;
    }

    @Override
    public List<Contact> getContacts() {
        List<Contact> items = new ArrayList<>();
        for(com.raizlabs.android.databasecomparison.greendao.gen.Contact contact: contactList) {
            items.add(new Contact(contact));
        }
        return items;
    }

    @Override
    public void setContacts(List<Contact> contacts) {
        List<com.raizlabs.android.databasecomparison.greendao.gen.Contact> items = new ArrayList<>();
        for(Contact contact: contacts) {
            items.add(contact);
        }
        contactList = items;
    }

    @Override
    public void saveAll() {

    }
}
