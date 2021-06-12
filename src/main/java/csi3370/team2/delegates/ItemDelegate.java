package csi3370.team2.delegates;

import io.micronaut.runtime.http.scope.RequestScope;
import csi3370.team2.models.ItemListItem;
import csi3370.team2.services.ItemService;

import javax.inject.Inject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

        ItemListItem item = new ItemListItem(name, itemType, description, parseDateString(dateString), rating, owningListId);
        itemService.saveNewItem(item);
    }

    public void removeItem(int itemId){
        itemService.removeItem(itemId);
    }

    public ItemListItem modifyItem(int itemId, String name, String type, String date){
        ItemListItem item = new ItemListItem();
        item.setItemId(itemId);
        item.setName(name);
        item.setType(type);
        item.setReleaseDate(parseDateString(date));

        itemService.updateItem(item);
        return itemService.loadItemById(itemId);
    }
    
    public ItemListItem fetchItem(int itemId){
        return itemService.loadItemById(itemId);
    }

    public void setRatingForItemByID(int itemId, int rating) {
        itemService.setRatingForItemById(itemId, rating);
    }

    public void updateItemDescription(int itemId, String description) {
        itemService.updateItemDescription(itemId, description);
    }

    private Date parseDateString(String date) {

        SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy");
        try {
            return format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
