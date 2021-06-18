package csi3370.team2.controllers;

import csi3370.team2.delegates.UserDelegate;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.reactivex.Single;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

@Controller
public class UserController {

    Logger log = LoggerFactory.getLogger("UserController");

    UserDelegate delegate;

    public UserController(UserDelegate delegate) {
        this.delegate = delegate;
    }

    @Post("/register")
    @Secured(SecurityRule.IS_ANONYMOUS)
    public Single<HttpResponse<String>> handleRegister(@Body Single<HashMap<String, String>> requestData) {
        return requestData.map(jsonData -> {
            if (!(jsonData.containsKey("username") || jsonData.containsKey("password") || jsonData.containsKey("email"))) {
                return HttpResponse.badRequest("Invalid form");
            }
            try {
                delegate.registerUser(jsonData.get("username"), jsonData.get("password"), jsonData.get("email"));
            } catch (RuntimeException e) {
                return HttpResponse.badRequest("Duplicate username not allowed");
            }

            return HttpResponse.created("Registered");
        });
    }
}
