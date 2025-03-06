package models.secretario.edicion

class ServiciosEdicion {
    private val ediciones = mutableListOf<ModeloEdicion>()

    // Método para crear una nueva edición
    fun crearEdicion() {
        println("Ingrese los datos de la edición:")

        print("Título de la edición: ")
        val titulo = readLine() ?: ""

        print("Fecha de inicio (YYYY-MM-DD): ")
        val fechaInicio = readLine() ?: ""

        print("Fecha de fin (YYYY-MM-DD): ")
        val fechaFin = readLine() ?: ""

        print("Estado de la edición (true/false): ")
        val estado = readLine()?.toBoolean() ?: true

        print("ID del curso asociado: ")
        val cursoId = readLine() ?: ""

        // Crear y agregar la edición
        val edicion = ModeloEdicion(
            tituloEdicion = titulo,
            fechaInicioEdicion = fechaInicio,
            fechaFinEdicion = fechaFin,
            estadoEdicion = estado,
            cursoId = cursoId,
            horarios = listOf(),
            estudiantes = listOf()
        )
        ediciones.add(edicion)
        println("Edición creada: $edicion")
    }

    // Método para mostrar todas las ediciones
    fun mostrarEdiciones() {
        if (ediciones.isEmpty()) {
            println("No hay ediciones disponibles.")
        } else {
            println("Ediciones disponibles:")
            ediciones.forEach { edicion ->
                println("Título: ${edicion.tituloEdicion}, Curso ID: ${edicion.cursoId}, Estado: ${edicion.estadoEdicion}")
            }
        }
    }

    // Método para actualizar una edición
    fun actualizarEdicion() {
        println("Ingrese el título de la edición a actualizar:")
        val titulo = readLine() ?: ""

        val edicion = ediciones.find { it.tituloEdicion == titulo }
        if (edicion != null) {
            println("Edición encontrada. Ingrese los nuevos datos (deje en blanco para no cambiar):")

            print("Nuevo título: ")
            val nuevoTitulo = readLine()?.takeIf { it.isNotBlank() }

            print("Nueva fecha de inicio (YYYY-MM-DD): ")
            val nuevaFechaInicio = readLine()?.takeIf { it.isNotBlank() }

            print("Nueva fecha de fin (YYYY-MM-DD): ")
            val nuevaFechaFin = readLine()?.takeIf { it.isNotBlank() }

            print("Nuevo estado (true/false): ")
            val nuevoEstado = readLine()?.toBoolean()

            print("Nuevo curso ID: ")
            val nuevoCursoId = readLine()?.takeIf { it.isNotBlank() }

            // Crear un nuevo objeto actualizado
            val edicionActualizada = ModeloEdicion(
                tituloEdicion = nuevoTitulo ?: edicion.tituloEdicion,
                fechaInicioEdicion = nuevaFechaInicio ?: edicion.fechaInicioEdicion,
                fechaFinEdicion = nuevaFechaFin ?: edicion.fechaFinEdicion,
                estadoEdicion = nuevoEstado ?: edicion.estadoEdicion,
                cursoId = nuevoCursoId ?: edicion.cursoId,
                horarios = edicion.horarios,
                estudiantes = edicion.estudiantes
            )

            // Reemplazar la edición en la lista
            ediciones[ediciones.indexOf(edicion)] = edicionActualizada
            println("Edición actualizada: $edicionActualizada")
        } else {
            println("Edición con título '$titulo' no encontrada.")
        }
    }

    // Método para eliminar una edición
    fun eliminarEdicion() {
        println("Ingrese el título de la edición a eliminar:")
        val titulo = readLine() ?: ""

        val edicion = ediciones.find { it.tituloEdicion == titulo }
        if (edicion != null) {
            ediciones.remove(edicion)
            println("Edición eliminada: $edicion")
        } else {
            println("Edición con título '$titulo' no encontrada.")
        }
    }
}
