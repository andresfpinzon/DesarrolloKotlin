package controllers.instructor.asistencia

import servicios.instructor.asistencia.AsistenciaServicio
import java.util.Date
import java.util.Scanner

class AsistenciaController(private val asistenciaServicio: AsistenciaServicio) {

    companion object {
        private val scanner = Scanner(System.`in`)

        private fun confirmarAccion(mensaje: String): Boolean {
            println("$mensaje (1: Sí, 2: No)")
            return scanner.nextLine() == "1"
        }
    }

    fun crearAsistencia() {
        println("Ingrese los datos de la asistencia:")
        print("Título: ")
        val titulo = scanner.nextLine()
        print("Fecha (yyyy-MM-dd): ")
        val fecha = Date(scanner.nextLine())
        print("Usuario ID: ")
        val usuarioId = scanner.nextLine()
        print("Estudiantes (separados por comas): ")
        val estudiantes = scanner.nextLine().split(",")

        if (confirmarAccion("¿Desea crear esta asistencia?")) {
            try {
                val nuevaAsistencia = asistenciaServicio.crearAsistencia(
                    tituloAsistencia = titulo,
                    fechaAsistencia = fecha,
                    usuarioId = usuarioId,
                    estudiantes = estudiantes
                )
                println("Asistencia creada con ID: ${nuevaAsistencia.getId()}")
            } catch (e: IllegalArgumentException) {
                println("Error: ${e.message}")
            }
        } else {
            println("Operación cancelada.")
        }
    }

    fun listarAsistenciasActivas() {
        val asistenciasActivas = asistenciaServicio.listarAsistenciasActivas()
        if (asistenciasActivas.isEmpty()) {
            println("No hay asistencias activas.")
        } else {
            println("Asistencias activas:")
            asistenciasActivas.forEach {
                println("ID: ${it.getId()}, Título: ${it.getTituloAsistencia()}, Fecha: ${it.getFechaAsistencia()}, Usuario: ${it.getUsuarioId()}, Estudiantes: ${it.getEstudiantes()}")
            }
        }
    }

    fun actualizarAsistencia() {
        print("Ingrese el ID de la asistencia a actualizar: ")
        val id = scanner.nextLine()

        try {
            val asistencia = asistenciaServicio.obtenerAsistenciaPorId(id)
            println("Asistencia encontrada: ID: ${asistencia.getId()}, Título: ${asistencia.getTituloAsistencia()}")

            print("Nuevo título (dejar en blanco para no cambiar): ")
            val titulo = scanner.nextLine().takeIf { it.isNotBlank() }
            print("Nueva fecha (yyyy-MM-dd, dejar en blanco para no cambiar): ")
            val fecha = scanner.nextLine().takeIf { it.isNotBlank() }?.let { Date(it) }
            print("Nuevo usuario ID (dejar en blanco para no cambiar): ")
            val usuarioId = scanner.nextLine().takeIf { it.isNotBlank() }
            print("Nuevos estudiantes (separados por comas, dejar en blanco para no cambiar): ")
            val estudiantes = scanner.nextLine().takeIf { it.isNotBlank() }?.split(",")

            if (confirmarAccion("¿Desea actualizar esta asistencia?")) {
                val asistenciaActualizada = asistenciaServicio.actualizarAsistencia(
                    id = id,
                    tituloAsistencia = titulo,
                    fechaAsistencia = fecha,
                    usuarioId = usuarioId,
                    estudiantes = estudiantes
                )
                println("Asistencia actualizada: ID: ${asistenciaActualizada.getId()}")
            } else {
                println("Operación cancelada.")
            }
        } catch (e: NoSuchElementException) {
            println("Error: ${e.message}")
        }
    }

    fun desactivarAsistencia() {
        print("Ingrese el ID de la asistencia a desactivar: ")
        val id = scanner.nextLine()

        if (confirmarAccion("¿Desea desactivar esta asistencia?")) {
            try {
                val asistenciaDesactivada = asistenciaServicio.desactivarAsistencia(id)
                println("Asistencia desactivada: ID: ${asistenciaDesactivada.getId()}")
            } catch (e: NoSuchElementException) {
                println("Error: ${e.message}")
            }
        } else {
            println("Operación cancelada.")
        }
    }
}

