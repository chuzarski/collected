package csi3370.team2.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.inject.Singleton;

@Singleton
public class PasswordHasher {

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public String encode(String password) {
        return encoder.encode(password);
    }

    public boolean verify(String plainPassword, String hashedPassword) {
        return encoder.matches(plainPassword, hashedPassword);
    }

}
