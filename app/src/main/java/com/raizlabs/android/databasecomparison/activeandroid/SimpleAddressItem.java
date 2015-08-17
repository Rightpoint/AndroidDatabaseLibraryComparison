package com.raizlabs.android.databasecomparison.activeandroid;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.raizlabs.android.databasecomparison.interfaces.IAddressItem;

/**
 * Description:
 */
@Table(name = "SimpleAddressItem")
public class SimpleAddressItem extends Model implements IAddressItem<AddressBook> {

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "phone")
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
        super.saveSilently();
    }
}
