package models.questions

import models.secretario.estudiante.Estudiante
import models.secretario.estudiante.EstudianteController
import controllers.instructor.asistencia.AsistenciaController


import models.administrativo.comando.Comando
import models.administrativo.comando.ComandoController

import controllers.administrativo.BrigadaController

import models.administrativo.unidad.Unidad
import models.administrativo.unidad.UnidadController

import models.administrativo.colegio.Colegio
import models.administrativo.colegio.ColegioController


import controllers.instructor.calificacion.CalificacionController
import controllers.root.FundacionController
import models.instructor.calificacion.Calificacion

import java.util.Scanner

// Inyección de Dependencias
import di.instructor.asistencia.AsistenciaModule
import di.administrador.brigada.BrigadaModule
import di.administrador.userModule.UserModule
import di.administrador.userModule.UserModule.userController
import models.root.fundacion.Fundacion


class Menu {

    companion object {
        private val scanner = Scanner(System.`in`)

        fun accederServicios() {

            val asistenciaController = AsistenciaModule.asistenciaController
            val brigadaController = BrigadaModule.brigadaController
            val userController = UserModule.userController

            while (true) {
                println("Seleccione el módulo al que desea acceder:")
                println("1. Administrativo")
                println("2. Instructor")
                println("3. Root")
                println("4. Secretario")
                println("5. Salir")
                print("Opción: ")
                when (scanner.nextInt()) {
                    1 -> moduloAdministrativo(brigadaController)
                    2 -> moduloInstructor(asistenciaController)
                    3 -> moduloRoot()
                    4 -> moduloSecretario()
                    5 -> return
                    else -> println("Opción no válida, intente de nuevo.")
                }
            }
        }

        private fun moduloAdministrativo(brigadaController: BrigadaController) {
            println("Seleccione el submódulo de Administrativo:")
            println("1. Auditoria")
            println("2. Brigada")
            println("3. Certificado")
            println("4. Colegio")
            println("5. Comando")
            println("6. Unidad")
            println("7. Usuario")
            println("8. Volver")
            print("Opción: ")
            when (scanner.nextInt()) {
                1 -> crudAuditoria()
                2 -> crudBrigada(brigadaController)
                3 -> crudCertificado()
                4 -> crudColegio()
                5 -> crudComando()
                6 -> crudUnidad()
                7 -> crudUsuario()
                8 -> return
                else -> println("Opción no válida, intente de nuevo.")
            }
        }

        private fun moduloInstructor(asistenciaController: AsistenciaController) {
            println("Seleccione el submódulo de Instructor:")
            println("1. Asistencia")
            println("2. Calificación")
            println("3. Volver")
            print("Opción: ")
            when (scanner.nextInt()) {
                1 -> crudAsistencia(asistenciaController)
                2 -> crudCalificacion()
                3 -> return
                else -> println("Opción no válida, intente de nuevo.")
            }
        }

        private fun moduloRoot() {
            println("Seleccione el submódulo de Root:")
            println("1. Fundación")
            println("2. Volver")
            print("Opción: ")
            when (scanner.nextInt()) {
                1 -> crudFundacion()
                2 -> return
                else -> println("Opción no válida, intente de nuevo.")
            }
        }

        private fun moduloSecretario() {
            println("Seleccione el submódulo de Secretario:")
            println("1. Estudiante")
            println("2. Curso")
            println("3. Edición")
            println("4. Horario")
            println("5. Volver")
            print("Opción: ")
            when (scanner.nextInt()) {
                1 -> crudEstudiante()
                2 -> crudCurso()
                3 -> crudEdicion()
                4 -> crudHorario()
                5 -> return
                else -> println("Opción no válida, intente de nuevo.")
            }
        }

        private fun crudAuditoria() {
            println("Acciones CRUD para Auditoria:")
            println("1. Crear Auditoria")
            println("2. Leer Auditoria")
            println("3. Actualizar Auditoria")
            println("4. Eliminar Auditoria")
            println("5. Volver")
            print("Opción: ")
            when (scanner.nextInt()) {
                1 -> println("Crear Auditoria")
                2 -> println("Leer Auditoria")
                3 -> println("Actualizar Auditoria")
                4 -> println("Eliminar Auditoria")
                5 -> return
                else -> println("Opción no válida, intente de nuevo.")
            }
        }

        private fun crudBrigada(brigadaController: BrigadaController){
            while (true) {
                println("Acciones CRUD para Brigada:")
                println("1. Crear Brigada")
                println("2. Leer Brigadas")
                println("3. Actualizar Brigada")
                println("4. Desactivar Brigada")
                println("5. Volver")
                print("Opción: ")
                when (scanner.nextInt()) {
                    1 -> brigadaController.crearBrigada()
                    2 -> brigadaController.listarBrigadasActivas()
                    3 -> brigadaController.actualizarBrigada()
                    4 -> brigadaController.desactivarBrigada()
                    5 -> return
                    else -> println("Opción no válida, intente de nuevo.")
                }
            }
        }

        private fun crudCertificado() {
            println("Acciones CRUD para Certificado:")
            println("1. Crear Certificado")
            println("2. Leer Certificado")
            println("3. Actualizar Certificado")
            println("4. Eliminar Certificado")
            println("5. Volver")
            print("Opción: ")
            when (scanner.nextInt()) {
                1 -> println("Crear Certificado")
                2 -> println("Leer Certificado")
                3 -> println("Actualizar Certificado")
                4 -> println("Eliminar Certificado")
                5 -> return
                else -> println("Opción no válida, intente de nuevo.")
            }
        }

        private fun crudColegio() {
            var colegios = mutableListOf(Colegio.colegio1, Colegio.colegio2)
            println("Acciones CRUD para Colegio:")
            println("1. Crear Colegio")
            println("2. Leer Colegio")
            println("3. Actualizar Colegio")
            println("4. desactivar Colegio")
            println("5. Volver")
            print("Opción: ")
            when (scanner.nextInt()) {
                1 -> ColegioController.crearColegio(colegios)
                2 -> ColegioController.listarColegiosActivos(colegios)
                3 -> ColegioController.actualizarColegio(colegios)
                4 -> ColegioController.desactivarColegio(colegios)
                5 -> return
                else -> println("Opción no válida, intente de nuevo.")
            }
        }

        private fun crudComando() {
            var comandos = mutableListOf(Comando.comando1, Comando.comando2)
            println("Acciones CRUD para Comando:")
            println("1. Crear Comando")
            println("2. Leer Comando")
            println("3. Actualizar Comando")
            println("4. Eliminar Comando")
            println("5. Volver")
            print("Opción: ")
            when (scanner.nextInt()) {
                1 -> ComandoController.crearComando(comandos)
                2 -> ComandoController.listarComandosActivos(comandos)
                3 -> ComandoController.actualizarComando(comandos)
                4 -> ComandoController.desactivarComando(comandos)
                5 -> return
                else -> println("Opción no válida, intente de nuevo.")
            }
        }

        private fun crudUnidad() {
            var unidades = mutableListOf(Unidad.Unidad1, Unidad.Unidad2, Unidad.Unidad3)
            println("Acciones CRUD para Unidad:")
            println("1. Crear Unidad")
            println("2. Leer Unidad")
            println("3. Actualizar Unidad")
            println("4. Eliminar Unidad")
            println("5. Volver")
            print("Opción: ")
            when (scanner.nextInt()) {
                1 -> UnidadController.crearUnidad(unidades)
                2 -> UnidadController.listarUnidadesActivas(unidades)
                3 -> UnidadController.actualizarUnidad(unidades)
                4 -> UnidadController.desactivarUnidad(unidades)
                5 -> return
                else -> println("Opción no válida, intente de nuevo.")
            }
        }

        private fun crudUsuario() {
            println("Acciones CRUD para Usuario:")
            println("1. Crear Usuario")
            println("2. Leer Usuario")
            println("3. Actualizar Usuario")
            println("4. Eliminar Usuario")
            println("5. Volver")
            print("Opción: ")
            when (scanner.nextInt()) {
                1 -> userController.crearUsuario()
                2 -> userController.buscarUsuarioPorId()
                3 -> userController.actualizarUsuario()
                4 -> userController.desactivarUsuario()
                5 -> return
                else -> println("Opción no válida, intente de nuevo.")
            }
        }

        private fun crudAsistencia(asistenciaController: AsistenciaController) {
            while (true) {
                println("Acciones CRUD para Asistencia:")
                println("1. Crear Asistencia")
                println("2. Leer Asistencias")
                println("3. Actualizar Asistencia")
                println("4. Desactivar Asistencia")
                println("5. Volver")
                when (scanner.nextInt()) {
                    1 -> asistenciaController.crearAsistencia()
                    2 -> asistenciaController.listarAsistenciasActivas()
                    3 -> asistenciaController.actualizarAsistencia()
                    4 -> asistenciaController.desactivarAsistencia()
                    5 -> return
                }
            }
        }


        private fun crudCalificacion() {
            var calificaciones = mutableListOf(Calificacion.calificacion1, Calificacion.calificacion2)
            while (true) {
                println("Acciones CRUD para Calificación:")
                println("1. Crear Calificación")
                println("2. Leer Calificaciones")
                println("3. Actualizar Calificación")
                println("4. Desactivar Calificación")
                println("5. Volver")
                print("Opción: ")
                when (scanner.nextInt()) {
                    1 -> CalificacionController.crearCalificacion(calificaciones)
                    2 -> CalificacionController.listarCalificacionesActivas(calificaciones)
                    3 -> CalificacionController.actualizarCalificacion(calificaciones)
                    4 -> CalificacionController.desactivarCalificacion(calificaciones)
                    5 -> return
                    else -> println("Opción no válida, intente de nuevo.")
                }
            }
        }

        private fun crudFundacion() {
            val fundaciones = mutableListOf(Fundacion.fundacion1)// Lista para almacenar las fundaciones

            while (true) {
                println("\nAcciones CRUD para Fundación:")
                println("1. Crear Fundación")
                println("2. Leer Fundación")
                println("3. Actualizar Fundación")
                println("4. Eliminar Fundación")
                println("5. Volver")
                print("Opción: ")

                when (scanner.nextInt()) {  // Usamos nextInt() como en tu versión original
                    1 -> {
                        FundacionController.crearFundacion(fundaciones)
                    }
                    2 -> {
                        FundacionController.listarFundacionesActivas(fundaciones)
                    }
                    3 -> {
                        FundacionController.actualizarFundacion(fundaciones)
                    }
                    4 -> {
                        FundacionController.desactivarFundacion(fundaciones)
                    }
                    5 -> return
                    else -> println("Opción no válida, intente de nuevo.")
                }
            }
        }

        private fun crudEstudiante() {
            var estudiantes = mutableListOf(Estudiante.estudiante1, Estudiante.estudiante2, Estudiante.estudiante3)
            while (true) {
                println("Acciones CRUD para Estudiante:")
                println("1. Crear Estudiante")
                println("2. Leer Estudiantes")
                println("3. Actualizar Estudiante")
                println("4. Desactivar Estudiante")
                println("5. Volver")
                print("Opción: ")
                when (scanner.nextInt()) {
                    1 -> EstudianteController.crearEstudiante(estudiantes)
                    2 -> EstudianteController.listarEstudiantes(estudiantes)
                    3 -> EstudianteController.actualizarEstudiante(estudiantes)
                    4 -> EstudianteController.desactivarEstudiante(estudiantes)
                    5 -> return
                    else -> println("Opción no válida, intente de nuevo.")
                }
            }
        }

        private fun crudCurso() {
            println("Acciones CRUD para Curso:")
            println("1. Crear Curso")
            println("2. Leer Curso")
            println("3. Actualizar Curso")
            println("4. Eliminar Curso")
            println("5. Volver")
            print("Opción: ")
            when (scanner.nextInt()) {
                1 -> println("Crear Curso")
                2 -> println("Leer Curso")
                3 -> println("Actualizar Curso")
                4 -> println("Eliminar Curso")
                5 -> return
                else -> println("Opción no válida, intente de nuevo.")
            }
        }

        private fun crudEdicion() {
            println("Acciones CRUD para Edición:")
            println("1. Crear Edición")
            println("2. Leer Edición")
            println("3. Actualizar Edición")
            println("4. Eliminar Edición")
            println("5. Volver")
            print("Opción: ")
            when (scanner.nextInt()) {
                1 -> println("Crear Edición")
                2 -> println("Leer Edición")
                3 -> println("Actualizar Edición")
                4 -> println("Eliminar Edición")
                5 -> return
                else -> println("Opción no válida, intente de nuevo.")
            }
        }

        private fun crudHorario() {
            println("Acciones CRUD para Horario:")
            println("1. Crear Horario")
            println("2. Leer Horario")
            println("3. Actualizar Horario")
            println("4. Eliminar Horario")
            println("5. Volver")
            print("Opción: ")
            when (scanner.nextInt()) {
                1 -> println("Crear Horario")
                2 -> println("Leer Horario")
                3 -> println("Actualizar Horario")
                4 -> println("Eliminar Horario")
                5 -> return
                else -> println("Opción no válida, intente de nuevo.")
            }
        }

    }


}