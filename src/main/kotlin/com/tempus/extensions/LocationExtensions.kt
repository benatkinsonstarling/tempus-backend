package com.tempus.extensions

import com.tempus.dto.SaveLocationRequest
import com.tempus.dto.SavedLocationResponse
import com.tempus.persistence.entity.SavedLocation

fun SaveLocationRequest.toDomain(userId: String) = SavedLocation(
    userId = userId,
    name = name,
    latitude = latitude,
    longitude = longitude,
    isFavorite = isFavourite
)

fun SavedLocation.toDto() = SavedLocationResponse(
    id = id!!,
    name = name,
    latitude = latitude,
    longitude = longitude,
    isFavorite = isFavorite,
    createdAt = createdAt
)