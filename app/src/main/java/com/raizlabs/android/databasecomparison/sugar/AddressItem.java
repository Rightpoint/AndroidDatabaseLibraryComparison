package com.raizlabs.android.databasecomparison.sugar;

import com.orm.SugarRecord;
import com.raizlabs.android.databasecomparison.IAddressItem;

/**
 * Description:
 */
public class AddressItem extends SugarRecord<AddressItem> implements IAddressItem<AddressBook> {

    private AddressBook addressBook;

    private String name;

    private String address;

    private String city;

    private String state;

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
        this.addressBook = addressBook;
    }

    @Override
    public void saveAll() {
        super.save();
    }
}
