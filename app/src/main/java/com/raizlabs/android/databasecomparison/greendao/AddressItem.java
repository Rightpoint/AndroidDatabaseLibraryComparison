package com.raizlabs.android.databasecomparison.greendao;

import com.raizlabs.android.databasecomparison.IAddressItem;

/**
 * Description:
 */
public class AddressItem extends com.raizlabs.android.databasecomparison.greendao.gen.AddressItem implements IAddressItem<AddressBook> {
    @Override
    public void setPhone(long phone) {
        setPhone(phone);
    }

    @Override
    public void setAddressBook(AddressBook addressBook) {
        super.setAddressBook(addressBook);
    }

    @Override
    public void saveAll() {

    }
}
