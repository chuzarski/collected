package csi3370.team2.delegates;

import io.micronaut.runtime.http.scope.RequestScope;
import csi3370.team2.models.ItemListItem;
import csi3370.team2.services.ItemService;

import javax.inject.Inject;

@RequestScope
public class ItemDelegate {

    @Inject
    private ItemService itemService;

    public ItemDelegate(ItemService itemService){
        this.itemService = itemService;
    }

    public void addItem() {
        ItemListItem item = new ItemListItem();
        itemService.saveNewItem(item);
    }

    public void removeItem(int itemId){
        itemService.removeItem(itemId);
    }

    public void modifyItem(int itemId){
        itemService.updateItem(itemId);
    }
    
    public ItemListItem fetchItem(int itemId){
        ItemListItem item = new ItemListItem();
        return  item;
    }
    
    public ItemListItem setRatingForItemById(int itemId, int rating){
        ItemListItem item = new ItemListItem();
        return  item;
    }

    public ItemListItem setRatingForItemByID(int itemId, int rating) {
        ItemListItem item = new ItemListItem();
        return  item;
    }

    public ItemListItem addWishListItem() {
        ItemListItem item = new ItemListItem();
        return  item;
    }

    public ItemListItem removeWishListItemById(int itemId) {
        ItemListItem item = new ItemListItem();
        return  item;
    }

    public ItemListItem modifyWishListItem(int itemId) {
        ItemListItem item = new ItemListItem();
        return  item;
    }

    public ItemListItem fetchWishListItem(int itemId) {
        ItemListItem item = new ItemListItem();
        return  item;
    }

    public ItemListItem updateItemDescription(int itemId, String description) {
        ItemListItem item = new ItemListItem();
        return  item;
    }
}