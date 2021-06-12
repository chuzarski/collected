package csi3370.team2.services;

import csi3370.team2.models.ItemList;
import csi3370.team2.models.ItemListItem;
import io.micronaut.core.annotation.NonNull;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;

public class ListDatabaseService implements ListService {

    private Jdbi jdbi;

    public ListDatabaseService(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    @Override
    public void createList(ItemList itemList) {

        String name = itemList.getName();
        String listType = itemList.getListType();
        String sortPreference = itemList.getSortPreference();
        int ownerId = itemList.getOwnerId();

        try (Handle handle = jdbi.open()) {

            handle.createUpdate(
                    "INSERT INTO ITEM_LIST (NAME, LIST_TYPE, SORT_PREFERENCE, OWNER_ID)" +
                            "VALUES (" + name +
                            "," + listType +
                            "," + sortPreference +
                            "," + ownerId +
                            ")").execute();

        }

    }

    @Override
    public void removeList(int listId) {

    }

    @Override
    public void renameList(int listID, String listName) {//Modify List Name
        /*try (Handle handle = jdbi.open()) {

            handle.createUpdate(
                    "UPDATE ITEM_LIST SET NAME =" + listName + " WHERE)").execute();

        }*/
    }

    @Override
    public ItemList loadListById(int itemId) {
        ItemList itemList = new ItemList("Test","Wish","yes",1);//Random info to remove error
        return itemList;
    }

    @Override
    public void setSortPreference(int itemId, String sortOrder) {//Modify List Sort Preference

    }
}
