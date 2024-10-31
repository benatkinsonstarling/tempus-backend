package com.tempus.controller

import io.micronaut.context.annotation.Replaces
import io.micronaut.http.HttpRequest
import io.micronaut.security.authentication.AuthenticationRequest
import io.micronaut.security.authentication.AuthenticationResponse
import io.micronaut.security.authentication.provider.AuthenticationProvider
import jakarta.inject.Singleton
import org.slf4j.LoggerFactory

@Singleton
@Replaces(AuthenticationProvider::class)
class ClerkAuthenticationProvider : AuthenticationProvider<HttpRequest<*>, String, Any> {
    private val logger = LoggerFactory.getLogger(ClerkAuthenticationProvider::class.java)

    override fun authenticate(
        requestContext: HttpRequest<*>?,
        authRequest: AuthenticationRequest<String, Any>?
    ): AuthenticationResponse {
        logger.debug("Hey ho")
        logger.debug("Attempting to authenticate: ${authRequest!!.identity}")
        return AuthenticationResponse.failure()
    }
}