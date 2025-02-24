import models.administrativo.Usuario
import models.questions.Acciones

fun main() {

    val confirmacion = "SI"
    val rolPermitido = "Secretario"

    println("___________________________________________________________")
    Acciones.comprobarEstado(Usuario.administrador, confirmacion)
    Acciones.crearEstudiante(Usuario.administrador, confirmacion, rolPermitido)

    println("___________________________________________________________")
    Acciones.comprobarEstado(Usuario.secretario, confirmacion)
    Acciones.crearEstudiante(Usuario.secretario, confirmacion, rolPermitido)

    println("___________________________________________________________")
    Acciones.comprobarEstado(Usuario.instructor, confirmacion)
    Acciones.crearEstudiante(Usuario.instructor, confirmacion, rolPermitido)
}