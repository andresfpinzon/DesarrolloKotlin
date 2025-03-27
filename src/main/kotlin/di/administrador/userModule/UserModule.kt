package di.administrador.userModule

import controllers.administrativo.usercontroller.UserController
import repositories.administrador.user.UserRepository
import servicios.administrativo.user.UserService

object UserModule {
    private val userRepository = UserRepository()
    private val userService = UserService(userRepository)
    val userController = UserController(userService)
}