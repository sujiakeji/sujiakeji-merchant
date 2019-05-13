package com.sujiakeji.merchant.util.common;

import com.google.common.base.Strings;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtils {

    public String encryptPassword(String password) {
        if (Strings.isNullOrEmpty(password)) {
            return null;
        }
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public Boolean checkPassword(String candidatePassword, String encryptedPassword) {
        if (Strings.isNullOrEmpty(candidatePassword) ||
                Strings.isNullOrEmpty(encryptedPassword)) {
            return false;
        }
        return BCrypt.checkpw(candidatePassword, encryptedPassword);
    }

    public String generateCode() {
        return String.valueOf((int) ((Math.random() * 9 + 1) * 100000));
    }
}
