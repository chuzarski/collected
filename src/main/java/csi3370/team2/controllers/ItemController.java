package csi3370.team2.controllers;

import csi3370.team2.delegates.ItemDelegate;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import csi3370.team2.models.ItemListItem;

import javax.inject.Inject;
import java.util.HashMap;

@Controller
public class ItemController {

    @Inject
    ItemDelegate itemDelegate;

    public ItemController(ItemDelegate itemDelegate){
        this.itemDelegate = itemDelegate;
    }

    @Post("/item/add")
    @Secured(SecurityRule.IS_AUTHENTICATED)
    public HttpResponse<String> addItem(@Body HashMap<String, String> data){


        itemDelegate.addItem(
                data.get("name"),
                data.get("type"),
                data.get("description"),
                data.get("release_date"),
                Integer.parseInt(data.get("rating")),
                Integer.parseInt(data.get("parent_list"))
        );
        return HttpResponse.ok();
    }

    @Delete("/item/{itemId}/remove")
    @Secured(SecurityRule.IS_AUTHENTICATED)
    public HttpResponse<Object> removeItem(@PathVariable int itemId){
        itemDelegate.removeItem(itemId);
        return HttpResponse.ok("Deleted!");
    }

    @Post("/item/{itemId}/modify")
    @Secured(SecurityRule.IS_AUTHENTICATED)
    public HttpResponse<ItemListItem> modifyItem(@PathVariable int itemId, @Body HashMap<String, String> data){

        if (!(data.containsKey("name") && data.containsKey("type") && data.containsKey("release_date") ))
            return HttpResponse.badRequest();

        return HttpResponse.ok(itemDelegate.modifyItem(
                itemId,
                data.get("name"),
                data.get("type"),
                data.get("release_date")
        ));
    }

    @Get("/item/{itemId}")
    @Secured(SecurityRule.IS_AUTHENTICATED)
    public HttpResponse<ItemListItem> handleViewItem(@PathVariable int itemId){
        ItemListItem item = itemDelegate.fetchItem(itemId);
        return HttpResponse.ok(item);
    }

    @Post("/item/{itemId}/rating/{rating}")
    @Secured(SecurityRule.IS_AUTHENTICATED)
    public HttpResponse<String> handleItemRating(@PathVariable("itemId") int itemId, @PathVariable("rating") int rating){
        itemDelegate.setRatingForItemByID(itemId, rating);
        return HttpResponse.ok("Updated");
    }

    @Post("item/{itemId}/description")
    @Secured(SecurityRule.IS_AUTHENTICATED)
    public HttpResponse<String> updateDescription(@PathVariable int itemId, @Body HashMap<String, String> data){

        if (!data.containsKey("description"))
            return HttpResponse.badRequest("missing description JSON key");
        itemDelegate.updateItemDescription(itemId, data.get("description"));

        return HttpResponse.ok();
    }

}
