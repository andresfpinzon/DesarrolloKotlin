import models.administrativo.Usuario
import models.questions.ConfirmarUsuario


fun ingresarAlSistema(usuario: Usuario, confirmacion: String) {
    println("Desea ingresar al sistema? Confirma con SI o con NO:")
    val confirmarIngreso: String = readln()
    if (confirmarIngreso.uppercase() == confirmacion) {
        val ingresar = ConfirmarUsuario(usuario.estadoUsuario)
        ingresar.verificarEstado()
    } else {
        println("Ingreso cancelado.")
    }
}


fun crearEstudiante(usuario: Usuario, confirmacion: String, rolPermitido: String) {
    println("Desea crear un estudiante? Confirma con SI o con NO:")
    val confirmarAccion: String = readln()
    if (confirmarAccion.uppercase() == confirmacion) {
        if (usuario.roles.contains(rolPermitido)) {
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

fun main() {

    val confirmacion = "SI"
    val rolPermitido = "Secretario"


    val administrador = Usuario(
        nombreUsuario = "Carlos",
        apellidoUsuario = "Gómez",
        numeroDocumento = "9876543210",
        correo = "carlos.gomez@gmail.com",
        password = "password123",
        roles = listOf("Administrativo"),
        estadoUsuario = false,
        creadoEn = "2023-01-01"
    )

    ingresarAlSistema(administrador, confirmacion)
    crearEstudiante(administrador, confirmacion, rolPermitido)

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

    ingresarAlSistema(secretario, confirmacion)
    crearEstudiante(secretario, confirmacion, rolPermitido)


    val instructor = Usuario(
        nombreUsuario = "Luis",
        apellidoUsuario = "Martínez",
        numeroDocumento = "5555555555",
        correo = "luis.martinez@gmail.com",
        password = "password789",
        roles = listOf("Instructor"),
        estadoUsuario = true,
        creadoEn = "2023-01-03"
    )

    ingresarAlSistema(instructor, confirmacion)
    crearEstudiante(instructor, confirmacion, rolPermitido)
}