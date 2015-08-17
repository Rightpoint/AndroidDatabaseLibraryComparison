package com.raizlabs.android.databasecomparison.ollie;


import ollie.annotation.Column;
import ollie.annotation.Table;

/**
 * Description:
 */
@Table("AddressItem")
public class AddressItem extends SimpleAddressItem
{

    @Column("addressBook")
    public AddressBook addressBook;

    @Override
    public void setAddressBook(AddressBook addressBook) {
        this.addressBook = addressBook;
    }

}
