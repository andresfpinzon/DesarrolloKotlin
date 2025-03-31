package config

import io.github.cdimascio.dotenv.dotenv
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import models.administrativo.brigada.BrigadaTable
import models.administrativo.user.model.UsuarioTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.SchemaUtils
import models.instructor.asistencia.AsistenciasTable

object DatabaseConfig {
    fun init() {
        val dotenv = dotenv() // Carga las variables del .env
        val config = HikariConfig().apply {
            jdbcUrl = dotenv["DB_URL"] ?: throw IllegalStateException("DB_URL no está configurado")
            username = dotenv["DB_USER"] ?: throw IllegalStateException("DB_USER no está configurado")
            password = dotenv["DB_PASSWORD"] ?: throw IllegalStateException("DB_PASSWORD no está configurado")
            maximumPoolSize = 10
            isAutoCommit = false
            transactionIsolation = "TRANSACTION_REPEATABLE_READ"
            validate()
        }

        Database.connect(HikariDataSource(config))

        transaction {
            SchemaUtils.createMissingTablesAndColumns(AsistenciasTable)
            SchemaUtils.createMissingTablesAndColumns(BrigadaTable)
            SchemaUtils.createMissingTablesAndColumns(UsuarioTable)
        }
    }
}
