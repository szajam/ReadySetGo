package com.ReadySetGo.backend.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Int,
    val email: String,
    val passwordHash: String,
    val createdAt: String,
    val updatedAt: String
)