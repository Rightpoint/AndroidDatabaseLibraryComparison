package com.raizlabs.android.databasecomparison.sugar;

import com.orm.StringUtil;
import com.orm.SugarRecord;
import com.orm.dsl.Ignore;
import com.raizlabs.android.databasecomparison.IAddressBook;

import java.util.List;

/**
 * Description:
 */
public class AddressBook extends SugarRecord<AddressBook> implements IAddressBook<SimpleAddressItem, Contact> {

    private String name;

    private String author;

    @Ignore
    List<SimpleAddressItem> addresses;

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
    public void setAddresses(List<SimpleAddressItem> addresses) {
        this.addresses = addresses;
    }

    @Override
    public List<SimpleAddressItem> getAddresses() {
        if(addresses == null) {
            addresses = SimpleAddressItem.find(SimpleAddressItem.class, StringUtil.toSQLName("addressBook") + "= ?",
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
        for(SimpleAddressItem addressItem : addresses) {
            addressItem.saveAll();
        }
        for(Contact contact: contacts) {
            contact.saveAll();
        }
    }
}
