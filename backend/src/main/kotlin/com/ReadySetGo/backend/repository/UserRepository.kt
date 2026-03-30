package com.ReadySetGo.backend.repository

import com.ReadySetGo.backend.model.User
import com.zaxxer.hikari.HikariDataSource

class UserRepository(private val dataSource: HikariDataSource) {

    fun findByEmail(email: String): User? {
        val sql = "SELECT id, email, password, created_at, updated_at FROM users WHERE email = ?"
        return dataSource.connection.use { conn ->
            conn.prepareStatement(sql).use { stmt ->
                stmt.setString(1, email)
                val rs = stmt.executeQuery()
                if (rs.next()) {
                    User(
                        id           = rs.getInt("id"),
                        email        = rs.getString("email"),
                        passwordHash = rs.getString("password"),
                        createdAt    = rs.getTimestamp("created_at").toString(),
                        updatedAt    = rs.getTimestamp("updated_at").toString()
                    )
                } else null
            }
        }
    }

    fun create(email: String, passwordHash: String): User {
        val sql = """
            INSERT INTO users (email, password)
            VALUES (?, ?)
            RETURNING id, email, password, created_at, updated_at
        """.trimIndent()
        return dataSource.connection.use { conn ->
            conn.prepareStatement(sql).use { stmt ->
                stmt.setString(1, email)
                stmt.setString(2, passwordHash)
                val rs = stmt.executeQuery()
                rs.next()
                User(
                    id           = rs.getInt("id"),
                    email        = rs.getString("email"),
                    passwordHash = rs.getString("password"),
                    createdAt    = rs.getTimestamp("created_at").toString(),
                    updatedAt    = rs.getTimestamp("updated_at").toString()
                )
            }
        }
    }
}