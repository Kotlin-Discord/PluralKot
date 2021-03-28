package com.kotlindiscord.pluralkot.exceptions

/**
 * Exception thrown when the PluralKit API states we don't have access to something.
 */
public class UnauthorizedException : PluralKotException(
    "Invalid system token, or you don't have permission to access this resource."
)
