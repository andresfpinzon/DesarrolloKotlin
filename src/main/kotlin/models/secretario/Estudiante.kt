package models.secretario

import java.util.Date

class Estudiante(
    val id: String,
    val nombreEstudiante: String,
    val apellidoEstudiante: String,
    val correoEstudiante: String,
    val tipoDocumento: String,
    val numeroDocumento: String,
    val fechaNacimiento: Date,
    val generoEstudiante: String,
    val unidadId: String,
    val colegioId: String,
    val estadoEstudiante: Boolean,
    val ediciones: List<String>,
    val calificaciones: List<String>,
    val asistencias: List<String>,
    val certificados: List<String>
) {
    companion object {
        val estudiante1 = Estudiante(
            id = "1",
            nombreEstudiante = "Juan",
            apellidoEstudiante = "Perez",
            correoEstudiante = "juan.perez@gmail.com",
            tipoDocumento = "C.C",
            numeroDocumento = "123456789",
            fechaNacimiento = Date(2002,4,24),
            generoEstudiante = "Masculino",
            unidadId = "UN1018238",
            colegioId = "C0L12381918",
            estadoEstudiante = true,
            ediciones = listOf("2025-1"),
            calificaciones = listOf("CAL18270123"),
            asistencias = listOf("ASIS12391723-91"),
            certificados = listOf("CERT1293123801")
        )

        val estudiante2 = Estudiante(
            id = "2",
            nombreEstudiante = "Julian",
            apellidoEstudiante = "Rivera",
            correoEstudiante = "julian.rivera@gmail.com",
            tipoDocumento = "C.C",
            numeroDocumento = "123972123",
            fechaNacimiento = Date(2005,3,30),
            generoEstudiante = "Masculino",
            unidadId = "UN1018238",
            colegioId = "C0L12381918",
            estadoEstudiante = true,
            ediciones = listOf("2025-1"),
            calificaciones = listOf("CAL18270321"),
            asistencias = listOf("ASIS12391723-19"),
            certificados = listOf("CERT1293123963")
        )

        val estudiante3 = Estudiante(
            id = "2",
            nombreEstudiante = "Andres",
            apellidoEstudiante = "Pinzon",
            correoEstudiante = "andres.pinzon@gmail.com",
            tipoDocumento = "C.C",
            numeroDocumento = "123972123",
            fechaNacimiento = Date(1996,4,24),
            generoEstudiante = "Masculino",
            unidadId = "UN1018238",
            colegioId = "C0L12381918",
            estadoEstudiante = true,
            ediciones = listOf("2025-1"),
            calificaciones = listOf("CAL18270723"),
            asistencias = listOf("ASIS12391723-46"),
            certificados = listOf("CERT1293123753")
        )

    }
}
