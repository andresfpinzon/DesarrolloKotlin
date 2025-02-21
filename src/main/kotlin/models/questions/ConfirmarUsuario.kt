package models.questions

class ConfirmarUsuario(val confirmacion: Boolean) {

    fun verificarEstado() {
        if (confirmacion) {
            println("Usuario Activo, Puede ingresar al programa")
        } else {
            println("Usuario inactivo, comunicarse con administración para solucionar el problema")
        }
    }

    fun verificarRol() {
        if (confirmacion) {
            println("Usted tiene el rol necesario para realizar la acción")
        } else {
            println("Lo sentimos, Usted no cuenta con el rol necesario para realizar la acción")
        }
    }
}