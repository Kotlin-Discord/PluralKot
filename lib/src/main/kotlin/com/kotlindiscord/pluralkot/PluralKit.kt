package com.kotlindiscord.pluralkot

import com.kotlindiscord.pluralkot.exceptions.HttpStatusException
import com.kotlindiscord.pluralkot.exceptions.TokenMissingException
import com.kotlindiscord.pluralkot.exceptions.UnauthorizedException
import com.kotlindiscord.pluralkot.models.*
import khttp.async
import khttp.responses.Response
import kotlinx.coroutines.CompletableDeferred
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlin.collections.set

private const val AUTH_HEADER: String = "Authorization"
private const val HTTP_OK: Int = 200
private const val HTTP_NOT_FOUND: Int = 404
private const val HTTP_UNAUTHORIZED: Int = 401

/**
 * Wrapping PluralKit API class.
 *
 * @param systemToken PluralKit system token, for authorization - `pk;token` to get one
 */
public class PluralKit(private val systemToken: String? = null) {
    private suspend fun httpDelete(
        url: String,
        params: Map<String, String> = mapOf(),
        throwByStatus: Boolean = true
    ): Response {
        val headers: MutableMap<String, String> = mutableMapOf()

        if (systemToken != null) {
            headers[AUTH_HEADER] = systemToken
        }

        val deferred: CompletableDeferred<Response> = CompletableDeferred()

        async.delete(
            url = url,
            params = params,
            headers = headers,
            onError = deferred::completeExceptionally,
            onResponse = deferred::complete
        )

        val response = deferred.await()

        if (throwByStatus && response.statusCode != HTTP_OK) {
            throw HttpStatusException(response)
        }

        return response
    }

    private suspend fun httpGet(
        url: String,
        params: Map<String, String> = mapOf(),
        throwByStatus: Boolean = true
    ): Response {
        val headers: MutableMap<String, String> = mutableMapOf()

        if (systemToken != null) {
            headers[AUTH_HEADER] = systemToken
        }

        val deferred: CompletableDeferred<Response> = CompletableDeferred()

        async.get(
            url = url,
            params = params,
            headers = headers,
            onError = deferred::completeExceptionally,
            onResponse = deferred::complete
        )

        val response = deferred.await()

        if (throwByStatus && response.statusCode != HTTP_OK) {
            throw HttpStatusException(response)
        }

        return response
    }

    private suspend fun httpPatch(
        url: String,
        data: Map<String, Any?> = mapOf(),
        throwByStatus: Boolean = true
    ): Response {
        val headers: MutableMap<String, String> = mutableMapOf()

        if (systemToken != null) {
            headers[AUTH_HEADER] = systemToken
        }

        val deferred: CompletableDeferred<Response> = CompletableDeferred()

        async.patch(
            url = url,
            json = data,
            headers = headers,
            onError = deferred::completeExceptionally,
            onResponse = deferred::complete
        )

        val response = deferred.await()

        if (throwByStatus && response.statusCode != HTTP_OK) {
            throw HttpStatusException(response)
        }

        return response
    }

    private suspend fun httpPost(
        url: String,
        data: Map<String, Any?> = mapOf(),
        throwByStatus: Boolean = true
    ): Response {
        val headers: MutableMap<String, String> = mutableMapOf()

        if (systemToken != null) {
            headers[AUTH_HEADER] = systemToken
        }

        val deferred: CompletableDeferred<Response> = CompletableDeferred()

        async.post(
            url = url,
            json = data,
            headers = headers,
            onError = deferred::completeExceptionally,
            onResponse = deferred::complete
        )

        val response = deferred.await()

        if (throwByStatus && response.statusCode != HTTP_OK) {
            throw HttpStatusException(response)
        }

        return response
    }

    /**
     * If you've provided a [systemToken], retrieve the [System] corresponding with the token.
     */
    public suspend fun system(): System {
        systemToken ?: throw TokenMissingException()

        val response = httpGet(URLs.SYSTEM, throwByStatus = false)

        if (response.statusCode == HTTP_UNAUTHORIZED) {
            throw UnauthorizedException()
        }

        return Json.decodeFromString(response.text)
    }

    /**
     * If you've provided a [systemToken], edit the corresponding [System] by updating it to match the given
     * [System] object.
     *
     * @param system System object to use to update the system. All properties are used, aside from `created` and` id`.
     */
    public suspend fun editSystem(system: System): System {
        systemToken ?: throw TokenMissingException()

        val response = httpPatch(URLs.SYSTEM, data = system.toSendingMap())

        return Json.decodeFromString(response.text)
    }

    /**
     * Retrieve the [System] corresponding with the given system ID. Returns `null` if no such System exists.
     *
     * @param systemId System ID to retrieve the [System] object for.
     */
    public suspend fun system(systemId: String): System? {
        val response = httpGet(URLs.SYSTEM_ID.replace(":id", systemId), throwByStatus = false)

        if (response.statusCode == HTTP_NOT_FOUND) {
            return null
        }

        return Json.decodeFromString(response.text)
    }

    /**
     * Retrieve the [System] corresponding with the given Discord user ID. Returns `null` if the user has no System.
     *
     * @param discordId Discord user ID to retrieve the [System] object for.
     */
    public suspend fun systemByDiscordUser(discordId: String): System? {
        val response = httpGet(URLs.ACCOUNT.replace(":id", discordId), throwByStatus = false)

        if (response.statusCode == HTTP_NOT_FOUND) {
            return null
        }

        return Json.decodeFromString(response.text)
    }

    /**
     * Retrieve the [Member]s for the System corresponding with the given system ID. Returns `null` if no such
     * System exists.
     *
     * @param systemId System ID to retrieve the [Member]s for.
     */
    public suspend fun members(systemId: String): List<Member>? {
        val response = httpGet(URLs.SYSTEM_ID_MEMBERS.replace(":id", systemId), throwByStatus = false)

        if (response.statusCode == HTTP_NOT_FOUND) {
            return null
        }

        return Json.decodeFromString(response.text)
    }

    /**
     * Retrieve the Switches for the System corresponding with the given system ID. Returns `null` if no such
     * System exists.
     *
     * Switches are returned as a list of [SwitchWithIDs] objects.
     *
     * @param systemId System ID to retrieve the Switches for.
     */
    public suspend fun switches(systemId: String, before: String? = null): List<SwitchWithIDs>? {
        val params: MutableMap<String, String> = mutableMapOf()

        if (before != null) {
            params["before"] = before
        }

        val response = httpGet(
            URLs.SYSTEM_ID_SWITCHES.replace(":id", systemId),
            params = params,
            throwByStatus = false
        )

        if (response.statusCode == HTTP_NOT_FOUND) {
            return null
        }

        return Json.decodeFromString(response.text)
    }

    /**
     * If you've provided a [systemToken], register a Switch.
     *
     * @param members List of Member IDs that this switch includes.
     */
    public suspend fun createSwitch(members: List<String>) {
        systemToken ?: throw TokenMissingException()

        httpPost(URLs.SYSTEM_SWITCHES, data = mapOf("members" to members))
    }

    /**
     * Retrieve the Fronters for the System corresponding with the given system ID. Returns `null` if no such
     * System exists.
     *
     * Fronters are returned as a list of [SwitchWithMembers] objects.
     *
     * @param systemId System ID to retrieve the Fronters for.
     */
    public suspend fun fronters(systemId: String): List<SwitchWithMembers>? {
        val response = httpGet(URLs.SYSTEM_ID_FRONTERS.replace(":id", systemId), throwByStatus = false)

        if (response.statusCode == HTTP_NOT_FOUND) {
            return null
        }

        return Json.decodeFromString(response.text)
    }

    /**
     * Retrieve the [Member] corresponding to the given Member IT. Returns `null` if no such Member exists.
     *
     * @param memberId Member ID to return the [Member] object for.
     */
    public suspend fun member(memberId: String): Member? {
        val response = httpGet(URLs.MEMBER_ID.replace(":id", memberId), throwByStatus = false)

        if (response.statusCode == HTTP_NOT_FOUND) {
            return null
        }

        return Json.decodeFromString(response.text)
    }

    /**
     * Create a new [Member] based on a given [Member] object. This is of limited usefulness, you probably want to use
     * the other `createMember` function.
     *
     * @param member Member object to create.
     */
    public suspend fun createMember(member: Member): Member {
        systemToken ?: throw TokenMissingException()

        val response = httpPost(URLs.MEMBER, data = member.toSendingMap())

        return Json.decodeFromString(response.text)
    }

    /**
     * Create a new [Member] based with the given name.
     *
     * @param name Name for the new Member.
     */
    public suspend fun createMember(name: String): Member {
        systemToken ?: throw TokenMissingException()

        val response = httpPost(URLs.MEMBER, data = mapOf("name" to name))

        return Json.decodeFromString(response.text)
    }

    /**
     * If you've provided a [systemToken], edit the given [Member] by updating it to match the given
     * [Member] object.
     *
     * @param member Member object to use to update the Member. All properties are used, aside from `created` and` id`.
     */
    public suspend fun editMember(member: Member): Member {
        systemToken ?: throw TokenMissingException()

        val response = httpPatch(
            URLs.MEMBER_ID.replace(":id", member.id),
            data = member.toSendingMap()
        )

        return Json.decodeFromString(response.text)
    }

    /**
     * If you've provided a [systemToken], delete the [Member] corresponding with the given Member ID.
     *
     * @param memberId Member ID representing the member to be deleted.
     */
    public suspend fun deleteMember(memberId: String) {
        systemToken ?: throw TokenMissingException()

        httpDelete(URLs.MEMBER_ID.replace(":id", memberId))
    }

    /**
     * Retrieve proxying information about a Discord message, by ID.
     *
     * PluralKit supports lookups using the ID for the original (deleted) message, or for the proxied message. This
     * function returns `null` if the message wasn't handled by PluralKit, or the proxied message was deleted.
     *
     * @param messageId Discord Message ID to look up.
     */
    public suspend fun message(messageId: String): Message? {
        val response = httpGet(URLs.MESSAGE.replace(":id", messageId), throwByStatus = false)

        if (response.statusCode == HTTP_NOT_FOUND) {
            return null
        }

        return Json.decodeFromString(response.text)
    }
}

/** @suppress Temporary test function **/
public suspend fun main() {
    val pk = PluralKit(java.lang.System.getenv("SYSTEM_TOKEN"))

    println(pk.system())
    println("")

    val newMember = pk.createMember("test")

    println(newMember)
    println("")

    newMember.pronouns = "he/him"

    pk.editMember(newMember)

    val receivedMember = pk.member(newMember.id)

    println(receivedMember)
    println("")

    pk.deleteMember(newMember.id)

    println(pk.member(newMember.id))
}
