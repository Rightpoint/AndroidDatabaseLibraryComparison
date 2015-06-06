package com.raizlabs.android.databasecomparison.ormlite;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import com.raizlabs.android.databasecomparison.interfaces.IAddressBook;

import java.sql.SQLException;
import java.util.Collection;


/**
 * Description: Address Book DAO
 */
@DatabaseTable(tableName = "AddressBook")
public class AddressBook implements IAddressBook<AddressItem, Contact> {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField
    private String name;

    @DatabaseField
    private String author;

    @ForeignCollectionField
    private ForeignCollection<AddressItem> addresses;
    private Collection<AddressItem> nonDaoAddresses;

    @ForeignCollectionField
    private ForeignCollection<Contact> contacts;
    private Collection<Contact> nonDaoContacts;

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
        if (addresses instanceof ForeignCollection) {
            this.addresses = (ForeignCollection) addresses;
        } else {
            this.nonDaoAddresses = addresses;
        }
    }

    /**
     * We have to do this separate step because OrmLite doesn't automatically insert children
     */
    public void insertNewAddresses(Dao<AddressBook, Integer> addressBookDao, Dao<AddressItem, Integer> addressItemDao) throws SQLException {
        if (this.nonDaoAddresses == null) {
            return;
        }
        addressBookDao.assignEmptyForeignCollection(this, "addresses");
        for (AddressItem addressItem : nonDaoAddresses) {
            addressItem.setAddressBook(this);
            addressItemDao.create(addressItem);
            addresses.add(addressItem);
        }
    }

    @Override
    public Collection<AddressItem> getAddresses() {
        return addresses;
    }

    @Override
    public Collection<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(Collection<Contact> contacts) {
        if (contacts instanceof ForeignCollection) {
            this.contacts = (ForeignCollection) contacts;
        } else {
            this.nonDaoContacts = contacts;
        }
    }


    /**
     * We have to do this separate step because OrmLite doesn't automatically insert children
     */
    public void insertNewContacts(Dao<AddressBook, Integer> addressBookDao, Dao<Contact, Integer> contactDao) throws SQLException {
        if (this.nonDaoContacts == null) {
            return;
        }
        addressBookDao.assignEmptyForeignCollection(this, "contacts");
        for (Contact contact : nonDaoContacts) {
            contact.setAddressBook(this);
            contactDao.create(contact);
            contacts.add(contact);
        }
    }

    @Override
    public void saveAll() {

    }
}
