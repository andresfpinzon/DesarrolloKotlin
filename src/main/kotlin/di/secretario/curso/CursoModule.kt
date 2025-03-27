package di.secretario.curso

import controllers.secretario.curso.CursoController
import servicios.secretario.curso.CursoServicio
import repositories.secretario.curso.CursoRepository

object CursoModule {
    private val cursoRepository = CursoRepository()
    private val cursoServicio = CursoServicio(cursoRepository)
    val cursoController = CursoController(cursoServicio)
}