package csi3370.team2.delegates;

import csi3370.team2.models.User;
import csi3370.team2.services.UserService;
import csi3370.team2.util.PasswordHasher;
import io.micronaut.runtime.http.scope.RequestScope;

@RequestScope
public class UserDelegate {

    UserService userService;
    PasswordHasher hasher;

    public UserDelegate(UserService service, PasswordHasher hasher) {
        this.userService = service;
        this.hasher = hasher;
    }

    public void registerUser(String username, String password, String email) {
        User model = new User(-1, username, hasher.encode(password), email);
        userService.saveNewUser(model);
    }


}
