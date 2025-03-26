import models.questions.*
import config.DatabaseConfig

fun main() {
    // Configurar la base de datos
    DatabaseConfig.init()
    println("Conexión a la base de datos establecida.")

    Menu.accederServicios()
}

