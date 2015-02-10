package com.raizlabs.android.databasecomparison.greendao;

import com.raizlabs.android.databasecomparison.IAddressItem;

/**
 * Description:
 */
public class AddressItem extends com.raizlabs.android.databasecomparison.greendao.gen.AddressItem implements IAddressItem<AddressBook> {

    public AddressItem(Long id) {
        super(id);
    }

    public AddressItem() {
    }

    public AddressItem(Long id, String name, String address, String city, String state, Long phone) {
        super(id, name, address, city, state, phone);
    }

    public AddressItem(com.raizlabs.android.databasecomparison.greendao.gen.AddressItem addressItem) {
        super(addressItem.getId(), addressItem.getName(), addressItem.getAddress(), addressItem.getCity(),
                addressItem.getState(), addressItem.getPhone());
    }

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
