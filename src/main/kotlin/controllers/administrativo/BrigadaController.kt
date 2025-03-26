package controllers.administrativo

import servicios.administrativo.BrigadaServicio
import java.util.Scanner

class BrigadaController(private val brigadaServicio: BrigadaServicio) {

    companion object {
        private val scanner = Scanner(System.`in`)

        private fun confirmarAccion(mensaje: String): Boolean {
            println("$mensaje (1: Sí, 2: No)")
            return scanner.nextLine() == "1"
        }
    }

    fun crearBrigada() {
        println("Ingrese los datos de la brigada:")
        print("Nombre: ")
        val nombre = scanner.nextLine()
        print("Ubicación: ")
        val ubicacion = scanner.nextLine()
        print("Comando ID: ")
        val comandoId = scanner.nextLine()
        print("Unidades (separadas por comas): ")
        val unidades = scanner.nextLine().split(",")

        if (confirmarAccion("¿Desea crear esta brigada?")) {
            try {
                val nuevaBrigada = brigadaServicio.crearBrigada(
                    nombreBrigada = nombre,
                    ubicacionBrigada = ubicacion,
                    comandoId = comandoId,
                    unidades = unidades
                )
                println("Brigada creada con ID: ${nuevaBrigada.getId()}")
            } catch (e: IllegalArgumentException) {
                println("Error: ${e.message}")
            }
        } else {
            println("Operación cancelada.")
        }
    }

    fun listarBrigadasActivas() {
        val brigadasActivas = brigadaServicio.listarBrigadasActivas()
        if (brigadasActivas.isEmpty()) {
            println("No hay brigadas activas.")
        } else {
            println("Brigadas activas:")
            brigadasActivas.forEach {
                println("ID: ${it.getId()}, Nombre: ${it.getNombreBrigada()}, Ubicación: ${it.getUbicacionBrigada()}, Comando ID: ${it.getComandoId()}, Unidades: ${it.getUnidades()}")
            }
        }
    }

    fun actualizarBrigada() {
        print("Ingrese el ID de la brigada a actualizar: ")
        val id = scanner.nextLine()

        try {
            val brigada = brigadaServicio.obtenerBrigadaPorId(id)
            println("Brigada encontrada: ID: ${brigada.getId()}, Nombre: ${brigada.getNombreBrigada()}")

            print("Nuevo nombre (dejar en blanco para no cambiar): ")
            val nombre = scanner.nextLine().takeIf { it.isNotBlank() }
            print("Nueva ubicación (dejar en blanco para no cambiar): ")
            val ubicacion = scanner.nextLine().takeIf { it.isNotBlank() }
            print("Nuevo comando ID (dejar en blanco para no cambiar): ")
            val comandoId = scanner.nextLine().takeIf { it.isNotBlank() }
            print("Nuevas unidades (separadas por comas, dejar en blanco para no cambiar): ")
            val unidades = scanner.nextLine().takeIf { it.isNotBlank() }?.split(",")

            if (confirmarAccion("¿Desea actualizar esta brigada?")) {
                val brigadaActualizada = brigadaServicio.actualizarBrigada(
                    id = id,
                    nombreBrigada = nombre,
                    ubicacionBrigada = ubicacion,
                    comandoId = comandoId,
                    unidades = unidades
                )
                println("Brigada actualizada: ID: ${brigadaActualizada.getId()}")
            } else {
                println("Operación cancelada.")
            }
        } catch (e: NoSuchElementException) {
            println("Error: ${e.message}")
        }
    }

    fun desactivarBrigada() {
        print("Ingrese el ID de la brigada a desactivar: ")
        val id = scanner.nextLine()

        if (confirmarAccion("¿Desea desactivar esta brigada?")) {
            try {
                val brigadaDesactivada = brigadaServicio.desactivarBrigada(id)
                println("Brigada desactivada: ID: ${brigadaDesactivada.getId()}")
            } catch (e: NoSuchElementException) {
                println("Error: ${e.message}")
            }
        } else {
            println("Operación cancelada.")
        }
    }
}