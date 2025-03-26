package servicios.instructor.asistencia

import models.instructor.asistencia.Asistencia
import repositories.instructor.asistencia.AsistenciaRepository
import java.util.*

class AsistenciaServicio(private val asistenciaRepository: AsistenciaRepository) {

    fun crearAsistencia(
        tituloAsistencia: String,
        fechaAsistencia: Date,
        usuarioId: String,
        estudiantes: List<String>
    ): Asistencia {
        if (tituloAsistencia.isBlank() || estudiantes.isEmpty()) {
            throw IllegalArgumentException("Faltan campos requeridos: título, estudiantes")
        }

        val nuevaAsistencia = Asistencia(
            tituloAsistencia = tituloAsistencia,
            fechaAsistencia = fechaAsistencia,
            usuarioId = usuarioId,
            estudiantes = estudiantes
        )

        asistenciaRepository.crearAsistencia(nuevaAsistencia)
        return nuevaAsistencia
    }

    fun listarAsistenciasActivas(): List<Asistencia> {
        return asistenciaRepository.listarAsistenciasActivas()
    }

    fun obtenerAsistenciaPorId(id: String): Asistencia {
        return asistenciaRepository.obtenerAsistenciaPorId(id)
            ?: throw NoSuchElementException("Asistencia no encontrada")
    }

    fun actualizarAsistencia(
        id: String,
        tituloAsistencia: String?,
        fechaAsistencia: Date?,
        usuarioId: String?,
        estudiantes: List<String>?
    ): Asistencia {
        val asistencia = asistenciaRepository.obtenerAsistenciaPorId(id)
            ?: throw NoSuchElementException("Asistencia no encontrada")

        tituloAsistencia?.let { asistencia.setTituloAsistencia(it) }
        fechaAsistencia?.let { asistencia.setFechaAsistencia(it) }
        usuarioId?.let { asistencia.setUsuarioId(it) }
        estudiantes?.let { asistencia.setEstudiantes(it) }

        asistenciaRepository.actualizarAsistencia(asistencia)
        return asistencia
    }

    fun desactivarAsistencia(id: String): Asistencia {
        asistenciaRepository.desactivarAsistencia(id)
        return asistenciaRepository.obtenerAsistenciaPorId(id)!!
    }
}
