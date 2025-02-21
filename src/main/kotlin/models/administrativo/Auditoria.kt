package models.administrativo

import java.sql.Date

class Auditoria (
    val _id : String,
    val fechaAuditoria : Date,
    val nombreEmisor : String,
    val certificadoId : String,
    val estadoAuditoria : Boolean
)