package servicios.administrativo.user

import models.administrativo.user.model.Usuario
import repositories.administrador.user.UserRepository
import java.security.MessageDigest
import java.security.SecureRandom
import java.time.LocalDate
import java.util.Base64
import java.util.UUID

class UserService(private val usuarioRepository: UserRepository) {

    // Secure password hashing function using SHA-256 with salt
    private fun hashPassword(password: String): String {
        // Generate a secure random salt
        val salt = generateSalt()

        // Combine password and salt
        val passwordWithSalt = "$password$salt"

        // Create SHA-256 message digest
        val digest = MessageDigest.getInstance("SHA-256")
        val hashBytes = digest.digest(passwordWithSalt.toByteArray(Charsets.UTF_8))

        // Convert to Base64 for storage
        val hashedPassword = Base64.getEncoder().encodeToString(hashBytes)

        // Return salt + hashed password for verification later
        return "$salt:$hashedPassword"
    }

    // Generate a cryptographically strong random salt
    private fun generateSalt(length: Int = 16): String {
        val random = SecureRandom()
        val saltBytes = ByteArray(length)
        random.nextBytes(saltBytes)
        return Base64.getEncoder().encodeToString(saltBytes)
    }

    // Verify password during login
    fun verifyPassword(inputPassword: String, storedPassword: String): Boolean {
        // Split stored password into salt and hashed password
        val (salt, hashedPassword) = storedPassword.split(":")

        // Recreate the hash with the stored salt
        val passwordWithSalt = "$inputPassword$salt"
        val digest = MessageDigest.getInstance("SHA-256")
        val inputHashBytes = digest.digest(passwordWithSalt.toByteArray(Charsets.UTF_8))
        val inputHashedPassword = Base64.getEncoder().encodeToString(inputHashBytes)

        // Compare the recreated hash with the stored hash
        return inputHashedPassword == hashedPassword
    }

    fun createUser(
        nombreUsuario: String,
        apellidoUsuario: String,
        numeroDocumento: String,
        correo: String,
        password: String,
        roles: List<String>,
        estadoUsuario: Boolean = true
    ): Usuario {
        // Validate input parameters
        validateUserInput(
            nombreUsuario,
            apellidoUsuario,
            numeroDocumento,
            correo,
            password,
            roles
        )

        // Hash the password before storing
        val hashedPassword = hashPassword(password)

        // Create a new user object
        val newUser = Usuario(
            id = generateUniqueId(), // Generate a unique ID
            nombreUsuario = nombreUsuario,
            apellidoUsuario = apellidoUsuario,
            numeroDocumento = numeroDocumento,
            correo = correo,
            password = hashedPassword,
            roles = roles,
            estadoUsuario = estadoUsuario,
            creadoEn = LocalDate.now()
        )

        // Save the user in the repository
        usuarioRepository.crearUser(newUser)

        return newUser
    }

    // Existing methods from previous implementation...
    private fun validateUserInput(
        nombreUsuario: String,
        apellidoUsuario: String,
        numeroDocumento: String,
        correo: String,
        password: String,
        roles: List<String>
    ) {
        // Validate name
        require(nombreUsuario.isNotBlank()) { "Nombre de usuario no puede estar vacío" }
        require(apellidoUsuario.isNotBlank()) { "Apellido de usuario no puede estar vacío" }

        // Validate document number
        require(numeroDocumento.isNotBlank()) { "Número de documento no puede estar vacío" }
        require(numeroDocumento.length in 6..20) { "Número de documento debe tener entre 6 y 20 caracteres" }

        // Validate email
        require(correo.isNotBlank()) { "Correo no puede estar vacío" }
        require(isValidEmail(correo)) { "Formato de correo electrónico inválido" }

        // Validate password
        require(password.length >= 9) { "La contraseña debe tener al menos 8 caracteres" }

        // Validate roles
        require(roles.isNotEmpty()) { "Debe tener al menos un rol" }
    }

    // Basic email validation
    private fun isValidEmail(email: String): Boolean {
        val emailRegex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$")
        return email.matches(emailRegex)
    }

    // Generate a unique ID
    private fun generateUniqueId(): Long {
        return UUID.randomUUID().mostSignificantBits
    }

    // Additional methods for user management...
    fun updateUser(
        id: Long,
        nombreUsuario: String? = null,
        apellidoUsuario: String? = null,
        numeroDocumento: String? = null,
        correo: String? = null,
        password: String? = null,
        roles: List<String>? = null,
        estadoUsuario: Boolean? = null
    ): Usuario? {
        // Retrieve existing user
        val existingUser = usuarioRepository.getUserById(id) ?: return null

        // Hash new password if provided
        val hashedPassword = password?.let { hashPassword(it) }
            ?: existingUser.getPassword()

        // Create updated user object with existing or new values
        val updatedUser = Usuario(
            id = existingUser.getId(),
            nombreUsuario = nombreUsuario ?: existingUser.getNombreUsuario(),
            apellidoUsuario = apellidoUsuario ?: existingUser.getApellidoUsuario(),
            numeroDocumento = numeroDocumento ?: existingUser.getNumeroDocumento(),
            correo = correo ?: existingUser.getCorreo(),
            password = hashedPassword,
            roles = roles ?: existingUser.getRoles(),
            estadoUsuario = estadoUsuario ?: existingUser.getEstadoUsuario(),
            creadoEn = existingUser.getCreadoEn()
        )

        // Validate and update user
        validateUserInput(
            updatedUser.getNombreUsuario(),
            updatedUser.getApellidoUsuario(),
            updatedUser.getNumeroDocumento(),
            updatedUser.getCorreo(),
            password ?: updatedUser.getPassword(),
            updatedUser.getRoles()
        )

        usuarioRepository.updateUser(updatedUser)
        return updatedUser
    }

    fun getUserById(id: Long): Usuario? {
        return usuarioRepository.getUserById(id)
    }

    fun deactivateUser(id: Long) {
        usuarioRepository.deactiveStateUser(id)
    }
}