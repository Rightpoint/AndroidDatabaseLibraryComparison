package com.raizlabs.android.databasecomparison;

import android.annotation.TargetApi;
import android.os.Build;

import com.raizlabs.android.databasecomparison.interfaces.IAddressBook;
import com.raizlabs.android.databasecomparison.interfaces.IAddressItem;
import com.raizlabs.android.databasecomparison.interfaces.IContact;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Description:
 */
public class Generator {

    public static <AddressItem extends IAddressItem> Collection<AddressItem> getAddresses(Class<AddressItem> itemClass, int count) {
        return getAddresses(itemClass, count, null);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static <AddressItem extends IAddressItem, AddressBook extends IAddressBook<AddressItem, IContact>> Collection<AddressItem>
    getAddresses(Class<AddressItem> itemClass, int count, AddressBook addressBook) {
        Collection<AddressItem> addressItemCollection = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            AddressItem addressItem = null;
            try {
                addressItem = itemClass.newInstance();
                addressItem.setName("Test");
                addressItem.setAddress("5486 Memory Lane");
                addressItem.setCity("Bronx");
                addressItem.setState("NY");
                addressItem.setPhone(7185555555l);
                if (addressBook != null) {
                    addressItem.setAddressBook(addressBook);
                }
                addressItemCollection.add(addressItem);
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return addressItemCollection;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static <AddressBook extends IAddressBook, Contact extends IContact> Collection<Contact>
    getContacts(Class<Contact> contactClass, int count, AddressBook addressBook) {
        Collection<Contact> contactModelList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Contact contactModel = null;
            try {
                contactModel = contactClass.newInstance();
                contactModel.setName("Test");
                contactModel.setEmail("abgrosner@gmail.com");
                if (addressBook != null) {
                    contactModel.setAddressBook(addressBook);
                }
                contactModelList.add(contactModel);
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return contactModelList;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static <Contact extends IContact,
            AddressBook extends IAddressBook,
            AddressItem extends IAddressItem<AddressBook>> Collection<AddressBook>
    createAddressBooks(Class<AddressBook> addressBookClass,
                       Class<Contact> contactClass,
                       Class<AddressItem> addressItemClass,
                       int count) {
        Collection<AddressBook> addressBooks = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            AddressBook addressBook = null;
            try {
                addressBook = addressBookClass.newInstance();
                addressBook.setName("Test");
                addressBook.setAuthor("Andrew Grosner");
                addressBook.setAddresses(getAddresses(addressItemClass, count, addressBook));
                addressBook.setContacts(getContacts(contactClass, count, addressBook));
                addressBooks.add(addressBook);
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }

        }
        return addressBooks;
    }
}
