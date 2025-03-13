package servicios.root.fundacion

import models.root.fundacion.Fundacion
import java.util.*

class FundacionServicio(){

    companion object{
        fun crearFundacion(
            fundaciones: MutableList<Fundacion>,
            id: String,
            nombreFundacion: String,
            estadoFundacion: Boolean = true,
            comando: List<String> = emptyList()
        ): Fundacion {
            if (nombreFundacion.isBlank() || comando.isEmpty()) {
                throw IllegalArgumentException("Faltan campos requeridos: nombreFundacion, comando")
            }

            val nuevaFundacion = Fundacion(
                id = id,
                nombreFundacion = nombreFundacion,
                estadoFundacion = estadoFundacion,
                comando = comando
            )

            fundaciones.add(nuevaFundacion)
            return nuevaFundacion
        }

        fun listarFundacionesActivas(fundaciones: List<Fundacion>): List<Fundacion> {
            return fundaciones.filter { it.estadoFundacion }
        }

        fun obtenerFundacionPorId(fundaciones: List<Fundacion>, id: String): Fundacion {
            return fundaciones.find { it.id == id } ?: throw NoSuchElementException("Fundacion no encontrada")
        }

        fun actualizarFundacion(
            fundaciones: MutableList<Fundacion>,
            id: String,
            nombreFundacion: String? = null,
            estadoFundacion: Boolean? = null,
            comando: List<String>? = null
        ): Fundacion {
            val fundacion = fundaciones.find { it.id == id } ?: throw NoSuchElementException("Fundacion no encontrada")

            fundacion.nombreFundacion  = nombreFundacion ?: fundacion.nombreFundacion
            fundacion.estadoFundacion = estadoFundacion ?: fundacion.estadoFundacion
            fundacion.comando = comando ?: fundacion.comando

            return fundacion
        }

        fun desactivarFundacion(fundaciones: MutableList<Fundacion>, id: String): Fundacion {
            val  fundacion = fundaciones.find { it.id == id } ?: throw NoSuchElementException("Fundacion no encontrada")
            fundacion.estadoFundacion = false
            return fundacion
        }

    }
}

