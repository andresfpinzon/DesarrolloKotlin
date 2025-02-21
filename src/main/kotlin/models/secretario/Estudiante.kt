package models.secretario

import java.util.Date

class Estudiante(
    val id: String,
    val nombreEstudiante: String,
    val apellidoEstudiante: String,
    val correoEstudiante: String,
    val tipoDocumento: String,
    val numeroDocumento: String,
    val fechaNacimiento: Date,
    val generoEstudiante: String,
    val unidadId: String,
    val colegioId: String,
    val estadoEstudiante: Boolean,
    val ediciones: List<String>,
    val calificaciones: List<String>,
    val asistencias: List<String>,
    val certificados: List<String>
)
