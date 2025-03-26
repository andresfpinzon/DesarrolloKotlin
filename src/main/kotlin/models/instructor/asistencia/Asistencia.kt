package models.instructor.asistencia

import java.util.Date
import java.util.UUID

data class Asistencia(
    private val id: String = UUID.randomUUID().toString(),
    private var tituloAsistencia: String,
    private var fechaAsistencia: Date,
    private var usuarioId: String,
    private var estadoAsistencia: Boolean = true,
    private var estudiantes: List<String>
) {
    // Getters
    fun getId(): String = id
    fun getTituloAsistencia(): String = tituloAsistencia
    fun getFechaAsistencia(): Date = fechaAsistencia
    fun getUsuarioId(): String = usuarioId
    fun getEstadoAsistencia(): Boolean = estadoAsistencia
    fun getEstudiantes(): List<String> = estudiantes.toList()

    // Setters con validación básica
    fun setTituloAsistencia(value: String) {
        require(value.isNotBlank()) { "El título no puede estar vacío" }
        tituloAsistencia = value
    }

    fun setFechaAsistencia(value: Date) {
        fechaAsistencia = value
    }

    fun setUsuarioId(value: String) {
        require(value.isNotBlank()) { "El ID de usuario no puede estar vacío" }
        usuarioId = value
    }

    fun setEstadoAsistencia(value: Boolean) {
        estadoAsistencia = value
    }

    fun setEstudiantes(value: List<String>) {
        require(value.isNotEmpty()) { "Debe haber al menos un estudiante" }
        estudiantes = value.toList() // Almacena copia
    }

}
