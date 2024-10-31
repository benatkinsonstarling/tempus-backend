package com.tempus.security

import io.micronaut.http.HttpMethod
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.MutableHttpResponse
import io.micronaut.http.annotation.Filter
import io.micronaut.http.filter.HttpServerFilter
import io.micronaut.http.filter.ServerFilterChain
import io.micronaut.security.authentication.Authentication
import io.micronaut.security.filters.SecurityFilter
import org.reactivestreams.Publisher
import reactor.core.publisher.Flux
import jakarta.inject.Singleton
import org.slf4j.LoggerFactory
import kotlin.math.log

@Singleton
@Filter("/**")
class ClerkAuthenticationFilter(
    private val jwtValidator: ClerkJwtValidator
) : HttpServerFilter {
    private val logger = LoggerFactory.getLogger(ClerkAuthenticationFilter::class.java)

    override fun doFilter(
        request: HttpRequest<*>,
        chain: ServerFilterChain
    ): Publisher<MutableHttpResponse<*>> {
        logger.debug("Processing request: ${request.method} ${request.path}")

        // Handle OPTIONS requests with CORS headers
        if (request.method == HttpMethod.OPTIONS) {
            val response = HttpResponse.ok<Any>()
                .header("Access-Control-Allow-Methods", "POST, GET, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Authorization")
                .header("Access-Control-Allow-Origin", request.headers["Origin"] ?: "*")
                .header("Access-Control-Allow-Credentials", "true")
            return Flux.just(response)
        }

        val authHeader = request.headers["Authorization"]?.toString()

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            val token = authHeader.substring(7)
            val decodedJwt = jwtValidator.validateToken(token)

            if (decodedJwt != null) {
                // Add authentication to the request
                val auth = Authentication.build(decodedJwt.subject)
                request.setAttribute("micronaut.AUTHENTICATION", auth)
                return chain.proceed(request)
            }
        }

        // For unauthenticated API requests, return 401
        return if (request.path.startsWith("/api/")) {
            Flux.just(HttpResponse.unauthorized<Any>())  // Changed from ok() to unauthorized()
        } else {
            chain.proceed(request)
        }
    }
}