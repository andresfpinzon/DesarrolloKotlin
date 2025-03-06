package models.administrativo

import models.administrativo.certificado.implement.CertificadoRepositoryImpl
import models.administrativo.certificado.model.Certificado
import models.administrativo.certificado.services.CertificadoService
import models.administrativo.user.implement.UsuarioRepositoryImpl
import models.administrativo.user.model.Usuario
import models.administrativo.user.services.UsuarioServices
import java.sql.Date
import java.time.LocalDate
import java.util.*

class SistemaDemo {
    companion object {
        fun ejecutarDemo(){
            println("=== Iniciando Demo del Sistema Integrado ===")

            // Inicializar repositorios
            val usuarioRepository = UsuarioRepositoryImpl()
            val certificadoRepository = CertificadoRepositoryImpl()

            // Inicializar servicios
            val usuarioService = UsuarioServices(usuarioRepository)
            val certificadoService = CertificadoService(certificadoRepository, usuarioRepository)

            // 1. Crear Usuarios
            println("\n=== Creando Usuarios ===")
            val usuario1 = crearUsuario(
                usuarioService,
                "Juan",
                "Pérez",
                "12345678A",
                "juan.perez@ejemplo.com",
                "contraseña123",
                listOf("ESTUDIANTE")
            )

            val usuario2 = crearUsuario(
                usuarioService,
                "Ana",
                "García",
                "87654321B",
                "ana.garcia@ejemplo.com",
                "password456",
                listOf("ADMIN", "PROFESOR")
            )

            // Mostrar lista de usuarios
            println("\n=== Lista de Usuarios ===")
            val usuarios = usuarioService.listarUsuarios()
            usuarios.forEach { imprimirUsuario(it) }

            // 2. Crear Certificados
            println("\n=== Creando Certificados ===")
            if (usuario1 != null && usuario2 != null) {
                // Crear certificado para usuario1 (como estudiante)
                val certificado1 = crearCertificado(
                    certificadoService,
                    usuario2.id.toString(), // emisor (profesor)
                    "CURSO-101",
                    usuario1.id.toString(), // estudiante
                    "Universidad XYZ"
                )

                if (certificado1 != null) {
                    // Mostrar certificado creado
                    println("\n=== Certificado Creado ===")
                    imprimirCertificado(certificado1)

                    // 3. Verificar certificado
                    println("\n=== Verificando Certificado ===")
                    certificadoService.verificarCertificado(certificado1.codigoVerificacion).fold(
                        onSuccess = { esValido ->
                            println("El certificado es ${if (esValido) "válido" else "inválido"}")

                            // 4. Revocar certificado
                            println("\n=== Revocando Certificado ===")
                            certificadoService.revocarCertificado(certificado1.id).fold(
                                onSuccess = { revocado ->
                                    println("Certificado revocado:")
                                    imprimirCertificado(revocado)

                                    // Verificar nuevamente
                                    println("\n=== Verificando Certificado Revocado ===")
                                    certificadoService.verificarCertificado(revocado.codigoVerificacion).fold(
                                        onSuccess = { esValidoDespuesDeRevocar ->
                                            println("El certificado es ${if (esValidoDespuesDeRevocar) "válido" else "inválido"}")
                                        },
                                        onFailure = { error ->
                                            println("Error: ${error.message}")
                                        }
                                    )
                                },
                                onFailure = { error ->
                                    println("Error: ${error.message}")
                                }
                            )
                        },
                        onFailure = { error ->
                            println("Error: ${error.message}")
                        }
                    )

                    // 5. Actualizar Usuario
                    println("\n=== Actualizando Usuario ===")
                    // Asumiendo que Usuario tiene un método para agregar roles o tiene un setter para roles
                    // Opción 1: Si Usuario es una data class:
                    val usuario1Actualizado = usuario1.copy(
                        roles = usuario1.roles + "GRADUADO"
                    )

                    // Opción 2: Alternativa si no es data class o no tiene copy
                    // val usuario1Actualizado = Usuario(
                    //     id = usuario1.id,
                    //     nombreUsuario = usuario1.nombreUsuario,
                    //     apellidoUsuario = usuario1.apellidoUsuario,
                    //     numeroDocumento = usuario1.numeroDocumento,
                    //     correo = usuario1.correo,
                    //     password = usuario1.password,
                    //     roles = usuario1.roles + "GRADUADO",
                    //     estadoUsuario = usuario1.estadoUsuario,
                    //     creadoEn = usuario1.creadoEn
                    // )

                    usuarioService.actualizarUsuario(usuario1.id, usuario1Actualizado).fold(
                        onSuccess = { actualizado ->
                            println("Usuario actualizado:")
                            imprimirUsuario(actualizado)
                        },
                        onFailure = { error ->
                            println("Error: ${error.message}")
                        }
                    )
                }
            }

            println("\n=== Demo Finalizada ===")
        }

        private fun crearUsuario(
            service: UsuarioServices,
            nombre: String,
            apellido: String,
            documento: String,
            correo: String,
            password: String,
            roles: List<String>
        ): Usuario? {
            val nuevoUsuario = Usuario(
                id = "",  // Se generará automáticamente
                nombreUsuario = nombre,
                apellidoUsuario = apellido,
                numeroDocumento = documento,
                correo = correo,
                password = password,
                roles = roles,
                estadoUsuario = true,
                creadoEn = ""  // Se asignará automáticamente
            )

            var usuarioCreado: Usuario? = null
            service.registrarUsuario(nuevoUsuario).fold(
                onSuccess = { usuario ->
                    println("Usuario creado: ${usuario.nombreUsuario} ${usuario.apellidoUsuario}")
                    usuarioCreado = usuario
                },
                onFailure = { error ->
                    println("Error al crear usuario: ${error.message}")
                }
            )

            return usuarioCreado
        }

        private fun crearCertificado(
            service: CertificadoService,
            usuarioId: String,
            cursoId: String,
            estudianteId: String,
            emisor: String
        ): Certificado? {
            val fechaActual = Date.valueOf(LocalDate.now().toString())
            val codigo = "CERT-" + UUID.randomUUID().toString().substring(0, 8)

            val nuevoCertificado = Certificado(
                id = "",  // Se generará automáticamente
                fechaEmision = fechaActual,
                usuarioId = usuarioId,
                cursoId = cursoId,
                estudianteId = estudianteId,
                nombreEmisorCertificado = emisor,
                codigoVerificacion = codigo,
                estadoCertificado = true
            )

            var certificadoCreado: Certificado? = null
            service.emitirCertificado(nuevoCertificado).fold(
                onSuccess = { certificado ->
                    println("Certificado creado con código: ${certificado.codigoVerificacion}")
                    certificadoCreado = certificado
                },
                onFailure = { error ->
                    println("Error al crear certificado: ${error.message}")
                }
            )

            return certificadoCreado
        }

        private fun imprimirUsuario(usuario: Usuario) {
            println("---------------------------------")
            println("ID: ${usuario.id}")
            println("Nombre: ${usuario.nombreUsuario} ${usuario.apellidoUsuario}")
            println("Documento: ${usuario.numeroDocumento}")
            println("Correo: ${usuario.correo}")
            println("Roles: ${usuario.roles}")
            println("Estado: ${if (usuario.estadoUsuario) "Activo" else "Inactivo"}")
            println("Creado: ${usuario.creadoEn}")
            println("---------------------------------")
        }

        private fun imprimirCertificado(certificado: Certificado) {
            println("---------------------------------")
            println("ID: ${certificado.id}")
            println("Fecha Emisión: ${certificado.fechaEmision}")
            println("Usuario ID: ${certificado.usuarioId}")
            println("Curso ID: ${certificado.cursoId}")
            println("Estudiante ID: ${certificado.estudianteId}")
            println("Emisor: ${certificado.nombreEmisorCertificado}")
            println("Código Verificación: ${certificado.codigoVerificacion}")
            println("Estado: ${if (certificado.estadoCertificado) "Válido" else "Revocado"}")
            println("---------------------------------")
        }
    }
}