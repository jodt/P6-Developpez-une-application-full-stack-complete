package com.openclassrooms.mdd_api.utils;

public class PasswordUtils {

    public static boolean isPasswordSecure(String password) {

        boolean hasOneDigit = password.matches(".*\\d.*");
        boolean hasUpperCase = password.matches(".*\\p{Upper}.*");
        boolean hasLowerCase = password.matches(".*\\p{Lower}.*");
        boolean hasSpecialCharacter = password.matches(".*[&!=+].*");
        boolean hasMinLength = password.length() >= 8;

        return hasOneDigit && hasLowerCase && hasUpperCase && hasSpecialCharacter && hasMinLength;
    }

}
