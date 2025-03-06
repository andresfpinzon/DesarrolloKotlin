//package models.administrativo.certificado
//
//import models.administrativo.SistemaDemo.Companion.crearCertificado
//import models.administrativo.SistemaDemo.Companion.imprimirCertificado
//import models.administrativo.certificado.implement.CertificadoRepositoryImpl
//import models.administrativo.certificado.model.Certificado
//import models.administrativo.certificado.services.CertificadoService
//import models.administrativo.user.implement.UsuarioRepositoryImpl
//import models.administrativo.user.services.UsuarioServices
//import java.sql.Date
//import java.time.LocalDate
//import java.util.*
//
//class DemoCertificado {
//    companion object{
//
//        fun ejecutarDemoCertificado(){
//            // Inicializar repositorios
//            val usuarioRepository = UsuarioRepositoryImpl()
//            val certificadoRepository = CertificadoRepositoryImpl()
//
//            // Inicializar servicios
//            val usuarioService = UsuarioServices(usuarioRepository)
//            val certificadoService = CertificadoService(certificadoRepository, usuarioRepository)
//
//            println("\n=== Creando Certificados ===")
//            if (usuario1 != null && usuario2 != null) {
//                // Crear certificado para usuario1 (como estudiante)
//                val certificado1 = crearCertificado(
//                    certificadoService,
//                    usuario2.id.toString(), // emisor (profesor)
//                    "CURSO-101",
//                    usuario1.id.toString(), // estudiante
//                    "Universidad XYZ"
//                )
//            }
//            if (certificado1 != null) {
//                // Mostrar certificado creado
//                println("\n=== Certificado Creado ===")
//                imprimirCertificado(certificado1)
//
//                // 3. Verificar certificado
//                println("\n=== Verificando Certificado ===")
//                certificadoService.verificarCertificado(certificado1.codigoVerificacion).fold(
//                    onSuccess = { esValido ->
//                        println("El certificado es ${if (esValido) "válido" else "inválido"}")
//
//                        // 4. Revocar certificado
//                        println("\n=== Revocando Certificado ===")
//                        certificadoService.revocarCertificado(certificado1.id).fold(
//                            onSuccess = { revocado ->
//                                println("Certificado revocado:")
//                                imprimirCertificado(revocado)
//
//                                // Verificar nuevamente
//                                println("\n=== Verificando Certificado Revocado ===")
//                                certificadoService.verificarCertificado(revocado.codigoVerificacion).fold(
//                                    onSuccess = { esValidoDespuesDeRevocar ->
//                                        println("El certificado es ${if (esValidoDespuesDeRevocar) "válido" else "inválido"}")
//                                    },
//                                    onFailure = { error ->
//                                        println("Error: ${error.message}")
//                                    }
//                                )
//                            },
//                            onFailure = { error ->
//                                println("Error: ${error.message}")
//                            }
//                        )
//                    },
//                    onFailure = { error ->
//                        println("Error: ${error.message}")
//                    }
//                )
//        }
//            private fun crearCertificado(
//                service: CertificadoService,
//                usuarioId: String,
//                cursoId: String,
//                estudianteId: String,
//                emisor: String
//            ): Certificado? {
//                val fechaActual = Date.valueOf(LocalDate.now().toString())
//                val codigo = "CERT-" + UUID.randomUUID().toString().substring(0, 8)
//
//                val nuevoCertificado = Certificado(
//                    id = "",  // Se generará automáticamente
//                    fechaEmision = fechaActual,
//                    usuarioId = usuarioId,
//                    cursoId = cursoId,
//                    estudianteId = estudianteId,
//                    nombreEmisorCertificado = emisor,
//                    codigoVerificacion = codigo,
//                    estadoCertificado = true
//                )
//
//                var certificadoCreado: Certificado? = null
//                service.emitirCertificado(nuevoCertificado).fold(
//                    onSuccess = { certificado ->
//                        println("Certificado creado con código: ${certificado.codigoVerificacion}")
//                        certificadoCreado = certificado
//                    },
//                    onFailure = { error ->
//                        println("Error al crear certificado: ${error.message}")
//                    }
//                )
//
//                return certificadoCreado
//            }
//            private fun imprimirCertificado(certificado: Certificado) {
//                println("---------------------------------")
//                println("ID: ${certificado.id}")
//                println("Fecha Emisión: ${certificado.fechaEmision}")
//                println("Usuario ID: ${certificado.usuarioId}")
//                println("Curso ID: ${certificado.cursoId}")
//                println("Estudiante ID: ${certificado.estudianteId}")
//                println("Emisor: ${certificado.nombreEmisorCertificado}")
//                println("Código Verificación: ${certificado.codigoVerificacion}")
//                println("Estado: ${if (certificado.estadoCertificado) "Válido" else "Revocado"}")
//                println("---------------------------------")
//            }
//    }
//    }
//}