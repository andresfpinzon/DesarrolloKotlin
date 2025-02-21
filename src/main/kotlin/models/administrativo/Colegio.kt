package models.administrativo

class Colegio(
    val id: String,
    val nombreColegio: String,
    val emailColegio: String,
    val estadoColegio: Boolean,
    val estudiantes: List<String>
)