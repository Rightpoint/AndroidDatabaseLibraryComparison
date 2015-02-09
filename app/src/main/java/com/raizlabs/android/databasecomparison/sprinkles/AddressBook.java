package com.raizlabs.android.databasecomparison.sprinkles;

import com.raizlabs.android.databasecomparison.IAddressBook;

import java.util.List;

import se.emilsjolander.sprinkles.Model;
import se.emilsjolander.sprinkles.Query;
import se.emilsjolander.sprinkles.annotations.AutoIncrement;
import se.emilsjolander.sprinkles.annotations.Column;
import se.emilsjolander.sprinkles.annotations.Key;
import se.emilsjolander.sprinkles.annotations.Table;

/**
 * Description:
 */
@Table("AddressBook")
public class AddressBook extends Model implements IAddressBook<SimpleAddressItem, Contact>{

    @Column("id")
    @AutoIncrement
    @Key
    private long id;

    @Column("name")
    private String name;

    @Column("author")
    private String author;

    List<SimpleAddressItem> addresses;

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
    public void setAddresses(List<SimpleAddressItem> addresses) {
        this.addresses = addresses;
    }

    @Override
    public List<SimpleAddressItem> getAddresses() {
        if (addresses == null) {
            addresses = Query.many(SimpleAddressItem.class, "addressBook = ?",
                    String.valueOf(id)).get().asList();
        }
        return addresses;
    }

    @Override
    public List<Contact> getContacts() {
        if (contacts == null) {
            contacts = Query.many(Contact.class, "addressBook = ?",
                    String.valueOf(id)).get().asList();
        }
        return contacts;
    }

    public long getId() {
        return id;
    }

    @Override
    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    @Override
    public void saveAll() {
        super.save();
        for (SimpleAddressItem addressItem : addresses) {
            addressItem.saveAll();
        }
        for (Contact contact : contacts) {
            contact.saveAll();
        }
    }
}
