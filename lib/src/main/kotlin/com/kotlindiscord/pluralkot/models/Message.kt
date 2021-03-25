package com.kotlindiscord.pluralkot.models

import kotlinx.serialization.Serializable

@Serializable
@Suppress("DataClassShouldBeImmutable", "UndocumentedPublicProperty")
/**
 * Data class representing PluralKit's proxied message information.
 */
public data class Message(
    val timestamp: String,
    val id: String,
    val original: String,
    val sender: String,
    val channel: String,
    val system: System,
    val member: Member
)
