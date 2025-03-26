package repositories.administrador.brigada

import models.administrativo.brigada.BrigadaTable
import models.administrativo.brigada.Brigada
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class BrigadaRepository {

    fun crearBrigada(brigada: Brigada) {
        transaction {
            BrigadaTable.insert {
                it[id] = brigada.getId()
                it[nombreBrigada] = brigada.getNombreBrigada()
                it[ubicacionBrigada] = brigada.getUbicacionBrigada()
                it[estadoBrigada] = brigada.getEstadoBrigada()
                it[comandoId] = brigada.getComandoId()
                it[unidades] = brigada.getUnidades().joinToString(",") // Guardar lista como CSV
            }
        }
    }

    fun listarBrigadasActivas(): List<Brigada> {
        return transaction {
            BrigadaTable.select { BrigadaTable.estadoBrigada eq true }
                .map { rowToBrigada(it) }
        }
    }

    fun obtenerBrigadaPorId(id: String): Brigada? {
        return transaction {
            BrigadaTable.select { BrigadaTable.id eq id }
                .mapNotNull { rowToBrigada(it) }
                .singleOrNull()
        }
    }

    fun actualizarBrigada(brigada: Brigada) {
        transaction {
            BrigadaTable.update({ BrigadaTable.id eq brigada.getId() }) {
                it[nombreBrigada] = brigada.getNombreBrigada()
                it[ubicacionBrigada] = brigada.getUbicacionBrigada()
                it[estadoBrigada] = brigada.getEstadoBrigada()
                it[comandoId] = brigada.getComandoId()
                it[unidades] = brigada.getUnidades().joinToString(",")
            }
        }
    }

    fun desactivarBrigada(id: String) {
        transaction {
            BrigadaTable.update({ BrigadaTable.id eq id }) {
                it[estadoBrigada] = false
            }
        }
    }

    private fun rowToBrigada(row: ResultRow): Brigada {
        return Brigada(
            id = row[BrigadaTable.id],
            nombreBrigada = row[BrigadaTable.nombreBrigada],
            ubicacionBrigada = row[BrigadaTable.ubicacionBrigada],
            estadoBrigada = row[BrigadaTable.estadoBrigada],
            comandoId = row[BrigadaTable.comandoId],
            unidades = row[BrigadaTable.unidades].split(",") // Convertir CSV a lista
        )
    }
}