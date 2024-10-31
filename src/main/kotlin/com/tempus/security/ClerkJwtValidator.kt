package com.tempus.security

import com.auth0.jwk.JwkProvider
import com.auth0.jwk.UrlJwkProvider
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.DecodedJWT
import jakarta.inject.Singleton
import java.net.URL
import java.security.interfaces.RSAPublicKey

@Singleton
class ClerkJwtValidator {
    private val jwkProvider: JwkProvider = UrlJwkProvider(
        URL("https://smashing-salmon-31.clerk.accounts.dev/.well-known/jwks.json")
    )

    fun validateToken(token: String): DecodedJWT? {
        return try {
            val jwt = JWT.decode(token)
            val jwk = jwkProvider.get(jwt.keyId)
            val algorithm = Algorithm.RSA256(jwk.publicKey as RSAPublicKey, null)

            JWT.require(algorithm)
                .withIssuer("https://smashing-salmon-31.clerk.accounts.dev")
                .build()
                .verify(token)
        } catch (e: Exception) {
            null
        }
    }
}