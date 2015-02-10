package com.raizlabs;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.PropertyType;
import de.greenrobot.daogenerator.Schema;

public class Generator {

    public static void main(String[] args) {
        Schema schema = new Schema(1, "com.raizlabs.android.databasecomparison.greendao.gen");


        Entity simpleAddressItem = getAddressItemEntity(schema, "SimpleAddressItem");

        Entity addressItem = getAddressItemEntity(schema, "AddressItem");
        Entity contactItem = getContactItemEntity(schema);

        Entity addressBook = getAddressBookEntity(schema);

        addressItem.addToOne(addressBook,  addressItem.getProperties().get(0));
        contactItem.addToOne(addressBook,  contactItem.getProperties().get(0));
        addressBook.addToMany(addressItem, addressItem.getProperties().get(addressItem.getProperties().size()-1));
        addressBook.addToMany(contactItem, contactItem.getProperties().get(contactItem.getProperties().size()-1));

        try {
            new DaoGenerator().generateAll(schema,
                    "../app/src/main/java");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Entity getContactItemEntity(Schema schema) {
        Entity contactItem = schema.addEntity("Contact");
        contactItem.addIdProperty();
        contactItem.addStringProperty("name");
        contactItem.addStringProperty("email");
        return contactItem;
    }

    static Entity getAddressItemEntity(Schema schema, String name) {
        Entity simpleAddressItem = schema.addEntity(name);
        simpleAddressItem.addIdProperty();
        simpleAddressItem.addStringProperty("name");
        simpleAddressItem.addStringProperty("address");
        simpleAddressItem.addStringProperty("city");
        simpleAddressItem.addStringProperty("state");
        simpleAddressItem.addLongProperty("phone");
        return simpleAddressItem;
    }

    static Entity getAddressBookEntity(Schema schema) {
        Entity addressBook = schema.addEntity("AddressBook");
        addressBook.addIdProperty();
        addressBook.addStringProperty("name");
        addressBook.addStringProperty("author");
        return addressBook;
    }
}
