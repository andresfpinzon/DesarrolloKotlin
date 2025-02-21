import models.administrativo.*
import models.questions.ConfirmarUsuario

fun main() {

    val administrador = Usuario(
        nombreUsuario = "Carlos",
        apellidoUsuario = "Gómez",
        numeroDocumento = "9876543210",
        correo = "carlos.gomez@gmail.com",
        password = "password123",
        roles = listOf("Instructor"),
        estadoUsuario = false,
        creadoEn = "2023-01-01"
    )

    val secretario = Usuario(
        nombreUsuario = "Ana",
        apellidoUsuario = "Pérez",
        numeroDocumento = "1234567890",
        correo = "ana.perez@gmail.com",
        password = "password456",
        roles = listOf("Secretario"),
        estadoUsuario = true,
        creadoEn = "2023-01-02"
    )


    val confirmacion = "SI"
    val rolPermitido = "Secretario"


    println("Desea ingresar al sistema? Confirma con SI o con NO:")
    val confirmarIngreso: String = readln()
    if (confirmarIngreso.uppercase() == confirmacion) {
        val ingresar = ConfirmarUsuario(administrador.estadoUsuario)
        ingresar.verificarEstado()
    } else {
        println("Ingreso cancelado.")
    }

    println("Desea crear un estudiante? Confirma con SI o con NO:")
    val confirmarAccion: String = readln()
    if (confirmarAccion.uppercase() == confirmacion) {
        if (secretario.roles.contains(rolPermitido)) {
            val crearEstudiante = ConfirmarUsuario(true)
            crearEstudiante.verificarRol()
        } else {
            val crearEstudiante = ConfirmarUsuario(false)
            crearEstudiante.verificarRol()
        }
    } else {
        println("Acción cancelada.")
    }
}