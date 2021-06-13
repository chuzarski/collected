package csi3370.team2.delegates;

import csi3370.team2.models.ItemList;
import csi3370.team2.services.ListService;
import csi3370.team2.util.ItemListItemComparators;
import io.micronaut.runtime.http.scope.RequestScope;

import java.util.Set;

@RequestScope
public class ListDelegate {

    private ListService listService;
    private ItemListItemComparators comparators;

    public ListDelegate(ListService listService, ItemListItemComparators comparators) {
        this.listService = listService;
        this.comparators = comparators;
    }

    public Set<ItemList> fetchAllListsForUserId(int userId) {
        Set<ItemList> lists = listService.loadOwnersLists(userId);

        for (ItemList list : lists) {
            list.applySortPreference(comparators.getComparator(list.getSortPreference()));
        }

        return listService.loadOwnersLists(userId);
    }

    public ItemList fetchListById(int listId) {

        ItemList theList = listService.loadListById(listId);
        theList.applySortPreference(comparators.getComparator(theList.getSortPreference()));
        return theList;
    }

    public ItemList createList(String name, String listType, int ownerId) {
        ItemList itemList = new ItemList();

        itemList.setListType(listType);
        itemList.setName(name);
        itemList.setOwnerId(ownerId);
        itemList.setSortPreference("NAME"); // all new lists are sorted by NAME, by default

        return listService.createList(itemList);
    }

    public void removeItemList(int listId) {
        listService.removeList(listId);
    }

    public void renameList(int listId, String listName) {
        listService.renameList(listId, listName);
    }

    public void setListSortPreferenceForListId(int listId, String sortPreference) {
        if (!comparators.checkValidSortMethod(sortPreference))
            throw new RuntimeException("Incorrect sort preference");
        listService.setSortPreference(listId, sortPreference);
    }

    public Set<String> fetchValidSortMethods() {
        return comparators.getValidSortMethods();
    }

}
