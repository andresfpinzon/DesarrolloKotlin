package models.administrativo

class Unidad(
    val id: String,
    val nombreUnidad: String,
    val estadoUnidad: Boolean,
    val brigadaId: String,
    val usuarioId: String,
    val estudiantes: List<String>
)