package com.kotlindiscord.pluralkot.models

import com.kotlindiscord.pluralkot.enums.Privacy
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Suppress("DataClassShouldBeImmutable", "UndocumentedPublicProperty")
/**
 * Data class representing a PluralKit System.
 */
public data class System(
    val id: String,
    var name: String?,
    var description: String?,
    var tag: String?,

    @SerialName("avatar_url")
    var avatarUrl: String?,

    var tz: String,
    val created: String,

    @SerialName("description_privacy")
    var descriptionPrivacy: Privacy?,

    @SerialName("member_list_privacy")
    var memberListPrivacy: Privacy?,

    @SerialName("front_privacy")
    var frontPrivacy: Privacy?,

    @SerialName("front_history_privacy")
    var frontHistoryPrivacy: Privacy?
) {
    /**
     * Convert this data class to a map, without the fields that may not be modified.
     *
     * This is used when editing this object, to send the changes to PluralKit.
     */
    public fun toSendingMap(): Map<String, Any?> = mapOf(
        "name" to name,
        "description" to description,
        "tag" to tag,
        "avatar_url" to avatarUrl,
        "tz" to tz,
        "description_privacy" to descriptionPrivacy?.string,
        "member_list_privacy" to memberListPrivacy?.string,
        "front_privacy" to frontPrivacy?.string,
        "front_history_privacy" to frontHistoryPrivacy?.string,
    )
}
