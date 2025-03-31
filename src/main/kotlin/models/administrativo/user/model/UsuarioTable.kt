package models.administrativo.user.model

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.javatime.date
import java.time.LocalDate

object UsuarioTable : Table("users") {
    val id = long("id_user").autoIncrement()
    val nombreUsuario = varchar("nombre_user", 255)
    val apellidoUsuario = varchar("apellido_user", 255)
    val numeroDocumento = varchar("numero_documento", 255)
    val correo = varchar("correo", 255).uniqueIndex()
    val password = varchar("password", 255)
    val roles =text("roles") // Corrección aquí
    val estadoUsuario = bool("estado_usuario").default(true)
    val creadoEn = date("creado_en").default(LocalDate.now())

    override val primaryKey = PrimaryKey(id)
}