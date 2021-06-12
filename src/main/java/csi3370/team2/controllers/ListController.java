package csi3370.team2.controllers;

import io.micronaut.http.annotation.Controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

import java.util.HashMap;

@Controller
public class ListController {

    int itemListID, itemId, listId;
    String sort;

    @Get("/HelloWorld")
    @Secured(SecurityRule.IS_ANONYMOUS)
    public HttpResponse<String> handleHelloWorld(){
        return HttpResponse.ok("Hello World");
    }

    @Post("/EchoMessage")
    @Secured(SecurityRule.IS_ANONYMOUS)
    public HttpResponse<String> handleEcho(@Body HashMap<String, String> body){
        return HttpResponse.ok(body.get("message"));
    }


    @Post("/first")
    @Secured(SecurityRule.IS_ANONYMOUS)
    public HttpResponse<String> addItemList(@Body HashMap<String, String> body) {
        return HttpResponse.ok(body.get("gone"));
    }

    @Post("/second")
    @Secured(SecurityRule.IS_ANONYMOUS)
    public HttpResponse<String> removeItemList(@Body HashMap<String, String> body){
        return HttpResponse.ok(body.get("umbrella"));
    }

    @Post("/third")
    @Secured(SecurityRule.IS_ANONYMOUS)
    public HttpResponse<String> renameItemList(@Body HashMap<String, String> body) {
        return HttpResponse.ok(body.get("letter"));
    }

    @Post("/fourth")
    @Secured(SecurityRule.IS_ANONYMOUS)
    public HttpResponse<String> handleViewItemList(@Body HashMap<String, String> body) {
        return HttpResponse.ok(body.get("friday"));
    }

    @Post("/fifth")
    @Secured(SecurityRule.IS_ANONYMOUS)
    public HttpResponse<String> fetchSortedList(@Body HashMap<String, String> body) {
        return HttpResponse.ok(body.get("soon"));
    }
}

