package csi3370.team2.services;

import csi3370.team2.models.ItemList;

import java.util.Set;

public interface ListService {

    void createList(ItemList itemList);
    void removeList(int listId);
    void renameList(int listID, String listName);
    ItemList loadListById(int listId);
    Set<ItemList> loadOwnersLists(int ownerId);
    void setSortPreference(int listId, String sortOrder);
}
