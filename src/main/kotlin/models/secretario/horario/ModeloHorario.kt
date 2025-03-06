package models.secretario.horario

class ModeloHorario(
    val tituloHorario: String,
    val horaInicio: String,
    val horaFin: String,
    val estadoHorario: Boolean
) {
    val horario1 = ModeloHorario(
        tituloHorario = "Mañana",
        horaInicio = "08:00",
        horaFin = "10:00",
        estadoHorario = true
    )

    val horario2 = ModeloHorario(
        tituloHorario = "Tarde",
        horaInicio = "14:00",
        horaFin = "16:00",
        estadoHorario = true
    )

    val horario3 = ModeloHorario(
        tituloHorario = "Noche",
        horaInicio = "18:00",
        horaFin = "20:00",
        estadoHorario = false
    )
}
