package com.raizlabs.android.databasecomparison.ormlite;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.raizlabs.android.databasecomparison.interfaces.IAddressItem;

/**
 * Description:
 */
@DatabaseTable(tableName = "SimpleAddressItem")
public class SimpleAddressItem implements IAddressItem<AddressBook> {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField
    private String name;

    @DatabaseField
    private String address;

    @DatabaseField
    private String city;

    @DatabaseField
    private String state;

    @DatabaseField
    private long phone;


    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public void setState(String state) {
        this.state = state;
    }

    @Override
    public void setPhone(long phone) {
        this.phone = phone;
    }

    @Override
    public void setAddressBook(AddressBook addressBook) {

    }

    @Override
    public void saveAll() {

    }
}
