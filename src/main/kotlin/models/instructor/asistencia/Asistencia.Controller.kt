package models.instructor.asistencia

import java.util.Date

import java.util.Scanner

class AsistenciaController(){

    companion object{

        private val scanner = Scanner(System.`in`)

        fun crearAsistencia(asistencias: MutableList<Asistencia>) {
            println("Ingrese los datos de la asistencia:")
            print("ID: ")
            val id = scanner.next()
            print("Título: ")
            val titulo = scanner.next()
            print("Fecha (yyyy-MM-dd): ")
            val fecha = Date(scanner.next())
            print("Usuario ID: ")
            val usuarioId = scanner.next()
            print("Estudiantes (separados por comas): ")
            val estudiantes = scanner.next().split(",")

            try {
                val nuevaAsistencia = AsistenciaServicio.crearAsistencia(
                    asistencias = asistencias,
                    id = id,
                    tituloAsistencia = titulo,
                    fechaAsistencia = fecha,
                    usuarioId = usuarioId,
                    estudiantes = estudiantes
                )
                println("Asistencia creada: $nuevaAsistencia")
            } catch (e: IllegalArgumentException) {
                println("Error: ${e.message}")
            }
        }

        fun listarAsistenciasActivas(asistencias: List<Asistencia>) {
            val asistenciasActivas = AsistenciaServicio.listarAsistenciasActivas(asistencias)
            if (asistenciasActivas.isEmpty()) {
                println("No hay asistencias activas.")
            } else {
                println("Asistencias activas:")
                asistenciasActivas.forEach { println(it) }
            }
        }

        fun actualizarAsistencia(asistencias: MutableList<Asistencia>) {
            print("Ingrese el ID de la asistencia a actualizar: ")
            val id = scanner.next()

            try {
                val asistencia = AsistenciaServicio.obtenerAsistenciaPorId(asistencias, id)
                println("Asistencia encontrada: $asistencia")

                print("Nuevo título (dejar en blanco para no cambiar): ")
                val titulo = scanner.nextLine().takeIf { it.isNotBlank() }
                print("Nueva fecha (yyyy-MM-dd, dejar en blanco para no cambiar): ")
                val fecha = scanner.nextLine().takeIf { it.isNotBlank() }?.let { Date(it) }
                print("Nuevo usuario ID (dejar en blanco para no cambiar): ")
                val usuarioId = scanner.nextLine().takeIf { it.isNotBlank() }
                print("Nuevos estudiantes (separados por comas, dejar en blanco para no cambiar): ")
                val estudiantes = scanner.nextLine().takeIf { it.isNotBlank() }?.split(",")

                val asistenciaActualizada = AsistenciaServicio.actualizarAsistencia(
                    asistencias = asistencias,
                    id = id,
                    tituloAsistencia = titulo,
                    fechaAsistencia = fecha,
                    usuarioId = usuarioId,
                    estudiantes = estudiantes
                )
                println("Asistencia actualizada: $asistenciaActualizada")
            } catch (e: NoSuchElementException) {
                println("Error: ${e.message}")
            }
        }

        fun desactivarAsistencia(asistencias: MutableList<Asistencia>) {
            print("Ingrese el ID de la asistencia a desactivar: ")
            val id = scanner.next()

            try {
                val asistenciaDesactivada = AsistenciaServicio.desactivarAsistencia(asistencias, id)
                println("Asistencia desactivada: $asistenciaDesactivada")
            } catch (e: NoSuchElementException) {
                println("Error: ${e.message}")
            }
        }
    }
}