package com.ReadySetGo.backend.service

import com.ReadySetGo.backend.model.AuthResponse
import com.ReadySetGo.backend.repository.UserRepository
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import org.mindrot.jbcrypt.BCrypt
import java.util.Date

class UserService(
    private val userRepository: UserRepository,
    private val jwtSecret: String,
    private val jwtIssuer: String,
    private val jwtAudience: String,
    private val jwtExpirationMs: Long
) {

    fun register(email: String, password: String): AuthResponse {
        if (userRepository.findByEmail(email) != null) {
            throw IllegalArgumentException("Email already in use")
        }
        val passwordHash = BCrypt.hashpw(password, BCrypt.gensalt())
        val user = userRepository.create(email, passwordHash)
        val token = generateToken(user.email)
        return AuthResponse(token = token, email = user.email)
    }

    fun login(email: String, password: String): AuthResponse {
        val user = userRepository.findByEmail(email)
            ?: throw IllegalArgumentException("Invalid email or password")
        if (!BCrypt.checkpw(password, user.passwordHash)) {
            throw IllegalArgumentException("Invalid email or password")
        }
        val token = generateToken(user.email)
        return AuthResponse(token = token, email = user.email)
    }

    private fun generateToken(email: String): String {
        return JWT.create()
            .withIssuer(jwtIssuer)
            .withAudience(jwtAudience)
            .withClaim("email", email)
            .withExpiresAt(Date(System.currentTimeMillis() + jwtExpirationMs))
            .sign(Algorithm.HMAC256(jwtSecret))
    }
}