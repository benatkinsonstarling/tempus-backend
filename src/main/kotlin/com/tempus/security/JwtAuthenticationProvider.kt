package com.tempus.security

import io.micronaut.http.HttpRequest
import io.micronaut.security.authentication.AuthenticationRequest
import io.micronaut.security.authentication.AuthenticationResponse
import io.micronaut.security.authentication.provider.AuthenticationProvider
import jakarta.inject.Singleton
import org.slf4j.LoggerFactory

@Singleton
class JwtAuthenticationProvider : AuthenticationProvider<HttpRequest<*>, String, Any> {
    private val logger = LoggerFactory.getLogger(JwtAuthenticationProvider::class.java)

    override fun authenticate(
        httpRequest: HttpRequest<*>?,
        authenticationRequest: AuthenticationRequest<String, Any>?
    ): AuthenticationResponse {
        logger.debug("Authenticating request")

        return AuthenticationResponse.failure("Invalid JWT")
    }
}