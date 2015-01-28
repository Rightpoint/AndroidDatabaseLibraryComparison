package com.raizlabs.android.databasecomparison.sprinkles;

import com.raizlabs.android.databasecomparison.IContact;

import java.util.List;

import se.emilsjolander.sprinkles.Model;
import se.emilsjolander.sprinkles.Query;
import se.emilsjolander.sprinkles.annotations.AutoIncrement;
import se.emilsjolander.sprinkles.annotations.Column;
import se.emilsjolander.sprinkles.annotations.Key;
import se.emilsjolander.sprinkles.annotations.Table;

/**
 * Description:
 */
@Table("contact")
public class Contact extends Model implements IContact<AddressBook>{

    @Column("id")
    @AutoIncrement
    @Key
    private long id;

    @Column("name")
    private String name;

    @Column("email")
    private String email;

    @Column("addressBook")
    private long addressBook_id;

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
    public AddressBook getAddressBook() {
        return Query.one(AddressBook.class, "id = ?" , addressBook_id).get();
    }

    @Override
    public void setAddressBook(AddressBook addressBook) {
        this.addressBook_id = addressBook.getId();
    }

    @Override
    public void saveAll() {
        super.save();
    }
}
