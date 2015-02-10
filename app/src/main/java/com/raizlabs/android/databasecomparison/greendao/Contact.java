package com.raizlabs.android.databasecomparison.greendao;

import com.raizlabs.android.databasecomparison.IContact;

/**
 * Description:
 */
public class Contact extends com.raizlabs.android.databasecomparison.greendao.gen.Contact implements IContact<AddressBook> {

    public Contact() {
    }

    public Contact(Long id) {
        super(id);
    }

    public Contact(Long id, String name, String email) {
        super(id, name, email);
    }

    public Contact(com.raizlabs.android.databasecomparison.greendao.gen.Contact contact) {
        super(contact.getId(), contact.getName(), contact.getEmail());
    }

    @Override
    public void saveAll() {

    }

    @Override
    public AddressBook getAddressBookField() {
        return (AddressBook) getAddressBook();
    }

    @Override
    public void setAddressBook(AddressBook addressBook) {
        super.setAddressBook(addressBook);
    }
}
