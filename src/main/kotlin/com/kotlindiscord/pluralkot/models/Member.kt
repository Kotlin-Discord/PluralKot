package com.kotlindiscord.pluralkot.models

import com.kotlindiscord.pluralkot.enums.Privacy
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Suppress("DataClassShouldBeImmutable", "UndocumentedPublicProperty")
/**
 * Data class representing a single Member of a System.
 */
public data class Member(
    val id: String,
    var name: String?,

    @SerialName("display_name")
    var displayName: String?,

    var description: String?,
    var pronouns: String?,
    var color: String?,

    @SerialName("avatar_url")
    var avatarUrl: String?,

    var birthday: String?,

    @Deprecated("Deprecated in favour of proxyTags", ReplaceWith("proxyTags"))
    var prefix: String? = null,

    @Deprecated("Deprecated in favour of proxyTags", ReplaceWith("proxyTags"))
    var suffix: String? = null,

    @SerialName("proxy_tags")
    var proxyTags: MutableList<ProxyTag>,

    @SerialName("keep_proxy")
    var keepProxy: Boolean,

    val created: String,

    @Deprecated("Deprecated in favour of visibility and <subject>Privacy", ReplaceWith("visibility"))
    var privacy: String? = null,

    var visibility: Privacy?,

    @SerialName("name_privacy")
    var namePrivacy: Privacy?,

    @SerialName("description_privacy")
    var descriptionPrivacy: Privacy?,

    @SerialName("avatar_privacy")
    var avatarPrivacy: Privacy?,

    @SerialName("birthday_privacy")
    var birthdayPrivacy: Privacy?,

    @SerialName("pronoun_privacy")
    var pronounPrivacy: Privacy?,

    @SerialName("metadata_privacy")
    var metadataPrivacy: Privacy?
) {
    /**
     * Convert this data class to a map, without the fields that may not be modified.
     *
     * This is used when editing this object, to send the changes to PluralKit.
     */
    public fun toSendingMap(): Map<String, Any?> = mapOf(
        "name" to name,
        "display_name" to displayName,
        "description" to description,
        "pronouns" to pronouns,
        "color" to color,
        "avatar_url" to avatarUrl,
        "birthday" to birthday,
        "proxy_tags" to proxyTags.map { it.toSendingMap() },
        "keep_proxy" to keepProxy,
        "visibility" to visibility?.string,
        "name_privacy" to namePrivacy?.string,
        "description_privacy" to descriptionPrivacy?.string,
        "avatar_privacy" to avatarPrivacy?.string,
        "birthday_privacy" to birthdayPrivacy?.string,
        "pronoun_privacy" to pronounPrivacy?.string,
        "metadata_privacy" to metadataPrivacy?.string
    )
}
