package com.kotlindiscord.pluralkot.exceptions

/**
 * Exception thrown when a system token is required, but one wasn't provided.
 */
public class TokenMissingException : PluralKotException("This API call requires a system token.")
