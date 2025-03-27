package controllers.secretario.curso

import servicios.secretario.curso.CursoServicio
import java.util.Scanner

class CursoController(private val cursoServicio: CursoServicio) {

    private val scanner = Scanner(System.`in`)

    private fun confirmarAccion(mensaje: String): Boolean {
        println("$mensaje (1: Sí, 2: No)")
        return scanner.nextLine() == "1"
    }

    fun crearCurso() {
        println("Ingrese los datos del curso:")
        print("Nombre del curso: ")
        val nombreCurso = scanner.nextLine()
        print("Descripción del curso: ")
        val descripcionCurso = scanner.nextLine()
        print("Intensidad horaria del curso: ")
        val intensidadHorariaCurso = scanner.nextLine()
        print("Fundación ID: ")
        val fundacionId = scanner.nextLine()
        print("Ediciones (separadas por comas, opcional): ")
        val ediciones = scanner.nextLine().takeIf { it.isNotBlank() }?.split(",") ?: emptyList()

        if (confirmarAccion("¿Desea crear este curso?")) {
            try {
                val nuevoCurso = cursoServicio.crearCurso(
                    nombreCurso = nombreCurso,
                    descripcionCurso = descripcionCurso,
                    intensidadHorariaCurso = intensidadHorariaCurso,
                    fundacionId = fundacionId,
                    ediciones = ediciones
                )
                println("Curso creado con ID: ${nuevoCurso.getId()}")
            } catch (e: IllegalArgumentException) {
                println("Error: ${e.message}")
            }
        } else {
            println("Operación cancelada.")
        }
    }

    fun listarCursosActivos() {
        val cursosActivos = cursoServicio.listarCursosActivos()
        if (cursosActivos.isEmpty()) {
            println("No hay cursos activos.")
        } else {
            println("Cursos activos:")
            cursosActivos.forEach {
                println("""
                    ID: ${it.getId()}
                    Nombre: ${it.getNombreCurso()}
                    Descripción: ${it.getDescripcionCurso()}
                    Intensidad horaria: ${it.getIntensidadHorariaCurso()}
                    Fundación ID: ${it.getFundacionId()}
                    Ediciones: ${it.getEdiciones()}
                """.trimIndent())
            }
        }
    }

    fun actualizarCurso() {
        print("Ingrese el ID del curso a actualizar: ")
        val id = scanner.nextLine()

        try {
            val curso = cursoServicio.obtenerCursoPorId(id)
            println("Curso encontrado: ID: ${curso.getId()}, Nombre: ${curso.getNombreCurso()}")

            print("Nuevo nombre (dejar en blanco para no cambiar): ")
            val nombreCurso = scanner.nextLine().takeIf { it.isNotBlank() }
            print("Nueva descripción (dejar en blanco para no cambiar): ")
            val descripcionCurso = scanner.nextLine().takeIf { it.isNotBlank() }
            print("Nueva intensidad horaria (dejar en blanco para no cambiar): ")
            val intensidadHorariaCurso = scanner.nextLine().takeIf { it.isNotBlank() }
            print("Nuevo estado (1: Activo, 2: Inactivo, dejar en blanco para no cambiar): ")
            val estadoCurso = scanner.nextLine().takeIf { it.isNotBlank() }?.let { it == "1" }
            print("Nueva fundación ID (dejar en blanco para no cambiar): ")
            val fundacionId = scanner.nextLine().takeIf { it.isNotBlank() }
            print("Nuevas ediciones (separadas por comas, dejar en blanco para no cambiar): ")
            val ediciones = scanner.nextLine().takeIf { it.isNotBlank() }?.split(",")

            if (confirmarAccion("¿Desea actualizar este curso?")) {
                val cursoActualizado = cursoServicio.actualizarCurso(
                    id = id,
                    nombreCurso = nombreCurso,
                    descripcionCurso = descripcionCurso,
                    intensidadHorariaCurso = intensidadHorariaCurso,
                    estadoCurso = estadoCurso,
                    fundacionId = fundacionId,
                    ediciones = ediciones
                )
                println("Curso actualizado: ID: ${cursoActualizado.getId()}")
            } else {
                println("Operación cancelada.")
            }
        } catch (e: NoSuchElementException) {
            println("Error: ${e.message}")
        }
    }

    fun desactivarCurso() {
        print("Ingrese el ID del curso a desactivar: ")
        val id = scanner.nextLine()

        if (confirmarAccion("¿Desea desactivar este curso?")) {
            try {
                val cursoDesactivado = cursoServicio.desactivarCurso(id)
                println("Curso desactivado: ID: ${cursoDesactivado.getId()}")
            } catch (e: NoSuchElementException) {
                println("Error: ${e.message}")
            }
        } else {
            println("Operación cancelada.")
        }
    }
}