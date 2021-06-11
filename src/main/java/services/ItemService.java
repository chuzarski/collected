package services;

import models.ItemListItem;

public interface ItemService {

    void saveNewItem(ItemListItem item);
    void removeItem(int itemId);
    void updateItem(int itemId);
}
