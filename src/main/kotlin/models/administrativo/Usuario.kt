package models.administrativo

class Usuario {
    val nombreUsuario = "Carlos"
    val apellidoUsuario = "Gómez"
    val numeroDocumento = "9876543210"
    val correo = "carlos.gomez@gmail.com"
    val password = "password123"
    val roles = listOf("Adminstrativo", "Root")
    val estadoUsuario = true
    val creadoEn = "2023-01-01"
}