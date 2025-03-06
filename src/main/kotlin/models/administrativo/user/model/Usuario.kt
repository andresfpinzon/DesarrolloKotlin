package models.administrativo.user.model

data class Usuario(
    val id : String,
    val nombreUsuario: String,
    val apellidoUsuario: String,
    val numeroDocumento: String,
    val correo: String,
    val password: String,
    val roles: List<String>,
    val estadoUsuario: Boolean,
    val creadoEn: String
) {
}
