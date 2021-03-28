package com.kotlindiscord.pluralkot.models

import kotlinx.serialization.Serializable

@Serializable
@Suppress("DataClassShouldBeImmutable", "UndocumentedPublicProperty")
/**
 * Data class representing a Switch, with Member IDs instead of Member objects.
 */
public data class SwitchWithIDs(
    val timestamp: String,
    val members: List<String>
)
