package csi3370.team2.controllers;

import csi3370.team2.delegates.ListDelegate;
import csi3370.team2.models.ItemList;
import io.micronaut.http.annotation.*;

import io.micronaut.http.HttpResponse;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.rules.SecurityRule;

import java.util.HashMap;
import java.util.Optional;
import java.util.Set;

@Controller
public class ListController {

    ListDelegate delegate;


    public ListController(ListDelegate delegate) {
        this.delegate = delegate;
    }

    @Get("/list/all")
    @Secured(SecurityRule.IS_AUTHENTICATED)
    public HttpResponse<Set<ItemList>> handleViewItemLists(Authentication authDetails) {
        int uid = Math.toIntExact((long) authDetails.getAttributes().get("uid"));
        return HttpResponse.ok(delegate.fetchAllListsForUserId(uid, ItemList.TYPE_COLLECTION));
    }

    @Get("/list/wishlists")
    @Secured(SecurityRule.IS_AUTHENTICATED)
    public HttpResponse<Set<ItemList>> handleViewWishlists(Authentication authDetails) {
        int uid = Math.toIntExact((long) authDetails.getAttributes().get("uid"));
        return HttpResponse.ok(delegate.fetchAllListsForUserId(uid, ItemList.TYPE_WISHLIST));
    }

    @Get("/list/{listId}")
    @Secured(SecurityRule.IS_AUTHENTICATED)
    public HttpResponse<ItemList> handleFetchList(@PathVariable("listId") int listId) {
        ItemList itemList = delegate.fetchListById(listId);

        if (itemList == null)
            return HttpResponse.notFound();
        return HttpResponse.ok(itemList);
    }

    @Post("/list/add")
    @Secured(SecurityRule.IS_AUTHENTICATED)
    public HttpResponse<ItemList> handleAddList(@Body HashMap<String, String> data, Authentication userDetails) {
        int uid = Math.toIntExact((long) userDetails.getAttributes().get("uid"));
        ItemList returnedItemList;

        if (!(data.containsKey("list_name") && data.containsKey("list_type")))
            return HttpResponse.badRequest();

        returnedItemList = delegate.createList(data.get("list_name"), data.get("list_type"), uid);

        return HttpResponse.ok(returnedItemList);
    }

    @Delete("/list/{listId}/remove")
    @Secured(SecurityRule.IS_AUTHENTICATED)
    public HttpResponse<String> handleRemoveList(@PathVariable int listId) {
        delegate.removeItemList(listId);
        return HttpResponse.ok("Removed");
    }

    @Post("/list/{listId}/rename")
    @Secured(SecurityRule.IS_AUTHENTICATED)
    public HttpResponse<String> handleRename(@PathVariable int listId, @Body HashMap<String, String> data) {
        if (!(data.containsKey("list_name")))
            return HttpResponse.badRequest();
        delegate.renameList(listId, data.get("list_name"));
        return HttpResponse.ok("Renamed");
    }

    @Post("/list/{listId}/sort/{direction}")
    @Secured(SecurityRule.IS_AUTHENTICATED)
    public HttpResponse<ItemList> handleSort(@PathVariable int listId, @PathVariable String direction) {
        ItemList list;
        delegate.setListSortPreferenceForListId(listId, direction);
        list = delegate.fetchListById(listId);

        return HttpResponse.ok(list);
    }

    @Get("/list/sortmethods")
    @Secured(SecurityRule.IS_ANONYMOUS)
    public HttpResponse<Set<String>> fetchValidSortMethods() {
        Set<String> sortMethods = delegate.fetchValidSortMethods();
        return HttpResponse.ok(sortMethods);
    }

}

