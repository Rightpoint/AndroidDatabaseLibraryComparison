package com.raizlabs.android.databasecomparison.dbflow;

import com.raizlabs.android.databasecomparison.interfaces.IContact;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.ForeignKeyReference;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.raizlabs.android.dbflow.structure.container.ForeignKeyContainer;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Description:
 */
@Table(tableName = "contact", databaseName = DBFlowDatabase.NAME)
public class Contact extends BaseModel implements IContact<AddressBook> {

    @PrimaryKey(autoincrement = true)
    @Column
    long id;

    @Column(name = "name")
    String name;

    @Column(name = "email")
    String email;

    @ForeignKey(references = {@ForeignKeyReference(columnName = "addressBook",
                                                   foreignColumnName = "id", columnType = long.class)},
                saveForeignKeyModel = false)
    @Column
    ForeignKeyContainer<AddressBook> addressBook;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public AddressBook getAddressBookField() {
        return addressBook.toModel();
    }

    @Override
    public void setAddressBook(AddressBook addressBook) {
        this.addressBook = new ForeignKeyContainer<>(AddressBook.class);
        Map<String, Object> keys = new LinkedHashMap<>();
        keys.put(AddressBook$Table.ID, addressBook.id);
        this.addressBook.setData(keys);
        this.addressBook.setModel(addressBook);
    }

    @Override
    public void saveAll() {
        super.insert();
    }
}
