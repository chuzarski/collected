package csi3370.team2.services;

import csi3370.team2.domain.User;

public interface UserService {

    void saveNewUser(User user);
    User loadUserByUsername(String username);
}
