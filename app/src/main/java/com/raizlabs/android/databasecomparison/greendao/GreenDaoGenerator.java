package com.raizlabs.android.databasecomparison.greendao;

import com.raizlabs.android.databasecomparison.greendao.gen.AddressBook;
import com.raizlabs.android.databasecomparison.greendao.gen.AddressItem;
import com.raizlabs.android.databasecomparison.greendao.gen.Contact;
import com.raizlabs.android.databasecomparison.greendao.gen.SimpleAddressItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 */
public class GreenDaoGenerator {

    public static List<SimpleAddressItem> getSimpleAddressItems(int count) {
        final List<SimpleAddressItem> addressItemList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            com.raizlabs.android.databasecomparison.greendao.gen.SimpleAddressItem simpleAddressItem = new com.raizlabs.android.databasecomparison.greendao.gen.SimpleAddressItem();
            simpleAddressItem.setName("Test");
            simpleAddressItem.setAddress("5486 Memory Lane");
            simpleAddressItem.setCity("Bronx");
            simpleAddressItem.setState("NY");
            simpleAddressItem.setPhone(7185555555l);
            addressItemList.add(simpleAddressItem);
        }
        return addressItemList;
    }

    public static List<AddressItem> getAddressItems(int count, AddressBook addressBook) {
        final List<AddressItem> addressItemList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            AddressItem simpleAddressItem = new AddressItem();
            simpleAddressItem.setName("Test");
            simpleAddressItem.setAddress("5486 Memory Lane");
            simpleAddressItem.setCity("Bronx");
            simpleAddressItem.setState("NY");
            simpleAddressItem.setPhone(7185555555l);
            simpleAddressItem.setAddressBook(addressBook);
            addressItemList.add(simpleAddressItem);
        }
        return addressItemList;
    }

    public static List<Contact> getContacts(int count, AddressBook addressBook) {
        List<Contact> sugarModelList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Contact contact = new Contact();
            contact.setName("Test");
            contact.setEmail("abgrosner@gmail.com");
            if (addressBook != null) {
                contact.setAddressBook(addressBook);
            }
            sugarModelList.add(contact);
        }
        return sugarModelList;
    }


    public static List<AddressBook> createAddressBooks(int count) {
        List<AddressBook> addressBooks = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            AddressBook addressBook = new AddressBook();
            addressBook.setName("Test");
            addressBook.setAuthor("Andrew Grosner");
            addressBook.setAddressItemList(getAddressItems(count, addressBook));
            addressBook.setContactList(getContacts(count, addressBook));
            addressBooks.add(addressBook);
        }
        return addressBooks;
    }


}
