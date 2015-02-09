package com.raizlabs.android.databasecomparison.dbflow;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKeyReference;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.container.ForeignKeyContainer;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Description:
 */
@Table(databaseName = DBFlowDatabase.NAME)
public class AddressItem extends SimpleAddressItem {

    @Column(name = "addressBook", columnType = Column.FOREIGN_KEY,
            references = {@ForeignKeyReference(columnName = "addressBook", columnType = long.class, foreignColumnName = "id")},
            saveForeignKeyModel = false)
    ForeignKeyContainer<AddressBook> addressBook;


    @Override
    public void setAddressBook(AddressBook addressBook) {
        this.addressBook = new ForeignKeyContainer<>(AddressBook.class);
        Map<String, Object> keys = new LinkedHashMap<>();
        keys.put(AddressBook$Table.ID, addressBook.id);
        this.addressBook.setData(keys);
    }
}
