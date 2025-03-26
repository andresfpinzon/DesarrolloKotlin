package repositories.instructor.asistencia

import models.instructor.asistencia.AsistenciasTable
import models.instructor.asistencia.Asistencia
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class AsistenciaRepository {

    fun crearAsistencia(asistencia: Asistencia) {
        transaction {
            AsistenciasTable.insert {
                it[id] = asistencia.getId()
                it[tituloAsistencia] = asistencia.getTituloAsistencia()
                it[fechaAsistencia] = asistencia.getFechaAsistencia().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate()
                it[usuarioId] = asistencia.getUsuarioId()
                it[estadoAsistencia] = asistencia.getEstadoAsistencia()
                it[estudiantes] = asistencia.getEstudiantes().joinToString(",") // Guardar lista como CSV
            }
        }
    }

    fun listarAsistenciasActivas(): List<Asistencia> {
        return transaction {
            AsistenciasTable.select { AsistenciasTable.estadoAsistencia eq true }
                .map { rowToAsistencia(it) }
        }
    }

    fun obtenerAsistenciaPorId(id: String): Asistencia? {
        return transaction {
            AsistenciasTable.select { AsistenciasTable.id eq id }
                .mapNotNull { rowToAsistencia(it) }
                .singleOrNull()
        }
    }

    fun actualizarAsistencia(asistencia: Asistencia) {
        transaction {
            AsistenciasTable.update({ AsistenciasTable.id eq asistencia.getId() }) {
                it[tituloAsistencia] = asistencia.getTituloAsistencia()
                it[fechaAsistencia] = asistencia.getFechaAsistencia().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate()
                it[usuarioId] = asistencia.getUsuarioId()
                it[estadoAsistencia] = asistencia.getEstadoAsistencia()
                it[estudiantes] = asistencia.getEstudiantes().joinToString(",")
            }
        }
    }

    fun desactivarAsistencia(id: String) {
        transaction {
            AsistenciasTable.update({ AsistenciasTable.id eq id }) {
                it[estadoAsistencia] = false
            }
        }
    }

    private fun rowToAsistencia(row: ResultRow): Asistencia {
        return Asistencia(
            id = row[AsistenciasTable.id],
            tituloAsistencia = row[AsistenciasTable.tituloAsistencia],
            fechaAsistencia = Date.from(row[AsistenciasTable.fechaAsistencia].atStartOfDay(java.time.ZoneId.systemDefault()).toInstant()),
            usuarioId = row[AsistenciasTable.usuarioId],
            estadoAsistencia = row[AsistenciasTable.estadoAsistencia],
            estudiantes = row[AsistenciasTable.estudiantes].split(",") // Convertir CSV a lista
        )
    }
}
