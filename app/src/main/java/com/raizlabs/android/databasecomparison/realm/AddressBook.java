package com.raizlabs.android.databasecomparison.realm;

import com.raizlabs.android.databasecomparison.interfaces.IAddressBook;

import java.util.Collection;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Realm address book object
 */
public class AddressBook extends RealmObject implements IAddressBook<AddressItem, Contact> {
    @PrimaryKey
    public long id;

    public String name;

    public String author;

    public RealmList<AddressItem> addresses = new RealmList<>();

    public RealmList<Contact> contacts = new RealmList<>();

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public Collection<AddressItem> getAddresses() {
        return addresses;
    }

    @Override
    public Collection<Contact> getContacts() {
        return contacts;
    }

    @Override
    public void setContacts(Collection contacts) {
        List<Contact> contactList = (List<Contact>) contacts;
        this.contacts.addAll(contactList);
    }

    @Override
    public void setAddresses(Collection addresses) {
        List<AddressItem> addressList = (List<AddressItem>) addresses;
        this.addresses.addAll(addressList);
    }

    @Override
    public void saveAll() {

    }
}
