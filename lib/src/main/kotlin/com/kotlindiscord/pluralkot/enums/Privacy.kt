package com.kotlindiscord.pluralkot.enums

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
/**
 * Enum representing PluralKit privacy values.
 *
 * @param string Human-readable lower-cased value.
 */
public enum class Privacy(public val string: String) {
    /** Private - this information won't be shown to the public. **/
    @SerialName("private")
    PRIVATE("private"),

    /** Public - this information will be shown to the public. **/
    @SerialName("public")
    PUBLIC("public")
}
