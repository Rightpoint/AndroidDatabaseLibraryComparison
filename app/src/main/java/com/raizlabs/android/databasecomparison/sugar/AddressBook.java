package com.raizlabs.android.databasecomparison.sugar;

import com.orm.StringUtil;
import com.orm.SugarRecord;
import com.orm.dsl.Ignore;
import com.raizlabs.android.databasecomparison.IAddressBook;

import java.util.List;

/**
 * Description:
 */
public class AddressBook extends SugarRecord<AddressBook> implements IAddressBook<AddressItem, Contact> {

    private String name;

    private String author;

    @Ignore
    List<AddressItem> addresses;

    @Ignore
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
        if(addresses == null) {
            addresses = AddressItem.find(AddressItem.class, StringUtil.toSQLName("addressBook") + "= ?",
                    String.valueOf(id));
        }
        return addresses;
    }

    @Override
    public List<Contact> getContacts() {
        if(contacts == null) {
            contacts = Contact.find(Contact.class, StringUtil.toSQLName("addressBook") + " = ?",
                    String.valueOf(id));
        }
        return contacts;
    }

    @Override
    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    @Override
    public void saveAll() {
        super.save();
        for(AddressItem addressItem : addresses) {
            addressItem.saveAll();
        }
        for(Contact contact: contacts) {
            contact.saveAll();
        }
    }
}
