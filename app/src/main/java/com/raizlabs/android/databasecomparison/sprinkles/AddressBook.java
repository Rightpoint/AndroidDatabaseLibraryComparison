package com.raizlabs.android.databasecomparison.sprinkles;

import com.raizlabs.android.databasecomparison.interfaces.IAddressBook;

import java.util.Collection;

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

    Collection<SimpleAddressItem> addresses;

    Collection<Contact> contacts;

    @Override
    public void setId(long id) {
        // not needed...we have autoincrementing primary keys
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
    public void setAddresses(Collection<SimpleAddressItem> addresses) {
        this.addresses = addresses;
    }

    @Override
    public Collection<SimpleAddressItem> getAddresses() {
        if (addresses == null) {
            addresses = Query.many(SimpleAddressItem.class, "addressBook = ?",
                    String.valueOf(id)).get().asList();
        }
        return addresses;
    }

    @Override
    public Collection<Contact> getContacts() {
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
    public void setContacts(Collection<Contact> contacts) {
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
