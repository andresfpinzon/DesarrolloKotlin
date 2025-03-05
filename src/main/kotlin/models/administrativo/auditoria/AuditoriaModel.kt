package models.administrativo.auditoria

import java.sql.Date

data class AuditoriaModel (
    val _id : String,
    val fechaAuditoria : Date,
    val nombreEmisor : String,
    val certificadoId : String,
    val estadoAuditoria : Boolean
){
    companion object{
        var auditoria1 = AuditoriaModel
    }
}