package com.kotlindiscord.pluralkot.models

import kotlinx.serialization.Serializable

@Serializable
@Suppress("DataClassShouldBeImmutable", "UndocumentedPublicProperty")
/**
 * Data class representing a Switch, with Member objects instead of Member IDs.
 */
public data class SwitchWithMembers(
    val timestamp: String,
    val members: List<Member>
)
