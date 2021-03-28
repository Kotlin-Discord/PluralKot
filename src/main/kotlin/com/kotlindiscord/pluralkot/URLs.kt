package com.kotlindiscord.pluralkot

/** @suppress Class for storing API URL constants. **/
public object URLs {
    public const val VERSION: String = "v1"
    public const val BASE_URL: String = "https://api.pluralkit.me/$VERSION"

    public const val ACCOUNT: String = "$BASE_URL/a/:id"

    public const val MEMBER: String = "$BASE_URL/m"
    public const val MEMBER_ID: String = "$MEMBER/:id"

    public const val MESSAGE: String = "$BASE_URL/msg/:id"

    public const val SYSTEM: String = "$BASE_URL/s"

    public const val SYSTEM_ID: String = "$SYSTEM/:id"
    public const val SYSTEM_ID_FRONTERS: String = "$SYSTEM_ID/fronters"
    public const val SYSTEM_ID_MEMBERS: String = "$SYSTEM_ID/members"
    public const val SYSTEM_ID_SWITCHES: String = "$SYSTEM_ID/switches"

    public const val SYSTEM_SWITCHES: String = "$SYSTEM/switches"
}
