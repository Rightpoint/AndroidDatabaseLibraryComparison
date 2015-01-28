package com.raizlabs.android.databasecomparison.activeandroid;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import com.raizlabs.android.databasecomparison.IAddressBook;

import java.util.List;

/**
 * Description:
 */
@Table(name = "AddressBook")
public class AddressBook extends Model implements IAddressBook<AddressItem, Contact> {

    @Column(name = "name")
    private String name;

    @Column(name = "author")
    private String author;

    List<AddressItem> addresses;

    List<Contact> contacts;

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public void setAddresses(List<AddressItem> addresses) {
        this.addresses = addresses;
    }

    @Override
    public List<AddressItem> getAddresses() {
        if (addresses == null) {
            addresses = new Select().from(AddressItem.class).where("addressBook = ?", getId()).execute();
        }
        return addresses;
    }

    @Override
    public List<Contact> getContacts() {
        if (contacts == null) {
            contacts = new Select().from(Contact.class).where("addressBook = ?", getId()).execute();
        }
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    @Override
    public void saveAll() {
        super.save();
        for (AddressItem addressItem : addresses) {
            addressItem.saveAll();
        }
        for (Contact contact : contacts) {
            contact.saveAll();
        }
    }
}
