package csi3370.team2.services;

import csi3370.team2.models.ItemList;
import csi3370.team2.models.ItemListItem;
import io.micronaut.core.annotation.NonNull;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;

import java.sql.ResultSet;

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

        try(Handle handle = jdbi.open()) {
            return handle.createQuery("SELECT * FROM ITEM_LIST WHERE ITEM_LIST_ID = :listId")
                    .bind("listId", listId)
                    .map((rs, ctx) -> new ItemList(
                            rs.getInt("ITEM_LIST_ID"),
                            rs.getString("LIST_NAME"),
                            rs.getString("LIST_TYPE"),
                            rs.getString("SORT_PREFERENCE"),
                            rs.getInt("OWNER_ID")
                    )).one();
        }
    }

    @Override
    public void setSortPreference(int listId, String sortOrder) {//Modify List Sort Preference
        try (Handle handle = jdbi.open()) {

            handle.createUpdate("UPDATE ITEM_LIST SET SORT_PREFERENCE = :sortOrder WHERE ITEM_LIST_ID = :listId")
                        .bind("listId", listId).bind("sortOrder", sortOrder).execute();
        }
    }
}
