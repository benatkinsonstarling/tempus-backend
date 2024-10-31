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


@Controller("/api/locations")
@Secured(SecurityRule.IS_AUTHENTICATED)  // Add this if not already present
open class LocationController(
    private val locationService: SavedLocationService
) {

    @Get
    open fun getUsersSavedLocations(authentication: Authentication):
            HttpResponse<List<SavedLocationResponse>> {
        return HttpResponse.ok(locationService.getUserLocations(authentication.name))
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
}