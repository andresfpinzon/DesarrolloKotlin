package servicios.administrativo

import models.administrativo.brigada.Brigada
import repositories.administrador.brigada.BrigadaRepository
import java.util.*

class BrigadaServicio(private val brigadaRepository: BrigadaRepository) {

    fun crearBrigada(
        nombreBrigada: String,
        ubicacionBrigada: String,
        comandoId: String,
        unidades: List<String>
    ): Brigada {
        if (nombreBrigada.isBlank() || unidades.isEmpty()) {
            throw IllegalArgumentException("Faltan campos requeridos: nombre, unidades")
        }

        val nuevaBrigada = Brigada(
            nombreBrigada = nombreBrigada,
            ubicacionBrigada = ubicacionBrigada,
            comandoId = comandoId,
            unidades = unidades
        )

        brigadaRepository.crearBrigada(nuevaBrigada)
        return nuevaBrigada
    }

    fun listarBrigadasActivas(): List<Brigada> {
        return brigadaRepository.listarBrigadasActivas()
    }

    fun obtenerBrigadaPorId(id: String): Brigada {
        return brigadaRepository.obtenerBrigadaPorId(id)
            ?: throw NoSuchElementException("Brigada no encontrada")
    }

    fun actualizarBrigada(
        id: String,
        nombreBrigada: String?,
        ubicacionBrigada: String?,
        comandoId: String?,
        unidades: List<String>?
    ): Brigada {
        val brigada = brigadaRepository.obtenerBrigadaPorId(id)
            ?: throw NoSuchElementException("Brigada no encontrada")

        nombreBrigada?.let { brigada.setNombreBrigada(it) }
        ubicacionBrigada?.let { brigada.setUbicacionBrigada(it) }
        comandoId?.let { brigada.setComandoId(it) }
        unidades?.let { brigada.setUnidades(it) }

        brigadaRepository.actualizarBrigada(brigada)
        return brigada
    }

    fun desactivarBrigada(id: String): Brigada {
        brigadaRepository.desactivarBrigada(id)
        return brigadaRepository.obtenerBrigadaPorId(id)!!
    }
}