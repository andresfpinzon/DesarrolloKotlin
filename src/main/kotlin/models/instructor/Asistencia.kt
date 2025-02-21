package models.instructor

import java.util.Date

class Asistencia(
    val id: String,
    val tituloAsistencia: String,
    val fechaAsistencia: Date,
    val usuarioId: String,
    val estadoAsistencia: Boolean,
    val estudiantes: List<String>
)

