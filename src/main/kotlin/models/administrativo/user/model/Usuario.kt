package models.administrativo.user.model

import java.time.LocalDate

data class Usuario(
    private val id : Long = 0,
    private val nombreUsuario: String,
    private val apellidoUsuario: String,
    private val numeroDocumento: String,
    private val correo: String,
    private val password: String,
    private val roles: List<String>,
    private val estadoUsuario: Boolean,
    private val creadoEn: LocalDate
) {
    fun getId(): Long = id
    fun getNombreUsuario(): String = nombreUsuario
    fun getApellidoUsuario(): String = apellidoUsuario
    fun getNumeroDocumento(): String = numeroDocumento
    fun getCorreo(): String = correo
    fun getPassword(): String = password
    fun getRoles():List<String> = roles
    fun getEstadoUsuario(): Boolean = estadoUsuario
    fun getCreadoEn(): LocalDate = creadoEn

    companion object{
//        val administrador = Usuario(
//            nombreUsuario = "Carlos",
//            apellidoUsuario = "Gómez",
//            numeroDocumento = "9876543210",
//            correo = "carlos.gomez@gmail.com",
//            password = "password123",
//            roles = listOf("Administrativo"),
//            estadoUsuario = false,
//        )
//
//        val secretario = Usuario(
//            nombreUsuario = "Ana",
//            apellidoUsuario = "Pérez",
//            numeroDocumento = "1234567890",
//            correo = "ana.perez@gmail.com",
//            password = "password456",
//            roles = listOf("Secretario"),
//            estadoUsuario = true,
//            creadoEn = "2023-01-02"
//        )
//
//        val instructor = Usuario(
//            nombreUsuario = "Luis",
//            apellidoUsuario = "Martínez",
//            numeroDocumento = "5555555555",
//            correo = "luis.martinez@gmail.com",
//            password = "password789",
//            roles = listOf("Instructor"),
//            estadoUsuario = true,
//            creadoEn = "2023-01-03"
//        )
    }
}
