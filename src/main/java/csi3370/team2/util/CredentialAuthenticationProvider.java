package csi3370.team2.util;

import csi3370.team2.models.User;
import csi3370.team2.services.UserService;
import io.micronaut.http.HttpRequest;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.security.authentication.*;
import io.reactivex.Flowable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import org.reactivestreams.Publisher;

import javax.inject.Named;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;

@Singleton
public class CredentialAuthenticationProvider implements AuthenticationProvider {

    UserService userService;
    PasswordHasher hasher;
    Scheduler ioScheduler;

    public CredentialAuthenticationProvider(UserService userService, PasswordHasher hasher, @Named(TaskExecutors.IO) ExecutorService executorService) {
        this.userService = userService;
        this.hasher = hasher;
        this.ioScheduler = Schedulers.from(executorService);
    }

    @Override
    public Publisher<AuthenticationResponse> authenticate(HttpRequest<?> httpRequest, AuthenticationRequest<?, ?> authenticationRequest) {
        // we're going to run this on a background thread to free up the request thread
        return Flowable.fromCallable(() -> performAuth(authenticationRequest)).subscribeOn(ioScheduler);
    }

    private AuthenticationResponse performAuth(AuthenticationRequest<?, ?> authenticationRequest) {
        User userDetails = retrieveUserDetails(authenticationRequest);

        if (userDetails == null)
            throw new AuthenticationException("Invalid user credentials");

        if (hasher.verify(authenticationRequest.getSecret().toString(), userDetails.getPassword()))
            return issueValidToken(authenticationRequest, userDetails);
        else
            throw new AuthenticationException("Invalid user credentials");
    }

    private User retrieveUserDetails(AuthenticationRequest<?, ?> authenticationRequest) {
        return userService.loadUserByUsername(authenticationRequest.getIdentity().toString());
    }

    private AuthenticationResponse issueValidToken(AuthenticationRequest<?, ?> authRequest, User userModel) {
        ArrayList<String> permissions = new ArrayList<>();
        permissions.add("USER");
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("uid", userModel.getUserId());

        return new UserDetails(userModel.getUsername(), permissions, attributes);
    }
}
