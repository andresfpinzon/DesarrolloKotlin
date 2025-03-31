package controllers.administrativo.usercontroller

import servicios.administrativo.user.UserService

class UserController(private val userService: UserService) {

    companion object {
        private fun readLine(prompt: String): String {
            print(prompt)
            return kotlin.io.readLine() ?: ""
        }

        private fun confirmarAccion(mensaje: String): Boolean {
            println("$mensaje (1: Sí, 2: No)")
            return kotlin.io.readLine() == "1"
        }
    }

    fun crearUsuario() {
        println("Ingrese los datos del usuario:")
        val nombreUsuario = readLine("Nombre de usuario: ")
        val apellidoUsuario = readLine("Apellido de usuario: ")
        val numeroDocumento = readLine("Número de documento: ")
        val correo = readLine("Correo electrónico: ")
        val password = readLine("Contraseña: ")

        // Solicitar roles
        println("Ingrese los roles (separados por comas, ejemplos: ADMIN, USER, OPERATOR):")
        val roles = readLine("Roles: ").split(",").map { it.trim() }

        if (confirmarAccion("¿Desea crear este usuario?")) {
            try {
                val nuevoUsuario = userService.createUser(
                    nombreUsuario = nombreUsuario,
                    apellidoUsuario = apellidoUsuario,
                    numeroDocumento = numeroDocumento,
                    correo = correo,
                    password = password,
                    roles = roles
                )
                println("Usuario creado con ID: ${nuevoUsuario.getId()}")
            } catch (e: IllegalArgumentException) {
                println("Error al crear usuario: ${e.message}")
            }
        } else {
            println("Operación de creación de usuario cancelada.")
        }
    }

    fun actualizarUsuario() {
        val id = readLine("Ingrese el ID del usuario a actualizar: ")

        try {
            val usuarioExistente = userService.getUserById(id.toLong())
            if (usuarioExistente == null) {
                println("Usuario no encontrado.")
                return
            }

            println("Usuario encontrado: ID: ${usuarioExistente.getId()}, Nombre: ${usuarioExistente.getNombreUsuario()}")

            // Campos opcionales para actualización
            val nombreUsuario = readLine("Nuevo nombre (dejar en blanco para no cambiar): ").takeIf { it.isNotBlank() }
            val apellidoUsuario = readLine("Nuevo apellido (dejar en blanco para no cambiar): ").takeIf { it.isNotBlank() }
            val numeroDocumento = readLine("Nuevo número de documento (dejar en blanco para no cambiar): ").takeIf { it.isNotBlank() }
            val correo = readLine("Nuevo correo electrónico (dejar en blanco para no cambiar): ").takeIf { it.isNotBlank() }
            val password = readLine("Nueva contraseña (dejar en blanco para no cambiar): ").takeIf { it.isNotBlank() }

            // Roles
            println("Roles actuales: ${usuarioExistente.getRoles().joinToString(", ")}")
            val roles = readLine("Nuevos roles (separados por comas, dejar en blanco para no cambiar): ")
                .takeIf { it.isNotBlank() }
                ?.split(",")
                ?.map { it.trim() }

            // Estado del usuario
            val estadoStr = readLine("Nuevo estado (activo/inactivo, dejar en blanco para no cambiar): ").lowercase().takeIf { it.isNotBlank() }
            val estadoUsuario = when (estadoStr) {
                "activo" -> true
                "inactivo" -> false
                else -> null
            }

            if (confirmarAccion("¿Desea actualizar este usuario?")) {
                val usuarioActualizado = userService.updateUser(
                    id = id.toLong(),
                    nombreUsuario = nombreUsuario,
                    apellidoUsuario = apellidoUsuario,
                    numeroDocumento = numeroDocumento,
                    correo = correo,
                    password = password,
                    roles = roles,
                    estadoUsuario = estadoUsuario
                )

                if (usuarioActualizado != null) {
                    println("Usuario actualizado: ID: ${usuarioActualizado.getId()}")
                } else {
                    println("No se pudo actualizar el usuario.")
                }
            } else {
                println("Operación de actualización cancelada.")
            }
        } catch (e: NumberFormatException) {
            println("Error: ID de usuario inválido.")
        } catch (e: Exception) {
            println("Error al actualizar usuario: ${e.message}")
        }
    }

    fun desactivarUsuario() {
        val id = readLine("Ingrese el ID del usuario a desactivar: ")

        if (confirmarAccion("¿Desea desactivar este usuario?")) {
            try {
                userService.deactivateUser(id.toLong())
                println("Usuario desactivado: ID: $id")
            } catch (e: NumberFormatException) {
                println("Error: ID de usuario inválido.")
            } catch (e: Exception) {
                println("Error al desactivar usuario: ${e.message}")
            }
        } else {
            println("Operación de desactivación cancelada.")
        }
    }

    fun buscarUsuarioPorId() {
        val id = readLine("Ingrese el ID del usuario a buscar: ")

        try {
            val usuario = userService.getUserById(id.toLong())
            if (usuario != null) {
                println("Detalles del usuario:")
                println("ID: ${usuario.getId()}")
                println("Nombre: ${usuario.getNombreUsuario()} ${usuario.getApellidoUsuario()}")
                println("Número de Documento: ${usuario.getNumeroDocumento()}")
                println("Correo: ${usuario.getCorreo()}")
                println("Roles: ${usuario.getRoles().joinToString(", ")}")
                println("Estado: ${if (usuario.getEstadoUsuario()) "Activo" else "Inactivo"}")
                println("Creado en: ${usuario.getCreadoEn()}")
            } else {
                println("Usuario no encontrado.")
            }
        } catch (e: NumberFormatException) {
            println("Error: ID de usuario inválido.")
        } catch (e: Exception) {
            println("Error al buscar usuario: ${e.message}")
        }
    }
}