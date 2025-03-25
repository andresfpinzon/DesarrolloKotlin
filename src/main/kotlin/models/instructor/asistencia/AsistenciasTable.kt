package models.instructor.asistencia

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.date

object AsistenciasTable : Table("asistencias") {
    val id = varchar("id", 50).uniqueIndex()
    val tituloAsistencia = varchar("titulo", 255)
    val fechaAsistencia = date("fecha")
    val usuarioId = varchar("usuario_id", 50)
    val estadoAsistencia = bool("estado").default(true)
    val estudiantes = text("estudiantes") // Se guardará como JSON o CSV

    override val primaryKey = PrimaryKey(id)
}

