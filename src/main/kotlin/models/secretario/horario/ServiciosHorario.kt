package models.secretario.horario

class ServiciosHorario {
    private val horarios = mutableListOf<ModeloHorario>()

    // Metodo para crear un nuevo horario
    fun crearHorario() {
        println("Ingrese los datos del horario:")

        print("Título del horario: ")
        val titulo = readLine() ?: ""

        print("Hora de inicio (HH:MM): ")
        val horaInicio = readLine() ?: ""

        print("Hora de fin (HH:MM): ")
        val horaFin = readLine() ?: ""

        print("Estado del horario (true/false): ")
        val estado = readLine()?.toBoolean() ?: true

        // Crear y agregar el horario
        val horario = ModeloHorario(
            tituloHorario = titulo,
            horaInicio = horaInicio,
            horaFin = horaFin,
            estadoHorario = estado
        )
        horarios.add(horario)
        println("Horario creado: $horario")
    }

    // Metodo para mostrar todos los horarios
    fun mostrarHorarios() {
        if (horarios.isEmpty()) {
            println("No hay horarios disponibles.")
        } else {
            println("Horarios disponibles:")
            horarios.forEach { horario ->
                println("Título: ${horario.tituloHorario}, Hora Inicio: ${horario.horaInicio}, Hora Fin: ${horario.horaFin}, Estado: ${horario.estadoHorario}")
            }
        }
    }

    // Metodo para actualizar un horario
    fun actualizarHorario() {
        println("Ingrese el título del horario a actualizar:")
        val titulo = readLine() ?: ""

        val horario = horarios.find { it.tituloHorario == titulo }
        if (horario != null) {
            println("Horario encontrado. Ingrese los nuevos datos (deje en blanco para no cambiar):")

            print("Nuevo título: ")
            val nuevoTitulo = readLine()?.takeIf { it.isNotBlank() }

            print("Nueva hora de inicio (HH:MM): ")
            val nuevaHoraInicio = readLine()?.takeIf { it.isNotBlank() }

            print("Nueva hora de fin (HH:MM): ")
            val nuevaHoraFin = readLine()?.takeIf { it.isNotBlank() }

            print("Nuevo estado (true/false): ")
            val nuevoEstado = readLine()?.toBoolean()

            // Crear un nuevo objeto actualizado
            val horarioActualizado = ModeloHorario(
                tituloHorario = nuevoTitulo ?: horario.tituloHorario,
                horaInicio = nuevaHoraInicio ?: horario.horaInicio,
                horaFin = nuevaHoraFin ?: horario.horaFin,
                estadoHorario = nuevoEstado ?: horario.estadoHorario
            )

            // Reemplazar el horario en la lista
            horarios[horarios.indexOf(horario)] = horarioActualizado
            println("Horario actualizado: $horarioActualizado")
        } else {
            println("Horario con título '$titulo' no encontrado.")
        }
    }

    // Metodo para eliminar un horario
    fun eliminarHorario() {
        println("Ingrese el título del horario a eliminar:")
        val titulo = readLine() ?: ""

        val horario = horarios.find { it.tituloHorario == titulo }
        if (horario != null) {
            horarios.remove(horario)
            println("Horario eliminado: $horario")
        } else {
            println("Horario con título '$titulo' no encontrado.")
        }
    }
}
