package csi3370.team2.services;

import csi3370.team2.delegates.ItemDelegate;
import csi3370.team2.models.ItemListItem;
import io.micronaut.core.annotation.NonNull;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;

import javax.inject.Singleton;


@Singleton
public class ItemDatabaseService implements ItemService {

    private Jdbi jdbi;

    public ItemDatabaseService(Jdbi jdbi) {
        this.jdbi = jdbi;
    }
    @Override
    public void saveNewItem(@NonNull ItemListItem item) {

        try(Handle handle = jdbi.open()) {

            handle.createUpdate("INSERT INTO ITEM_DESCRIPTIONS (NAME, ITEM_TYPE, DESCRIPTION, RELEASE_DATE) VALUES (:name, :type, :description, :date); INSERT INTO ITEM_LIST_ITEMS (RATING, ITEM_DESCRIPTION_ID, ITEM_LIST_ID) VALUES ( :rating, (SELECT SCOPE_IDENTITY() LIMIT 1), :parentList)")
                    .bind("name", item.getName())
                    .bind("type", item.getType())
                    .bind("description", item.getDescription())
                    .bind("date", item.getReleaseDate())
                    .bind("name", item.getName())
                    .bind("rating", item.getRating())
                    .bind("parentList", item.getMemberListId())
                    .execute();

        }
    }

    @Override
    public void removeItem(int itemId) {
        try(Handle handle = jdbi.open()) {
            handle.createUpdate("DELETE FROM ITEM_DESCRIPTIONS WHERE DESCRIPTION_ID = (SELECT DESCRIPTION_ID FROM ITEM_DESCRIPTIONS JOIN ITEM_LIST_ITEMS IL on ITEM_DESCRIPTIONS.DESCRIPTION_ID = IL.ITEM_DESCRIPTION_ID WHERE IL.LIST_ITEM_ID = :itemId)")
                    .bind("itemId", itemId).execute();
        }
    }

    @Override
    public void updateItem(@NonNull ItemListItem item) {
        try(Handle handle = jdbi.open()) {
            handle.createUpdate("UPDATE ITEM_DESCRIPTIONS SET NAME = :itemName, ITEM_TYPE = :itemType, RELEASE_DATE = :itemDate WHERE DESCRIPTION_ID = (SELECT ITEM_DESCRIPTION_ID FROM ITEM_LIST_ITEMS WHERE LIST_ITEM_ID = :itemId LIMIT 1)")
                    .bind("itemName", item.getName())
                    .bind("itemType", item.getType())
                    .bind("itemDate", item.getReleaseDate())
                    .execute();
        }
    }

    @Override
    public ItemListItem loadItemById(int itemId) {

        try(Handle handle = jdbi.open()) {
            return handle.createQuery("SELECT * FROM ITEM_LIST_ITEMS IL JOIN ITEM_DESCRIPTIONS ID on ID.DESCRIPTION_ID = IL.ITEM_DESCRIPTION_ID WHERE LIST_ITEM_ID = :itemId")
                    .bind("itemId", itemId)
                    .map((rs, ctx) -> new ItemListItem(
                            rs.getInt("LIST_ITEM_ID"),
                            rs.getString("NAME"),
                            rs.getString("ITEM_TYPE"),
                            rs.getString("DESCRIPTION"),
                            rs.getDate("RELEASE_DATE"),
                            rs.getInt("RATING"),
                            rs.getInt("ITEM_LIST_ID")
                    )).one();
        }
    }

    @Override
    public void setRatingForItemById(int itemId, int rating) {

        try(Handle handle = jdbi.open()) {
            handle.createUpdate("UPDATE ITEM_LIST_ITEMS SET RATING = :rating WHERE LIST_ITEM_ID = :itemId")
                    .bind("rating", rating)
                    .bind("itemId", itemId)
                    .execute();
        }
    }

    @Override
    public void updateItemDescription(int itemId, String description) {
        try(Handle handle = jdbi.open()) {
            handle.createUpdate("UPDATE ITEM_DESCRIPTIONS SET DESCRIPTION = :description WHERE DESCRIPTION_ID = (SELECT ITEM_DESCRIPTION_ID FROM ITEM_LIST_ITEMS WHERE LIST_ITEM_ID = :itemId LIMIT 1)")
                    .bind("description", description)
                    .bind("itemId", itemId)
                    .execute();
        }
    }
}
