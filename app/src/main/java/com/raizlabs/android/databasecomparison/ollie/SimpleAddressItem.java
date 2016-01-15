package com.raizlabs.android.databasecomparison.ollie;

import com.raizlabs.android.databasecomparison.interfaces.IAddressItem;

import ollie.Model;
import ollie.annotation.Column;
import ollie.annotation.Table;

/**
 * Description:
 */
@Table("SimpleAddressItem")
public class SimpleAddressItem extends Model implements IAddressItem<AddressBook> {

    @Column("name")
    public String name;

    @Column("address")
    public String address;

    @Column("city")
    public String city;

    @Column("state")
    public String state;

    @Column("phone")
    public Long phone;


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
        super.save();
    }
}
