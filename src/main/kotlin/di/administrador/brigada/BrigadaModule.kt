package di.administrador.brigada

import controllers.administrativo.BrigadaController
import servicios.administrativo.BrigadaServicio
import repositories.administrador.brigada.BrigadaRepository

object BrigadaModule {
    private val brigadaRepository = BrigadaRepository()
    private val brigadaServicio = BrigadaServicio(brigadaRepository)
    val brigadaController = BrigadaController(brigadaServicio)
}