package com.ReadySetGo.backend

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.github.cdimascio.dotenv.dotenv
import org.mindrot.jbcrypt.BCrypt

// Data
private val SEED_USERS = listOf(
    Triple("user1@mail.com",  "user1",  "pass123"),
    Triple("user2@mail.com",  "user2",  "pass123"),
    Triple("user3@mail.com",  "user3",  "pass123"),
    Triple("user4@mail.com",  "user4",  "pass123"),
    Triple("user5@mail.com",  "user5",  "pass123"),
    Triple("admin@mail.com",  "admin",  "admin123")
)

fun main() {
    val dotenv = dotenv {
        directory = "../"
        ignoreIfMissing = false
    }

    val dataSource = HikariDataSource(HikariConfig().apply {
        jdbcUrl         = "jdbc:postgresql://${dotenv["DB_HOST"]}:${dotenv["DB_PORT"]}/${dotenv["DB_NAME"]}"
        username        = dotenv["DB_USER"]
        password        = dotenv["DB_PASSWORD"]
        driverClassName = "org.postgresql.Driver"
        maximumPoolSize = 2
    })

    dataSource.connection.use { conn ->
        conn.prepareStatement("TRUNCATE TABLE users RESTART IDENTITY CASCADE").execute()
        println("Cleared users table\n")

        val sql = "INSERT INTO users (email, password) VALUES (?, ?)"
        conn.prepareStatement(sql).use { stmt ->
            SEED_USERS.forEach { (email, username, password) ->
                val hash = BCrypt.hashpw(password, BCrypt.gensalt(12))
                stmt.setString(1, email)
                stmt.setString(2, hash)
                stmt.executeUpdate()
                println("Seeded: $email (password: $password)")
            }
        }
    }

    println("\nDone! Seeded ${SEED_USERS.size} users.")
    dataSource.close()
}