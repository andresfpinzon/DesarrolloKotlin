package models.secretario.curso

class ServiciosCurso {
    private val cursos = mutableListOf<ModelCurso>()
    private var contadorId = 4

    // Generar un ID único
    private fun generarId(): String {
        return "curso${contadorId++}"
    }

    // Metodo para crear un curso
    fun crearCurso() {
        println("Ingrese los datos del curso:")

        print("Nombre del Curso: ")
        val nombreCurso = readLine() ?: ""

        print("Descripción: ")
        val descripcionCurso = readLine() ?: ""

        print("Intensidad Horaria: ")
        val intensidadHorariaCurso = readLine() ?: ""

        print("Estado del Curso (true/false): ")
        val estadoCurso = readLine()?.toBoolean() ?: true

        print("Fundación ID: ")
        val fundacionId = readLine() ?: ""

        val id = generarId()
        val curso = ModelCurso(id, nombreCurso, descripcionCurso, intensidadHorariaCurso, estadoCurso, fundacionId, listOf())
        cursos.add(curso)

        println("Curso agregado: $curso")
    }

    // Metodo para mostrar todos los cursos
    fun mostrarCursos() {
        if (cursos.isEmpty()) {
            println("No hay cursos disponibles.")
        } else {
            println("Cursos disponibles:")
            cursos.forEach { curso ->
                println("ID: ${curso.id}, Nombre: ${curso.nombreCurso}, Intensidad: ${curso.intensidadHorariaCurso}, Estado: ${curso.estadoCurso}")
            }
        }
    }

    // Metodo para actualizar un curso
    fun actualizarCurso() {
        println("Ingrese el ID del curso a actualizar:")
        val id = readLine() ?: ""

        val curso = cursos.find { it.id == id }
        if (curso != null) {
            println("Curso encontrado. Ingrese los nuevos datos (deje en blanco para no cambiar):")

            print("Nuevo nombre del curso: ")
            val nuevoNombre = readLine()?.takeIf { it.isNotBlank() }

            print("Nueva descripción: ")
            val nuevaDescripcion = readLine()?.takeIf { it.isNotBlank() }

            print("Nueva intensidad horaria: ")
            val nuevaIntensidad = readLine()?.takeIf { it.isNotBlank() }

            print("Nuevo estado (true/false): ")
            val nuevoEstado = readLine()?.toBoolean()

            print("Nueva fundación ID: ")
            val nuevaFundacionId = readLine()?.takeIf { it.isNotBlank() }

            // Crear un nuevo objeto actualizado
            val cursoActualizado = ModelCurso(
                id = curso.id,
                nombreCurso = nuevoNombre ?: curso.nombreCurso,
                descripcionCurso = nuevaDescripcion ?: curso.descripcionCurso,
                intensidadHorariaCurso = nuevaIntensidad ?: curso.intensidadHorariaCurso,
                estadoCurso = nuevoEstado ?: curso.estadoCurso,
                fundacionId = nuevaFundacionId ?: curso.fundacionId,
                ediciones = curso.ediciones
            )

            // Reemplazar el curso en la lista
            cursos[cursos.indexOf(curso)] = cursoActualizado
            println("Curso actualizado: $cursoActualizado")
        } else {
            println("Curso con ID $id no encontrado.")
        }
    }

    // Metodo para eliminar un curso
    fun eliminarCurso() {
        println("Ingrese el ID del curso a eliminar:")
        val id = readLine() ?: ""

        val curso = cursos.find { it.id == id }
        if (curso != null) {
            cursos.remove(curso)
            println("Curso eliminado: $curso")
        } else {
            println("Curso con ID $id no encontrado.")
        }
    }
}

