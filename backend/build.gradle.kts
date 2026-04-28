plugins {
    kotlin("jvm")                  version "1.9.22"
    kotlin("plugin.serialization") version "1.9.22"
    application
    id("com.gradleup.shadow")      version "8.3.6"
}

group   = "com.ReadySetGo"
version = "1.0.0"

application {
    mainClass.set("io.ktor.server.netty.EngineMain")
}

tasks.named<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar>("shadowJar") {
    archiveFileName.set("backend.jar")
    mergeServiceFiles()
    manifest {
        attributes["Main-Class"] = "io.ktor.server.netty.EngineMain"
    }
}

tasks.register<JavaExec>("seed") {
    group = "application"
    description = "Seeds the database with test data"
    classpath = sourceSets["main"].runtimeClasspath
    mainClass.set("com.ReadySetGo.backend.SeederKt")
}

dependencies {
    implementation("io.ktor:ktor-server-core:2.3.12")
    implementation("io.ktor:ktor-server-netty:2.3.12")
    implementation("io.ktor:ktor-server-content-negotiation:2.3.12")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.12")
    implementation("io.ktor:ktor-server-cors:2.3.12")
    implementation("io.ktor:ktor-server-status-pages:2.3.12")
    implementation("io.ktor:ktor-server-call-logging:2.3.12")
    implementation("com.zaxxer:HikariCP:5.1.0")
    implementation("org.postgresql:postgresql:42.7.1")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.2")
    implementation("ch.qos.logback:logback-classic:1.4.14")
    implementation("io.github.cdimascio:dotenv-kotlin:6.4.1")

    // JWT
    implementation("com.auth0:java-jwt:4.4.0")

    // BCrypt
    implementation("org.mindrot:jbcrypt:0.4")

    // Ktor auth
    implementation("io.ktor:ktor-server-auth:2.3.12")
    implementation("io.ktor:ktor-server-auth-jwt:2.3.12")
}