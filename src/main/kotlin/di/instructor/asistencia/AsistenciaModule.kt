package di.instructor.asistencia

import controllers.instructor.asistencia.AsistenciaController
import servicios.instructor.asistencia.AsistenciaServicio
import repositories.instructor.asistencia.AsistenciaRepository

object AsistenciaModule {
    private val asistenciaRepository = AsistenciaRepository()
    private val asistenciaServicio = AsistenciaServicio(asistenciaRepository)
    val asistenciaController = AsistenciaController(asistenciaServicio)
}
