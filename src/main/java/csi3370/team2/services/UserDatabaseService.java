package csi3370.team2.services;

import csi3370.team2.models.User;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.UnableToExecuteStatementException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;

@Singleton
public class UserDatabaseService implements UserService{

    private Jdbi jdbi;

    public UserDatabaseService(Jdbi datasource) {
        this.jdbi = datasource;
    }

    @Override
    public void saveNewUser(User user) {

        try(Handle handle = jdbi.open()) {
            handle.createUpdate("INSERT INTO USERS (USERNAME, PASSWORD, EMAIL) VALUES ( :uname, :password, :email )")
                    .bind("uname", user.getUsername())
                    .bind("password", user.getPassword())
                    .bind("email", user.getEmail())
                    .execute();
        } catch (UnableToExecuteStatementException e) {
            throw new RuntimeException("User already registered");
        }
    }

    @Override
    public User loadUserByUsername(String username) {
        User model;

        try(Handle handle = jdbi.open()) {
            model = handle.createQuery("SELECT USER_ID, USERNAME, PASSWORD, EMAIL FROM USERS WHERE USERNAME = :uname")
                    .bind("uname", username)
                    .map((rs, ctx) ->
                            // Map result to User object
                            new User(rs.getInt("USER_ID"),
                                    rs.getString("USERNAME"),
                                    rs.getString("PASSWORD"),
                                    rs.getString("EMAIL"))
                    ).one();
        }catch (IllegalStateException e) {
            // at this point, the database has not returned a rowset matching the username
            // The user will be returned null, at which point it's safe to say the user was not found
            model = null;
        }

        return model;
    }
}
