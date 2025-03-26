package models.administrativo.brigada

import java.util.UUID

data class Brigada(
    private val id: String = UUID.randomUUID().toString(),
    private var nombreBrigada: String,
    private var ubicacionBrigada: String,
    private var estadoBrigada: Boolean = true,
    private var comandoId: String,
    private var unidades: List<String>
) {
    // Getters
    fun getId(): String = id
    fun getNombreBrigada(): String = nombreBrigada
    fun getUbicacionBrigada(): String = ubicacionBrigada
    fun getEstadoBrigada(): Boolean = estadoBrigada
    fun getComandoId(): String = comandoId
    fun getUnidades(): List<String> = unidades.toList()

    // Setters con validación básica
    fun setNombreBrigada(value: String) {
        require(value.isNotBlank()) { "El nombre no puede estar vacío" }
        nombreBrigada = value
    }

    fun setUbicacionBrigada(value: String) {
        require(value.isNotBlank()) { "La ubicación no puede estar vacía" }
        ubicacionBrigada = value
    }

    fun setEstadoBrigada(value: Boolean) {
        estadoBrigada = value
    }

    fun setComandoId(value: String) {
        require(value.isNotBlank()) { "El ID de comando no puede estar vacío" }
        comandoId = value
    }

    fun setUnidades(value: List<String>) {
        require(value.isNotEmpty()) { "Debe haber al menos una unidad" }
        unidades = value.toList() // Almacena copia
    }
}