package csi3370.team2.services;

import csi3370.team2.models.ItemList;
import csi3370.team2.util.ItemListProducer;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;

import javax.inject.Singleton;
import java.util.Set;

@Singleton
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
                    "INSERT INTO ITEM_LIST (LIST_NAME, LIST_TYPE, SORT_PREFERENCE, OWNER_ID) VALUES (:name, :listType, :sortPreference, :ownerId)")
                    .bind("name", name)
                    .bind("listType", listType)
                    .bind("sortPreference", sortPreference)
                    .bind("ownerId", ownerId)
                    .execute();
        }

    }

    @Override
    public void removeList(int listId) {
        try (Handle handle = jdbi.open()) {
            handle.createUpdate("DELETE FROM ITEM_DESCRIPTIONS WHERE DESCRIPTION_ID = (SELECT ITEM_DESCRIPTION_ID FROM ITEM_LIST_ITEMS WHERE ITEM_LIST_ID = :listId); DELETE FROM ITEM_LISTS WHERE ITEM_LIST_ID = :listId")
                    .bind("listId", listId)
                    .execute();
        }
    }

    @Override
    public void renameList(int listId, String listName) {//Modify List Name
        try (Handle handle = jdbi.open()) {

            handle.createUpdate(
                    "UPDATE ITEM_LIST SET LIST_NAME = :listName WHERE ITEM_LIST_ID = :listId)")
                    .bind("listName", listName)
                    .bind("listId", listId)
                    .execute();

        }
    }

    @Override
    public ItemList loadListById(int listId) {
        Set<ItemList> itemSet;
        try(Handle handle = jdbi.open()) {
            itemSet = handle.createQuery("SELECT I.ITEM_LIST_ID, I.LIST_TYPE, I. SORT_PREFERENCE, I.OWNER_ID, I.LIST_NAME, IL.LIST_ITEM_ID AS ITEM_ID, IL.RATING AS ITEM_RATING,\n" +
                    "       ID.NAME AS ITEM_NAME, ID.ITEM_TYPE, ID.DESCRIPTION, ID.RELEASE_DATE\n" +
                    "FROM ITEM_LIST_ITEMS IL\n" +
                    "    JOIN ITEM_DESCRIPTIONS ID on IL.ITEM_DESCRIPTION_ID = ID.DESCRIPTION_ID\n" +
                    "    JOIN ITEM_LISTS I on IL.ITEM_LIST_ID = I.ITEM_LIST_ID\n" +
                    "WHERE I.LIST_TYPE = 'COLLECTION' AND I.ITEM_LIST_ID= :listId")
                    .bind("listId", listId)
                    .execute(new ItemListProducer());
        }
        if (itemSet.isEmpty())
            return null;
        return (ItemList) itemSet.toArray()[0];
    }

    @Override
    public Set<ItemList> loadOwnersLists(int ownerId) {
        Set<ItemList> itemSet;
        try(Handle handle = jdbi.open()) {
            itemSet = handle.createQuery("SELECT I.ITEM_LIST_ID, I.LIST_TYPE, I. SORT_PREFERENCE, I.OWNER_ID, I.LIST_NAME, IL.LIST_ITEM_ID AS ITEM_ID, IL.RATING AS ITEM_RATING,\n" +
                    "       ID.NAME AS ITEM_NAME, ID.ITEM_TYPE, ID.DESCRIPTION, ID.RELEASE_DATE\n" +
                    "FROM ITEM_LIST_ITEMS IL\n" +
                    "    JOIN ITEM_DESCRIPTIONS ID on IL.ITEM_DESCRIPTION_ID = ID.DESCRIPTION_ID\n" +
                    "    JOIN ITEM_LISTS I on IL.ITEM_LIST_ID = I.ITEM_LIST_ID\n" +
                    "WHERE I.LIST_TYPE = 'COLLECTION' AND I.OWNER_ID = :owner")
                    .bind("owner", ownerId)
                    .execute(new ItemListProducer());
        }

        return itemSet;
    }


    @Override
    public void setSortPreference(int listId, String sortOrder) {//Modify List Sort Preference
        try (Handle handle = jdbi.open()) {

            handle.createUpdate("UPDATE ITEM_LIST SET SORT_PREFERENCE = :sortOrder WHERE ITEM_LIST_ID = :listId")
                        .bind("listId", listId).bind("sortOrder", sortOrder).execute();
        }
    }
}
