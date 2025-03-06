package models.administrativo.user.implement

import models.administrativo.user.model.Usuario
import models.administrativo.user.repository.UsuarioRepository
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class UsuarioRepositoryImpl : UsuarioRepository {

    // Simulación de db con un mapa
    private val usuarioDB = mutableMapOf<String, Usuario>()

    override fun crear(usuario: Usuario): Usuario {
        // Asignar un nuevo Id y fecha de creación actual
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val ahora = LocalDateTime.now().format(formatter)
        val newId = UUID.randomUUID().toString() // Generar un ID único

        val newUsuario = usuario.copy(
            id = newId,
            creadoEn = ahora
        )
        usuarioDB[newId] = newUsuario
        return newUsuario
    }

    override fun obtenerPorId(id: String): Usuario? {
        return usuarioDB[id]
    }

    override fun obtenerTodos(): List<Usuario> {
        return usuarioDB.values.toList()
    }

    override fun actualizar(id: String, usuario: Usuario): Usuario? {
        if (!usuarioDB.containsKey(id)) {
            return null
        }
        // Mantener el Id original y la fecha de creación
        val usuarioActual = usuarioDB[id]!! // Fuerzo que no sea null
        val usuarioActualizado = usuario.copy(
            id = id,
            creadoEn = usuarioActual.creadoEn
        )

        usuarioDB[id] = usuarioActualizado
        return usuarioActualizado
    }

    override fun eliminar(id: String): Boolean {
        return usuarioDB.remove(id) != null
    }

    override fun buscarPorCorreo(correo: String): Usuario? {
        return usuarioDB.values.find { it.correo == correo }
    }

    override fun buscarPorNumeroDocumento(numeroDocumento: String): Usuario? {
        return usuarioDB.values.find { it.numeroDocumento == numeroDocumento }
    }
}