package com.raizlabs.android.databasecomparison.sprinkles;

import com.raizlabs.android.databasecomparison.interfaces.IAddressItem;

import se.emilsjolander.sprinkles.Model;
import se.emilsjolander.sprinkles.annotations.AutoIncrement;
import se.emilsjolander.sprinkles.annotations.Column;
import se.emilsjolander.sprinkles.annotations.Key;
import se.emilsjolander.sprinkles.annotations.Table;

/**
 * Description:
 */
@Table("SimpleAddressItem")
public class SimpleAddressItem extends Model implements IAddressItem<AddressBook> {

    @Column("id")
    @AutoIncrement
    @Key
    private long id;

    @Column("name")
    private String name;

    @Column("address")
    private String address;

    @Column("city")
    private String city;

    @Column("state")
    private String state;

    @Column("phone")
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
        super.save();
    }
}
