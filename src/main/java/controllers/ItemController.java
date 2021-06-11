package controllers;

import delegates.ItemDelegate;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.reactivex.Single;
import models.ItemListItem;

import javax.inject.Inject;

@Controller
public class ItemController {

    @Inject
    ItemDelegate itemDelegate;

    public ItemController(ItemDelegate itemDelegate){
        this.itemDelegate = itemDelegate;
    }

    @Post("/item/add")
    @Secured(SecurityRule.IS_AUTHENTICATED)
    public HttpResponse<String> addItem(){
        itemDelegate.addItem();
        return HttpResponse.ok();
    }

    @Delete("/item/remove/{itemId}")
    @Secured(SecurityRule.IS_AUTHENTICATED)
    public HttpResponse<Object> removeItem(@PathVariable int itemId){
        itemDelegate.removeItem(itemId);
        return HttpResponse.ok();
        //System.out.println("Error Removing Item");
    }

    @Post("/item/modify/{itemId}")
    @Secured(SecurityRule.IS_AUTHENTICATED)
    public HttpResponse<Object> modifyItem(@PathVariable int itemId){
        itemDelegate.modifyItem(itemId);
        return HttpResponse.ok();
        //System.out.println("Error Modifying Item");
    }

    @Get("/item/view/{itemId}")
    @Secured(SecurityRule.IS_AUTHENTICATED)
    public HttpResponse<ItemListItem> handleViewItem(@PathVariable int itemId){
        if(String.valueOf(itemId) != null)
            return (HttpResponse<ItemListItem>) itemDelegate.fetchItem(itemId);
        else
            return null;
    }

    @Post("/item/rating/{itemId}/{rating}")
    @Secured(SecurityRule.IS_AUTHENTICATED)
    public HttpResponse<ItemListItem> handleItemRating(@PathVariable("itemId") int itemId, @PathVariable("rating") int rating){
        if(String.valueOf(itemId) != null && String.valueOf(rating) != null)
            return (HttpResponse<ItemListItem>) itemDelegate.setRatingForItemByID(itemId, rating);
        else
            return null;
    }

    @Post("/wishlistitem/add")
    @Secured(SecurityRule.IS_AUTHENTICATED)
    public HttpResponse<ItemListItem> addWishListItem(){
        return (HttpResponse<ItemListItem>) itemDelegate.addWishListItem();
    }

    @Delete("/wishlistitem/modify/{itemId}")
    @Secured(SecurityRule.IS_AUTHENTICATED)
    public HttpResponse<ItemListItem> removeWishListItem(@PathVariable int itemId){
        if(String.valueOf(itemId) != null)
            return (HttpResponse<ItemListItem>) itemDelegate.removeWishListItemById(itemId);
        else
            return  null;
        //System.out.println("Error Removing WishList Item");
    }

    @Post("/wishlistitem/modify/{itemId}")
    @Secured(SecurityRule.IS_AUTHENTICATED)
    public HttpResponse<ItemListItem> modifyWishListItem(@PathVariable int itemId){
        if(String.valueOf(itemId) != null)
            return (HttpResponse<ItemListItem>) itemDelegate.modifyWishListItem(itemId);
        else
            return null;
        //System.out.println("Error Modifying WishList Item");
    }

    @Get("/wishlistitem/view/{itemId}")
    @Secured(SecurityRule.IS_AUTHENTICATED)
    public HttpResponse<ItemListItem> handleViewWishListItem(@PathVariable int itemId){
        if(String.valueOf(itemId) != null)
            return (HttpResponse<ItemListItem>) itemDelegate.fetchWishListItem(itemId);
        else
            return null;
    }

    @Post("item/modify/{itemId}")
    @Secured(SecurityRule.IS_AUTHENTICATED)
    public HttpResponse<ItemListItem> updateDescription(@PathVariable int itemId, String description){
        //String description = "Test Description";
        if(String.valueOf(itemId) != null)
            return (HttpResponse<ItemListItem>) itemDelegate.updateItemDescription(itemId, description);
        else
            return null;
        //System.out.println("Error Updating Item Description");
    }

}
