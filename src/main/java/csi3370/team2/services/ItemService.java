package csi3370.team2.services;

import csi3370.team2.models.ItemListItem;

public interface ItemService {

    void saveNewItem(ItemListItem item);
    void removeItem(int itemId);
    void updateItem(int itemId);
}
