package csi3370.team2.delegates;

import io.micronaut.runtime.http.scope.RequestScope;
import csi3370.team2.models.ItemListItem;
import csi3370.team2.services.ItemService;

import javax.inject.Inject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;

@RequestScope
public class ItemDelegate {

    @Inject
    private ItemService itemService;

    public ItemDelegate(ItemService itemService){
        this.itemService = itemService;
    }

    public void addItem(
            String name,
            String itemType,
            String description,
            String dateString,
            int rating,
            int owningListId
    ) {

        SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy");
        Date release = null;
        try {
            release = format.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ItemListItem item = new ItemListItem(name, itemType, description, release, rating, owningListId);
        itemService.saveNewItem(item);
    }

    public void removeItem(int itemId){
        itemService.removeItem(itemId);
    }

    public void modifyItem(int itemId){
        itemService.updateItem(null); //fixme make sure this is passed an ItemListItem
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
