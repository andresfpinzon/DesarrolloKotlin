package repositories.administrador.user

import models.administrativo.user.model.Usuario
import models.administrativo.user.model.UsuarioTable
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

class UserRepository {
    fun crearUser(data: Usuario){
        transaction {
            UsuarioTable.insert {
                it[UsuarioTable.nombreUsuario] = data.getNombreUsuario()
                it[UsuarioTable.apellidoUsuario] = data.getApellidoUsuario()
                it[UsuarioTable.numeroDocumento] = data.getNumeroDocumento()
                it[UsuarioTable.correo] = data.getCorreo()
                it[UsuarioTable.password] = data.getPassword()
                it[UsuarioTable.roles] = data.getRoles().joinToString(",")// Convertir List<String> a Array<String>
                it[UsuarioTable.estadoUsuario] = data.getEstadoUsuario()
                it[UsuarioTable.creadoEn] = data.getCreadoEn()
            }
        }
    }

    fun getUsers():List<Usuario>{
        return transaction{
            UsuarioTable.select{ UsuarioTable.estadoUsuario eq true }
                .map { rowToUser(it) }

        }
    }

    fun getUserById(id: Long): Usuario?{
        return transaction {
            UsuarioTable.select{UsuarioTable.id eq id}
                .mapNotNull { rowToUser(it) }
                .singleOrNull()
        }
    }

    fun updateUser(data: Usuario){
        transaction {
            UsuarioTable.update({UsuarioTable.id eq data.getId()}){
                it[nombreUsuario] = data.getNombreUsuario()
                it[apellidoUsuario] = data.getApellidoUsuario()
                it[numeroDocumento] = data.getNumeroDocumento()
                it[correo] = data.getCorreo()
                it[password] = data.getPassword()
                it[roles] = data.getRoles().joinToString(",")
                it[estadoUsuario] = data.getEstadoUsuario()
            }
        }
    }

    fun deactiveStateUser(id: Long){
        transaction { UsuarioTable.update({
            UsuarioTable.id eq id
        }){
            it[estadoUsuario] = false
        } }
    }

    private fun rowToUser(row:ResultRow): Usuario{
        return  Usuario(
            id = row[UsuarioTable.id],
            nombreUsuario = row[UsuarioTable.nombreUsuario],
            apellidoUsuario = row[UsuarioTable.apellidoUsuario],
            numeroDocumento = row[UsuarioTable.numeroDocumento],
            correo = row[UsuarioTable.correo],
            password = row[UsuarioTable.password],
            roles = row[UsuarioTable.roles].split(","),
            estadoUsuario = row[UsuarioTable.estadoUsuario],
            creadoEn = row[UsuarioTable.creadoEn]

        )
    }
}