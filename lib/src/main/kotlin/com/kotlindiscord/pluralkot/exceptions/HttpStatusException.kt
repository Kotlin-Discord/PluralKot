package com.kotlindiscord.pluralkot.exceptions

import khttp.responses.Response

/**
 * Exception thrown for API functions, when an unexpected HTTP status code is encountered.
 *
 * @param response khttp Response object.
 * @param code HTTP status code.
 */
public class HttpStatusException(
    public val response: Response,
    public val code: Int = response.statusCode
) : PluralKotException("HTTP status $code\n\nResponse: ${response.text}")
