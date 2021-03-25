package com.kotlindiscord.pluralkot.exceptions

/**
 * Base class for all exceptions thrown by PluralKot.
 *
 * @param message Human-readable error message.
 */
public open class PluralKotException(message: String?) : Exception(message)
