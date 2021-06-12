package csi3370.team2.controllers;

import csi3370.team2.delegates.ListDelegate;
import csi3370.team2.models.ItemList;
import io.micronaut.http.annotation.*;

import io.micronaut.http.HttpResponse;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.rules.SecurityRule;

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
        return HttpResponse.ok(delegate.fetchAllListsForUserId(uid));
    }

    @Get("/list/{itemId}")
    @Secured(SecurityRule.IS_AUTHENTICATED)
    public HttpResponse<ItemList> handleFetchList(@PathVariable("itemId") int itemId) {
        ItemList itemList = delegate.fetchListById(itemId);

        if (itemList == null)
            return HttpResponse.notFound();
        return HttpResponse.ok(itemList);
    }
}

