package models.secretario.edicion

class ModeloEdicion(
    val tituloEdicion: String,
    val fechaInicioEdicion: String,
    val fechaFinEdicion: String,
    val estadoEdicion: Boolean,
    val cursoId: String,
    val horarios: List<String>,
    val estudiantes: List<String>
) {
    override fun toString(): String {
        return "ModeloEdicion(titulo='$tituloEdicion', fechaInicio='$fechaInicioEdicion', fechaFin='$fechaFinEdicion', estado=$estadoEdicion, cursoId='$cursoId')"
    }
}

// Ejemplos de ediciones precargadas
val edicion1 = ModeloEdicion(
    tituloEdicion = "2024-1",
    fechaInicioEdicion = "2024-01-01",
    fechaFinEdicion = "2024-06-22",
    estadoEdicion = true,
    cursoId = "curso1",
    horarios = listOf("Lunes 8:00 - 10:00", "Miércoles 8:00 - 10:00"),
    estudiantes = listOf("Estudiante1", "Estudiante2", "Estudiante3")
)

val edicion2 = ModeloEdicion(
    tituloEdicion = "2024-2",
    fechaInicioEdicion = "2024-07-01",
    fechaFinEdicion = "2024-12-22",
    estadoEdicion = true,
    cursoId = "curso2",
    horarios = listOf("Martes 14:00 - 16:00", "Jueves 14:00 - 16:00"),
    estudiantes = listOf("Estudiante4", "Estudiante5")
)

val edicion3 = ModeloEdicion(
    tituloEdicion = "2025-1",
    fechaInicioEdicion = "2025-01-01",
    fechaFinEdicion = "2025-06-22",
    estadoEdicion = false,
    cursoId = "curso3",
    horarios = listOf("Viernes 10:00 - 12:00"),
    estudiantes = listOf("Estudiante6", "Estudiante7", "Estudiante8", "Estudiante9")
)
