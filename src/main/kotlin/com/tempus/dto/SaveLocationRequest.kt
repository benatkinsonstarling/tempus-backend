package com.tempus.dto

import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable

@Serdeable.Deserializable
@Introspected
data class SaveLocationRequest(
    val name: String,
    val longitude: Double,
    val latitude: Double,
    val isFavourite: Boolean
)