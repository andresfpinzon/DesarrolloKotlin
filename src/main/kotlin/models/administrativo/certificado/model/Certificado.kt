package models.administrativo.certificado.model

import java.sql.Date


data class Certificado(
    val id: String,
    val fechaEmision: Date,
    val usuarioId: String,
    val cursoId: String,
    val estudianteId: String,
    val nombreEmisorCertificado: String,
    val codigoVerificacion: String,
    val estadoCertificado: Boolean
)
