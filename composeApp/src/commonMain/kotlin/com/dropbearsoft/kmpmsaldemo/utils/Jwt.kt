package com.dropbearsoft.kmpmsaldemo.utils

import io.ktor.util.decodeBase64String
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject

fun decodeJwt(jwt: String): JsonObject? {
    val parts = jwt.split(".")
    if (parts.size != 3) {
        println("Invalid JWT format")
        return null
    }

    val payload = parts[1]

    // Decode the Base64 payload
    val decodedPayload = try {
        payload.decodeBase64String()
    } catch (e: Exception) {
        println("Error decoding Base64: ${e.message}")
        return null
    }

    // Parse the JSON payload
    return try {
        Json.parseToJsonElement(decodedPayload).jsonObject
    } catch (e: Exception) {
        println("Error parsing JSON: ${e.message}")
        null
    }
}