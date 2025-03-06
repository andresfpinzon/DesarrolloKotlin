package models.administrativo.user

import models.administrativo.user.implement.UsuarioRepositoryImpl
import models.administrativo.user.model.Usuario
import models.administrativo.user.services.UsuarioServices

class DemoUsuario {
    companion object {
        fun ejecutarDemoUsuario() {
            val usuarioRepository = UsuarioRepositoryImpl()
            val usuarioService = UsuarioServices(usuarioRepository)

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

            // 5. Actualizar Usuario
            println("\n=== Actualizando Usuario ===")
            usuario1?.let { usuario ->
                val usuario1Actualizado = usuario.copy(
                    roles = usuario.roles + "GRADUADO"
                )

                usuarioService.actualizarUsuario(usuario.id, usuario1Actualizado).fold(
                    onSuccess = { actualizado ->
                        println("Usuario actualizado:")
                        imprimirUsuario(actualizado)
                    },
                    onFailure = { error ->
                        println("Error: ${error.message}")
                    }
                )
            } ?: println("El usuario1 es nulo, no se puede actualizar.")

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
    }
}