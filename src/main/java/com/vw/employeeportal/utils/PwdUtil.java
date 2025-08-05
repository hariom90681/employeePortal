package com.vw.employeeportal.utils;

import java.security.SecureRandom;

public class PwdUtil {

    // It's good practice to make these constants (static final) so they are created only once.
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();
    private static final int PASSWORD_LENGTH = 6; // The fixed length from your original code

    public static String generateRandomPwd() {
        // Use StringBuilder for efficiency when creating strings in a loop
        StringBuilder password = new StringBuilder(PASSWORD_LENGTH);

        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            // 1. Get a random index from 0 to the end of the CHARACTERS string
            int randomIndex = RANDOM.nextInt(CHARACTERS.length());

            // 2. Get the character at that random index
            char randomChar = CHARACTERS.charAt(randomIndex);

            // 3. Append it to the password
            password.append(randomChar);
        }

        return password.toString();
    }
}
