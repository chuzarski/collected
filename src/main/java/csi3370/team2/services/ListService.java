package csi3370.team2.services;

import csi3370.team2.models.ItemList;

public interface ListService {

    void createList(ItemList itemList);
    void removeList(int listId);
    void renameList(int listID, String listName);
    ItemList loadListById(int itemId);
    void setSortPreference(int itemId, String sortOrder);
}
