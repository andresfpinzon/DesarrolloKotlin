package models.administrativo

class Comando(
    val id: String,
    val nombreComando: String,
    val estadoComando: Boolean,
    val ubicacionComando: String,
    val fundacionId: String,
    val brigadas: List<String>
)