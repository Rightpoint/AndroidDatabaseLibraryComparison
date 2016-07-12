package com.raizlabs.android.databasecomparison.ollie;

import com.raizlabs.android.databasecomparison.interfaces.IAddressBook;

import java.util.Collection;

import ollie.Model;
import ollie.annotation.AutoIncrement;
import ollie.annotation.Column;
import ollie.annotation.PrimaryKey;
import ollie.annotation.Table;
import ollie.query.Select;

/**
 * Description:
 */
@Table("AddressBook")
public class AddressBook extends Model implements IAddressBook<AddressItem, Contact> {
    @PrimaryKey
    @AutoIncrement
    public Long id;

    @Column("name")
    public String name;

    @Column("author")
    public String author;

    Collection<AddressItem> addresses;

    Collection<Contact> contacts;

    @Override
    public void setId(long id) {
        // not needed...we have autoincrementing keys
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
    public void setAddresses(Collection<AddressItem> addresses) {
        this.addresses = addresses;
    }

    @Override
    public Collection<AddressItem> getAddresses() {
        if (addresses == null) {
            addresses = Select.from(AddressItem.class).where("addressBook = ?", id).fetch();
        }
        return addresses;
    }

    @Override
    public Collection<Contact> getContacts() {
        if (contacts == null) {
            contacts = Select.from(Contact.class).where("addressBook = ?", id).fetch();
        }
        return contacts;
    }

    public void setContacts(Collection<Contact> contacts) {
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
