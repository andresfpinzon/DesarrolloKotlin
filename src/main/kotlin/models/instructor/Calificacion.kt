package models.instructor

class Calificacion(
    val id: String,
    val tituloCalificacion: String,
    val aprobado: Boolean,
    val usuarioId: String,
    val estadoCalificacion: Boolean,
    val estudiantes: List<String>
)