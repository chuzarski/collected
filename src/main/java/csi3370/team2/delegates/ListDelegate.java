package csi3370.team2.delegates;

import csi3370.team2.models.ItemList;
import csi3370.team2.services.ListService;
import io.micronaut.runtime.http.scope.RequestScope;

import java.util.Set;

@RequestScope
public class ListDelegate {

    private ListService listService;

    public ListDelegate(ListService listService) {
        this.listService = listService;
    }

    public Set<ItemList> fetchAllListsForUserId(int userId) {
        return listService.loadOwnersLists(userId);
    }

    public ItemList fetchListById(int listId) {
        return listService.loadListById(listId);
    }

    public ItemList createList(String name, String listType, int ownerId) {
        ItemList itemList = new ItemList();

        itemList.setListType(listType);
        itemList.setName(name);
        itemList.setOwnerId(ownerId);
        itemList.setSortPreference("DEFAULT"); // TODO this needs to be configured for a "DEFAULT VALUE"

        return listService.createList(itemList);
    }

    public void removeItemList(int listId) {
        listService.removeList(listId);
    }

    public void renameList(int listId, String listName) {
        listService.renameList(listId, listName);
    }
}
