package com.raizlabs.android.databasecomparison.sprinkles;

import se.emilsjolander.sprinkles.annotations.Column;
import se.emilsjolander.sprinkles.annotations.Table;

/**
 * Description:
 */
@Table("AddressItem")
public class AddressItem extends SimpleAddressItem {

    @Column("addressBook")
    private long addressBook_id;

    @Override
    public void setAddressBook(AddressBook addressBook) {
        this.addressBook_id = addressBook.getId();
    }
}
