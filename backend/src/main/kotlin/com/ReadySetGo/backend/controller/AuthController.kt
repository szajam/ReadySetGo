package com.ReadySetGo.backend.controller

import com.ReadySetGo.backend.model.ErrorResponse
import com.ReadySetGo.backend.model.LoginRequest
import com.ReadySetGo.backend.model.RegisterRequest
import com.ReadySetGo.backend.service.UserService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.authRoutes(userService: UserService) {

    route("/auth") {

        post("/register") {
            val request = call.receive<RegisterRequest>()
            try {
                val response = userService.register(request.email, request.password)
                call.respond(HttpStatusCode.Created, response)
            } catch (e: IllegalArgumentException) {
                call.respond(HttpStatusCode.Conflict, ErrorResponse(e.message ?: "Registration failed"))
            } catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError, ErrorResponse("Something went wrong"))
            }
        }

        post("/login") {
            val request = call.receive<LoginRequest>()
            try {
                val response = userService.login(request.email, request.password)
                call.respond(HttpStatusCode.OK, response)
            } catch (e: IllegalArgumentException) {
                call.respond(HttpStatusCode.Unauthorized, ErrorResponse(e.message ?: "Login failed"))
            } catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError, ErrorResponse("Something went wrong"))
            }
        }
    }
}