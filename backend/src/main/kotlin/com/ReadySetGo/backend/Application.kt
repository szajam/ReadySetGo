package com.ReadySetGo.backend

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.github.cdimascio.dotenv.dotenv
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {

    // ── Load .env from root ReadySetGo/ folder ──────────────────
    val dotenv = dotenv {
        directory = "../"
        ignoreIfMissing = false
    }

    // ── Plugins ──────────────────────────────────────────────────
    install(ContentNegotiation) {
        json()
    }

    // ── Database ─────────────────────────────────────────────────
    val dataSource = HikariDataSource(HikariConfig().apply {
        jdbcUrl         = "jdbc:postgresql://${dotenv["DB_HOST"]}:${dotenv["DB_PORT"]}/${dotenv["DB_NAME"]}"
        username        = dotenv["DB_USER"]
        password        = dotenv["DB_PASSWORD"]
        driverClassName = "org.postgresql.Driver"
        maximumPoolSize = 5
    })

    log.info("Database pool initialized → ${dataSource.jdbcUrl}")

    // ── Routes ───────────────────────────────────────────────────
    routing {
        get("/health") {
            val dbOk = try {
                dataSource.connection.use { it.isValid(2) }
            } catch (e: Exception) {
                false
            }
            call.respond(
                mapOf(
                    "status"   to "ok",
                    "database" to if (dbOk) "connected" else "unreachable"
                )
            )
        }
    }
}