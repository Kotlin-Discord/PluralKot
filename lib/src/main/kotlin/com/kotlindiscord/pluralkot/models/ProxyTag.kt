package com.kotlindiscord.pluralkot.models

import kotlinx.serialization.Serializable

@Serializable
@Suppress("DataClassShouldBeImmutable", "UndocumentedPublicProperty")
/**
 * Data class representing a potential pair of prefix and suffix used to match messages for proxying.
 */
public data class ProxyTag(
    val prefix: String?,
    val suffix: String?
) {
    /**
     * Convert this data class to a map, without the fields that may not be modified.
     *
     * This is used when editing this object, to send the changes to PluralKit.
     */
    public fun toSendingMap(): Map<String, Any?> = mapOf(
        "prefix" to prefix,
        "suffix" to suffix,
    )
}
