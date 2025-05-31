package com.qa.api.constants;

/**
 * Enum representing different types of authentication methods.
 * This enum is used to specify the authentication type required for API requests.
 */
public enum AuthType {

    /** Authentication using a Bearer Token. */
    BEARER_TOKEN,

    /** Basic Authentication using username and password. */
    BASIC_AUTH,

    /** Authentication using an API key. */
    API_KEY,

    /** No authentication required. */
    NO_AUTH
}