package br.com.adautomoises.gerenciador_perfil.Service

import br.com.adautomoises.gerenciador_perfil.model.Entity.AuthUser
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTCreationException
import com.auth0.jwt.exceptions.JWTVerificationException
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset

@Service
class TokenService(
    @Value("\${jwt.secret}")
    private val secret: String,
    @Value("\${jwt.expiration.hours}")
    private val expirationHours: Long
) {
    fun generateToken(authUser: AuthUser): String {
        try {
            val algorithm = Algorithm.HMAC256(secret)
            return JWT.create()
                .withIssuer("gerenciador-perfil-api")
                .withSubject(authUser.username)
                .withExpiresAt(genExpirationDate())
                .sign(algorithm)
        } catch (exception: JWTCreationException) {
            throw RuntimeException("Error generating JWT token", exception)
        }
    }

    fun validateToken(token: String): String? {
        try {
            val algorithm = Algorithm.HMAC256(secret)
            val verifier = JWT.require(algorithm)
                .withIssuer("gerenciador-perfil-api")
                .build()

            val decodedJWT = verifier.verify(token)
            return decodedJWT.subject
        } catch (exception: JWTVerificationException) {
            return null
        }
    }

    private fun genExpirationDate(): Instant {
        return LocalDateTime.now().plusHours(expirationHours).toInstant(ZoneOffset.of("-03:00"))
    }
}