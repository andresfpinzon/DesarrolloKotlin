package models.root.fundacion

class Fundacion(
    val id: String,
    var nombreFundacion: String,
    var estadoFundacion: Boolean,
    var comando: List<String>
) {
    companion object {
        val fundacion1 = Fundacion(
            id = "FUN-001",
            nombreFundacion = "FuSDEC",
            estadoFundacion = true,
            comando = listOf("COMANDO 1", "COMANDO 2")
        )

    }
}