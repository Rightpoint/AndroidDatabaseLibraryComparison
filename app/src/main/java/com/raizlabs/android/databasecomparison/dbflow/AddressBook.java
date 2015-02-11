package com.raizlabs.android.databasecomparison.dbflow;

import com.raizlabs.android.databasecomparison.IAddressBook;
import com.raizlabs.android.databasecomparison.MainActivity;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ContainerAdapter;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.builder.Condition;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.raizlabs.android.dbflow.structure.cache.BaseCacheableModel;
import com.raizlabs.android.dbflow.structure.cache.ModelCache;
import com.raizlabs.android.dbflow.structure.cache.SparseArrayBasedCache;

import java.util.List;

/**
 * Description:
 */
@Table(value = "AddressBook", databaseName = DBFlowDatabase.NAME)
@ContainerAdapter
public class AddressBook extends BaseCacheableModel implements IAddressBook<AddressItem, Contact> {

    @Column(name = "id", columnType = Column.PRIMARY_KEY_AUTO_INCREMENT)
    long id;

    @Column(name = "name")
    String name;

    @Column(name = "author")
    String author;

    List<AddressItem> addresses;

    List<Contact> contacts;

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setAddresses(List<AddressItem> addresses) {
        this.addresses = addresses;
    }

    public List<AddressItem> getAddresses() {
        if (addresses == null) {
            addresses = Select.all(AddressItem.class, Condition.column(AddressItem$Table.ADDRESSBOOK_ADDRESSBOOK).is(id));
        }
        return addresses;
    }

    public List<Contact> getContacts() {
        if (contacts == null) {
            contacts = Select.all(Contact.class, Condition.column(Contact$Table.ADDRESSBOOK_ADDRESSBOOK).is(id));
        }
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    @Override
    public void saveAll() {
        super.insert(false);
        for (AddressItem addressItem : addresses) {
            addressItem.saveAll();
        }
        for (Contact contact : contacts) {
            contact.saveAll();
        }
    }

    @Override
    public int getCacheSize() {
        return MainActivity.ADDRESS_BOOK_COUNT;
    }

}