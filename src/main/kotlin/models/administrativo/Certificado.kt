package models.administrativo

import java.sql.Date

class Certificado(
    val id: String,
    val fechaEmision: Date,
    val usuarioId: String,
    val cursoId: String,
    val estudianteId: String,
    val nombreEmisorCertificado: String,
    val codigoVerificacion: String,
    val estadoCertificado: Boolean
)