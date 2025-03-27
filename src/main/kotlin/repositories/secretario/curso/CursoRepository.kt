package repositories.secretario.curso

import models.secretario.curso.CursosTable
import models.secretario.curso.Curso
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class CursoRepository {

    fun guardarCurso(curso: Curso) {
        transaction {
            CursosTable.insert {
                it[id] = curso.getId()
                it[nombreCurso] = curso.getNombreCurso()
                it[descripcionCurso] = curso.getDescripcionCurso()
                it[intensidadHorariaCurso] = curso.getIntensidadHorariaCurso()
                it[estadoCurso] = curso.getEstadoCurso()
                it[fundacionId] = curso.getFundacionId()
                it[ediciones] = curso.getEdiciones().joinToString(",") // Guardar lista como CSV
            }
        }
    }

    fun listarCursosActivos(): List<Curso> {
        return transaction {
            CursosTable.select { CursosTable.estadoCurso eq true }
                .map { rowToCurso(it) }
        }
    }

    fun obtenerCursoPorId(id: String): Curso? {
        return transaction {
            CursosTable.select { CursosTable.id eq id }
                .mapNotNull { rowToCurso(it) }
                .singleOrNull()
        }
    }

    fun actualizarCurso(curso: Curso) {
        transaction {
            CursosTable.update({ CursosTable.id eq curso.getId() }) {
                it[nombreCurso] = curso.getNombreCurso()
                it[descripcionCurso] = curso.getDescripcionCurso()
                it[intensidadHorariaCurso] = curso.getIntensidadHorariaCurso()
                it[estadoCurso] = curso.getEstadoCurso()
                it[fundacionId] = curso.getFundacionId()
                it[ediciones] = curso.getEdiciones().joinToString(",")
            }
        }
    }

    fun desactivarCurso(id: String) {
        transaction {
            CursosTable.update({ CursosTable.id eq id }) {
                it[estadoCurso] = false
            }
        }
    }

    private fun rowToCurso(row: ResultRow): Curso {
        return Curso(
            id = row[CursosTable.id],
            nombreCurso = row[CursosTable.nombreCurso],
            descripcionCurso = row[CursosTable.descripcionCurso],
            intensidadHorariaCurso = row[CursosTable.intensidadHorariaCurso],
            estadoCurso = row[CursosTable.estadoCurso],
            fundacionId = row[CursosTable.fundacionId],
            ediciones = row[CursosTable.ediciones].split(",").filter { it.isNotBlank() } // CSV a lista
        )
    }
}