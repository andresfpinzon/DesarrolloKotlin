package models.secretario

import java.util.Date

class Edicion(
    val tituloEdicion: String,
    val fechaInicioEdicion: Date,
    val fechaFinEdicion: Date,
    val estadoEdicion: Boolean,
    val cursoId: String,
    val horarios: List<String>,
    val estudiantes: List<String>
)