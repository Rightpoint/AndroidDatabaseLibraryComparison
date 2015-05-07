package com.raizlabs.android.databasecomparison.ormlite;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Description: Address Item DAO
 */
@DatabaseTable(tableName = "AddressItem")
public class AddressItem extends SimpleAddressItem {

    @DatabaseField(foreign=true,foreignAutoRefresh=true)
    private AddressBook addressBook;

    @Override
    public void setAddressBook(AddressBook addressBook) {
        this.addressBook = addressBook;
    }

}
