package controllers.root

import models.root.fundacion.Fundacion
import servicios.root.fundacion.FundacionServicio
import java.util.Scanner

class FundacionController() {

    companion object {

        private val scanner = Scanner(System.`in`)

        private fun confirmarAccion(mensaje: String): Boolean {
            println("$mensaje (1: Sí, 2: No)")
            return scanner.nextLine() == "1"
        }

        fun crearFundacion(fundaciones: MutableList<Fundacion>) {
            println("Ingrese los datos de la fundacion:")
            print("ID: ")
            val id = scanner.nextLine()
            print("Nombre: ")
            val nombreFundacion = scanner.nextLine()
            print("Comandos (separados por comas): ")
            val comando = scanner.nextLine().split(",")



            if (confirmarAccion("¿Desea crear esta fundacion?")) {
                try {
                    val nuevaFundacion = FundacionServicio.crearFundacion(
                        fundaciones = fundaciones,
                        id = id,
                        nombreFundacion = nombreFundacion,
                        comando = comando

                    )
                    println("Fundacion creada: $nuevaFundacion")
                } catch (e: IllegalArgumentException) {
                    println("Error: ${e.message}")
                }
            } else {
                println("Operación cancelada.")
            }
        }

        fun listarFundacionesActivas(fundaciones: List<Fundacion>) {
            val fundacionesActivas = FundacionServicio.listarFundacionesActivas(fundaciones)
            if (fundacionesActivas.isEmpty()) {
                println("No hay fundaciones activas.")
            } else {
                println("Fundaciones  activas:")
                fundacionesActivas.forEach { println(it) }
            }
        }

        fun actualizarFundacion(fundaciones: MutableList<Fundacion>) {
            print("Ingrese el ID de la fundación  a actualizar: ")
            val id = scanner.nextLine()

            try {
                val fundación  = FundacionServicio.obtenerFundacionPorId(fundaciones, id)
                println("Fundacion  encontrada: ${fundación.nombreFundacion}")

                print("Nuevo nombre (dejar en blanco para no cambiar): ")
                val nombreFundacion = scanner.nextLine().takeIf { it.isNotBlank() }
                print("Nuevos comandos (separados por comas, dejar en blanco para no cambiar): ")
                val comando = scanner.nextLine().takeIf { it.isNotBlank() }?.split(",")



                if (confirmarAccion("¿Desea actualizar esta fundacion?")) {
                    val fundacionActualizada = FundacionServicio.actualizarFundacion(
                        fundaciones = fundaciones,
                        id = id,
                        nombreFundacion = nombreFundacion,
                        comando = comando,

                        )
                    println("Fundacion actualizada: $fundacionActualizada")
                } else {
                    println("Operación cancelada.")
                }
            } catch (e: NoSuchElementException) {
                println("Error: ${e.message}")
            }
        }

        fun desactivarFundacion(fundaciones: MutableList<Fundacion>) {
            print("Ingrese el ID de la fundacion a desactivar: ")
            val id = scanner.nextLine()

            if (confirmarAccion("¿Desea desactivar esta fundacion?")) {
                try {
                    val fundacionDesactivada = FundacionServicio.desactivarFundacion(fundaciones, id)
                    println("Fundacion  desactivada: $fundacionDesactivada")
                } catch (e: NoSuchElementException) {
                    println("Error: ${e.message}")
                }
            } else {
                println("Operación cancelada.")
            }
        }
    }
}

























