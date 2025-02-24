package models.questions
import models.administrativo.Usuario

class Acciones() {

    companion object{
    fun comprobarEstado(usuario: Usuario, confirmacion: String) {
        println("Desea ingresar al sistema? Confirma con SI o con NO:")
        val confirmarIngreso: String = readln()
        if (confirmarIngreso.uppercase() == confirmacion) {
            val ingresar = ConfirmarUsuario()
            ingresar.verificarEstado(usuario.estadoUsuario)
        } else {
            println("Ingreso cancelado.")
        }
    }


    fun crearEstudiante(usuario: Usuario, confirmacion: String, rolPermitido: String) {
        println("Desea crear un estudiante? Confirma con SI o con NO:")
        val confirmarAccion: String = readln()
        if (confirmarAccion.uppercase() == confirmacion) {
            if (usuario.roles.contains(rolPermitido)) {
                val crearEstudiante = ConfirmarUsuario()
                crearEstudiante.verificarRol(true)
            } else {
                val crearEstudiante = ConfirmarUsuario()
                crearEstudiante.verificarRol(false)
            }
        } else {
            println("Acción cancelada.")
        }
    }
    }
}
