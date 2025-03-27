plugins {
    kotlin("jvm") version "2.1.10"
    application
}

group = "com.sena"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

        dependencies {
    // PostgreSQL
    implementation("org.postgresql:postgresql:42.7.5")

    //dot env
    implementation("io.github.cdimascio:dotenv-kotlin:6.4.1")

    // Exposed - Core
    implementation("org.jetbrains.exposed:exposed-core:0.43.0")

    // Exposed - DAO (para facilitar la manipulación de entidades)
    implementation("org.jetbrains.exposed:exposed-dao:0.43.0")

    // Exposed - JDBC (para soporte de PostgreSQL)
    implementation("org.jetbrains.exposed:exposed-jdbc:0.43.0")

    // Exposed - Kotlin DateTime (para manejar fechas)
    implementation("org.jetbrains.exposed:exposed-java-time:0.43.0")

    // Testing
    testImplementation(kotlin("test"))

    // HikariCP (Manejo de conexiones a la base de datos)
    implementation("com.zaxxer:HikariCP:5.1.0")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(23)
}