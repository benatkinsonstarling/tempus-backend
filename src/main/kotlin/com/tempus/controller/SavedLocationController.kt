package com.tempus.controller

import com.tempus.dto.SaveLocationRequest
import com.tempus.dto.SavedLocationResponse
import com.tempus.locations.SavedLocationService
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
import io.micronaut.security.annotation.Secured
import io.micronaut.security.authentication.Authentication
import io.micronaut.security.rules.SecurityRule
import jakarta.validation.Valid
import org.slf4j.LoggerFactory
import kotlin.math.log


@Controller("/api/locations")
@Secured(SecurityRule.IS_AUTHENTICATED)  // Add this if not already present
open class LocationController(
    private val locationService: SavedLocationService
) {
    private val logger = LoggerFactory.getLogger(ClerkAuthenticationProvider::class.java)

    @Get
    open fun getUsersSavedLocations(authentication: Authentication):
            HttpResponse<List<SavedLocationResponse>> {
        return HttpResponse.ok(locationService.getUserLocations(authentication.name))
    }

    @Delete("{savedLocationId}")
    open fun deleteSavedLocation(authentication: Authentication,
                                 @QueryValue savedLocationId: Long):
            HttpResponse<Any> {
        locationService.deleteSavedLocation(savedLocationId);
        return HttpResponse.ok()
    }

    @Post("/save")
    open fun saveLocation(
        authentication: Authentication,
        @Body @Valid request: SaveLocationRequest
    ): HttpResponse<SavedLocationResponse> {
        println("Received request: $request") // Add this for debugging

        return HttpResponse.created(
            locationService.saveLocation(authentication.name, request)
        )
    }

    @Post("/{id}/favorite")
    open fun toggleFavorite(
        authentication: Authentication,
        @QueryValue id: Long
    ): HttpResponse<SavedLocationResponse> {
        logger.debug("recjsncdanl")
        return HttpResponse.ok(locationService.toggleFavourite(id, authentication.name))
    }
}