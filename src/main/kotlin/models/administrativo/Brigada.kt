package models.administrativo

class Brigada(
    val id: String,
    val nombreBrigada: String,
    val ubicacionBrigada: String,
    val estadoBrigada: Boolean,
    val comandoId: String,
    val unidades: List<String>
)