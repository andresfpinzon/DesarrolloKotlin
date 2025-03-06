package models.administrativo.certificado.implement

import models.administrativo.certificado.model.Certificado
import models.administrativo.certificado.repository.CertificadoRepository
import java.util.UUID

class CertificadoRepositoryImpl : CertificadoRepository {

    private val certificadosDB = mutableMapOf<String, Certificado>()

    override fun crear(certificado: Certificado): Certificado {
        val id = if(certificado.id.isBlank()) UUID.randomUUID().toString() else certificado.id

        val nuevoCertificado = Certificado(
            id = id,
            fechaEmision = certificado.fechaEmision,
            usuarioId = certificado.usuarioId,
            cursoId = certificado.cursoId,
            estudianteId = certificado.estudianteId,
            nombreEmisorCertificado = certificado.nombreEmisorCertificado,
            codigoVerificacion = certificado.codigoVerificacion,
            estadoCertificado = certificado.estadoCertificado
        )
        certificadosDB[id] = nuevoCertificado
        return  nuevoCertificado
    }

    override fun obtenerPorId(id: String): Certificado? {
        return certificadosDB[id]
    }

    override fun obtenerTodos(): List<Certificado> {
        return certificadosDB.values.toList()
    }

    override fun actualizar(id: String, certificado: Certificado): Certificado? {
        if (!certificadosDB.containsKey(id)) {
            return null
        }

        val certificadoActualizado = Certificado(
            id = id,
            fechaEmision = certificado.fechaEmision,
            usuarioId = certificado.usuarioId,
            cursoId = certificado.cursoId,
            estudianteId = certificado.estudianteId,
            nombreEmisorCertificado = certificado.nombreEmisorCertificado,
            codigoVerificacion = certificado.codigoVerificacion,
            estadoCertificado = certificado.estadoCertificado
        )

        certificadosDB[id] = certificadoActualizado
        return certificadoActualizado
    }

    override fun eliminar(id: String): Boolean {
        return certificadosDB.remove(id) != null
    }

    override fun buscarPorEstudianteId(estudianteId: String): List<Certificado> {
        return certificadosDB.values.filter { it.estudianteId == estudianteId }
    }

    override fun buscarPorCursoId(cursoId: String): List<Certificado> {
        return certificadosDB.values.filter { it.cursoId == cursoId }
    }

    override fun buscarPorCodigoVerificacion(codigo: String): Certificado? {
        return certificadosDB.values.find { it.codigoVerificacion == codigo }
    }
}