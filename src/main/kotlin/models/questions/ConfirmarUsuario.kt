package models.questions

class ConfirmarUsuario() {

    fun verificarEstado(confirmacion: Boolean) {
        if (confirmacion) {
            println("Usuario Activo, Puede ingresar al programa")
        } else {
            println("Usuario inactivo, comunicarse con administración para solucionar el problema")
        }
    }

    fun verificarRol(confirmacion: Boolean) {
        if (confirmacion) {
            println("Usted tiene el rol necesario para realizar la acción")
        } else {
            println("Lo sentimos, Usted no cuenta con el rol necesario para realizar la acción")
        }
    }
}