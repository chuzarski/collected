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
}
