package com.qa.api.utils;

/**
 * Utility class for string operations.
 * Provides methods to generate random strings for testing purposes.
 */
public class StringUtils {

    /**
     * Generates a random email ID for testing purposes.
     * The email ID is constructed using a fixed prefix, the current system time in milliseconds,
     * and a fixed domain.
     *
     * @return A randomly generated email ID in the format "automationTesting<timestamp>@gmail.com".
     */
    public static String generateRandomEmailId() {
        return "automationTesting" + System.currentTimeMillis() + "@gmail.com";
    }
}