package models.secretario

class Curso(
    val id: String,
    val nombreCurso: String,
    val descripcionCurso: String,
    val intensidadHorariaCurso: String,
    val estadoCurso: Boolean,
    val fundacionId: String,
    val ediciones: List<String>
)