package com.tempus.locations

import com.tempus.dto.SaveLocationRequest
import com.tempus.dto.SavedLocationResponse
import com.tempus.extensions.toDomain
import com.tempus.extensions.toDto
import com.tempus.persistence.entity.SavedLocation
import com.tempus.persistence.repository.SavedLocationRepository
import jakarta.inject.Singleton

@Singleton
open class SavedLocationService (
    private val locationRepository: SavedLocationRepository
) {

    open fun saveLocation(userId: String, saveLocationRequest: SaveLocationRequest): SavedLocationResponse {
        if (locationRepository.existsByUserIdAndLatitudeAndLongitude(userId, saveLocationRequest.latitude, saveLocationRequest.longitude)) {
            // location already saved, do nothing.
        }

        return locationRepository.save(
            saveLocationRequest.toDomain(userId)
        ).toDto()
    }

    fun getUserLocations(userId: String): List<SavedLocationResponse> =
        locationRepository.findByUserId(userId)
            .map(SavedLocation::toDto)

    fun deleteSavedLocation(id: Long) {
        locationRepository.deleteById(id)
    }

    fun toggleFavourite(
        id: Long,
        userId: String) : SavedLocationResponse{
        val location = locationRepository.findById(id)
            .orElseThrow()

        if (location.userId != userId) {
            throw RuntimeException(String.format("You nasty piece of work!"))
        }

        locationRepository.updateIsFavoriteByIdAndUserId(id, userId, !location.isFavorite)

        return locationRepository.findById(id).get().toDto()
    }
}