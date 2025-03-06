package models.secretario.curso

class ModeloCurso(
    val id: String,
    val nombreCurso: String,
    val descripcionCurso: String,
    val intensidadHorariaCurso: String,
    val estadoCurso: Boolean,
    val fundacionId: String,
    val ediciones: List<String>
){

    val curso1 = ModeloCurso(
        id = "curso1",
        nombreCurso = "Primeros Auxilios Básicos",
        descripcionCurso = "Curso introductorio sobre primeros auxilios en situaciones de emergencia.",
        intensidadHorariaCurso = "30 horas",
        estadoCurso = true,
        fundacionId = "F001",
        ediciones = listOf("E101", "E102")
    )

    val curso2 = ModeloCurso(
        id = "curso2",
        nombreCurso = "Reanimación Cardiopulmonar (RCP) y Uso del DEA",
        descripcionCurso = "Técnicas de RCP y uso del desfibrilador externo automático (DEA).",
        intensidadHorariaCurso = "20 horas",
        estadoCurso = true,
        fundacionId = "F002",
        ediciones = listOf("E201", "E202")
    )

    val curso3 = ModeloCurso(
        id = "curso3",
        nombreCurso = "Atención de Emergencias y Traumatismos",
        descripcionCurso = "Manejo de heridas, fracturas y quemaduras en situaciones de emergencia.",
        intensidadHorariaCurso = "40 horas",
        estadoCurso = true,
        fundacionId = "F003",
        ediciones = listOf("E301", "E302")
    )

}