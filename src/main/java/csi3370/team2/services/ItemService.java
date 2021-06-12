package csi3370.team2.services;

import csi3370.team2.models.ItemListItem;

public interface ItemService {

    void saveNewItem(ItemListItem item);
    void removeItem(int itemId);
    void updateItem(ItemListItem item);
    ItemListItem loadItemById(int itemId);
    void setRatingForItemById(int itemId, int rating);
    void updateItemDescription(int itemId, String description);
}
