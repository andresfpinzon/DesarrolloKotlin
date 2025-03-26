package models.administrativo.brigada

import org.jetbrains.exposed.sql.Table

object BrigadaTable : Table("brigadas") {
    val id = varchar("id", 50).uniqueIndex()
    val nombreBrigada = varchar("nombre_brigada", 255)
    val ubicacionBrigada = varchar("ubicacion_brigada", 255)
    val estadoBrigada = bool("estado_brigada").default(true)
    val comandoId = varchar("comando_id", 50)
    val unidades = text("unidades") // Se guardará como JSON o CSV

    override val primaryKey = PrimaryKey(id)
}