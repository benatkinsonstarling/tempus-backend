package com.tempus.persistence.entity

import io.micronaut.data.annotation.DateCreated
import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import jakarta.annotation.Nullable
import jakarta.validation.constraints.DecimalMax
import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.NotNull
import java.time.Instant

@MappedEntity("saved_locations")
data class SavedLocation(
    @Id
    @GeneratedValue(GeneratedValue.Type.IDENTITY)
    val id: Long? = null,

    @field:NotNull
    val userId: String,

    @field:Nullable
    val name: String,

    @field:NotNull
    @field:DecimalMin("-90.0")
    @field:DecimalMax("90.0")
    val latitude: Double,

    @field:NotNull
    @field:DecimalMin("-180.0")
    @field:DecimalMax("180.0")
    val longitude: Double,

    val isFavorite: Boolean = false,

    @DateCreated
    val createdAt: Instant? = null
)