package com.tempus.dto

import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable
import java.time.Instant

@Introspected
@Serdeable.Serializable
data class SavedLocationResponse(
    val id: Long,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val isFavorite: Boolean,
    val createdAt: Instant?
)