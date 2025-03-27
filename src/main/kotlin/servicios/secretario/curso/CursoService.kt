package servicios.secretario.curso

import models.secretario.curso.Curso
import repositories.secretario.curso.CursoRepository
import java.util.*

class CursoServicio(private val cursoRepository: CursoRepository) {

    fun crearCurso(
        nombreCurso: String,
        descripcionCurso: String,
        intensidadHorariaCurso: String,
        fundacionId: String,
        estadoCurso: Boolean = true,
        ediciones: List<String> = emptyList()
    ): Curso {
        if (nombreCurso.isBlank() || descripcionCurso.isBlank() || intensidadHorariaCurso.isBlank() || fundacionId.isBlank()) {
            throw IllegalArgumentException("Campos requeridos: nombre, descripción, intensidad horaria y fundación ID")
        }

        val nuevoCurso = Curso(
            id = UUID.randomUUID().toString(),
            nombreCurso = nombreCurso,
            descripcionCurso = descripcionCurso,
            intensidadHorariaCurso = intensidadHorariaCurso,
            estadoCurso = estadoCurso,
            fundacionId = fundacionId,
            ediciones = ediciones
        )

        cursoRepository.guardarCurso(nuevoCurso)
        return nuevoCurso
    }

    fun listarCursosActivos(): List<Curso> {
        return cursoRepository.listarCursosActivos()
    }

    fun obtenerCursoPorId(id: String): Curso {
        return cursoRepository.obtenerCursoPorId(id)
            ?: throw NoSuchElementException("Curso no encontrado")
    }

    fun actualizarCurso(
        id: String,
        nombreCurso: String? = null,
        descripcionCurso: String? = null,
        intensidadHorariaCurso: String? = null,
        estadoCurso: Boolean? = null,
        fundacionId: String? = null,
        ediciones: List<String>? = null
    ): Curso {
        val curso = cursoRepository.obtenerCursoPorId(id)
            ?: throw NoSuchElementException("Curso no encontrado")

        nombreCurso?.let { curso.setNombreCurso(it) }
        descripcionCurso?.let { curso.setDescripcionCurso(it) }
        intensidadHorariaCurso?.let { curso.setIntensidadHorariaCurso(it) }
        estadoCurso?.let { curso.setEstadoCurso(it) }
        fundacionId?.let { curso.setFundacionId(it) }
        ediciones?.let { curso.setEdiciones(it) }

        cursoRepository.actualizarCurso(curso)
        return curso
    }

    fun desactivarCurso(id: String): Curso {
        val curso = cursoRepository.obtenerCursoPorId(id)
            ?: throw NoSuchElementException("Curso no encontrado")
        curso.setEstadoCurso(false)
        cursoRepository.actualizarCurso(curso)
        return curso
    }
}