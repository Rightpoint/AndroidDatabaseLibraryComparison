package com.raizlabs.android.databasecomparison.realm;

import com.raizlabs.android.databasecomparison.interfaces.IAddressItem;

import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.annotations.RealmClass;

/**
 * AddressItem for Realm
 */
public class AddressItem extends RealmObject implements IAddressItem<AddressBook> {
    public AddressBook addressBook;
    public String name;
    public String address;
    public String city;
    public String state;
    public long phone;

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
        // TODO: ???
    }
}
