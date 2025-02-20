import models.administrativo.*
import models.instructor.*
import models.root.*
import models.secretario.*

fun main() {

    println("=== Modulo Administrativo ===")
    //Auditoria
    val auditoria = Auditoria()
    println("=== Auditoria ===")
    println("ID: ${auditoria._id}")
    println("Fecha: ${auditoria.fechaAuditoria}")
    println("Nombre del Emisor: ${auditoria.nombreEmisor}")
    println("Certificado ID: ${auditoria.certificadoId}")
    println("Estado: ${auditoria.estadoAuditoria}")
    println()

    //Brigada
    val brigada = Brigada()
    println("=== Brigada ===")
    println("ID: ${brigada._id}")
    println("Nombre: ${brigada.nombreBrigada}")
    println("Ubicación: ${brigada.ubicacionBrigada}")
    println("Estado: ${brigada.estadoBrigada}")
    println("Comando ID: ${brigada.comandoId}")
    println("Unidades: ${brigada.unidades.joinToString()}")
    println()

    //Certificado
    val certificado = Certificado()
    println("=== Colegio ===")
    println("ID: ${certificado._id}")
    println("Fecha Emision: ${certificado.fechaEmision}")
    println("Usuario: ${certificado.usuarioId}")
    println("Curso: ${certificado.cursoId}")
    println("Estudiante: ${certificado.estudianteId}")
    println("Emisor: ${certificado.nombreEmisorCertificado}")
    println("Codigo de Verificacion: ${certificado.codigoVerificacion}")
    println("Estado: ${certificado.estadoCertificado}")
    println()

    //Colegio
    val colegio = Colegio()
    println("=== Colegio ===")
    println("ID: ${colegio._id}")
    println("Nombre: ${colegio.nombreColegio}")
    println("Email: ${colegio.emailColegio}")
    println("Estado: ${colegio.estadoColegio}")
    println("Estudiantes: ${colegio.estudiantes.joinToString()}")
    println()

    //Comando
    val comando = Comando()
    println("=== Comando ===")
    println("ID: ${comando._id}")
    println("Nombre: ${comando.nombreComando}")
    println("Ubicación: ${comando.ubicacionComando}")
    println("Estado: ${comando.estadoComando}")
    println("Fundacion ID: ${comando.fundacionId}")
    println("Unidades: ${comando.brigadas.joinToString()}")
    println()

    //Unidad
    val unidad = Unidad()
    println("=== Unidad ===")
    println("ID: ${unidad._id}")
    println("Nombre: ${unidad.nombreUnidad}")
    println("Estado: ${unidad.estadoUnidad}")
    println("Brigada ID: ${unidad.brigadaId}")
    println("Usuario ID: ${unidad.usuarioId}")
    println("Estudiantes: ${unidad.estudiantes.joinToString()}")
    println()

    //Usuario
    val usuario = Usuario()
    println("=== Usuario ===")
    println("Nombre: ${usuario.nombreUsuario} ${usuario.apellidoUsuario}")
    println("Número de Documento: ${usuario.numeroDocumento}")
    println("Correo: ${usuario.correo}")
    println("contraseña: ${usuario.password}")
    println("Roles: ${usuario.roles.joinToString()}")
    println("Estado: ${usuario.estadoUsuario}")
    println("Creado en: ${usuario.creadoEn}")

    println("=== Modulo Instructor ===")

    //Asistencia
    val asistencia = Asistencia()
    println("=== Asistencia ===")
    println("ID: ${asistencia._id}")
    println("Título: ${asistencia.tituloAsistencia}")
    println("Fecha: ${asistencia.fechaAsistencia}")
    println("Usuario ID: ${asistencia.usuarioId}")
    println("Estado: ${asistencia.estadoAsistencia}")
    println("Estudiantes: ${asistencia.estudiantes.joinToString()}")
    println()

    //Calificacion
    val calificacion = Calificacion()
    println("=== Calificacion ===")
    println("ID: ${calificacion._id}")
    println("Título: ${calificacion.tituloCalificacion}")
    println("Aprobado: ${calificacion.aprobado}")
    println("Usuario ID: ${calificacion.usuarioId}")
    println("Estado: ${calificacion.estadoCalificacion}")
    println("Estudiantes: ${calificacion.estudiantes.joinToString()}")
    println()

    println("=== Modulo Root ===")
    //Fundacion
    val fundacion = Fundacion()
    println("=== Fundacion ===")
    println("ID: ${fundacion._id}")
    println("Nombre: ${fundacion.nombreFundacion}")
    println("estado: ${fundacion.estadoFundaciontrue}")
    println("comandos: ${fundacion.comando.joinToString()}")
    println()


    println("=== Modulo Secretario ===")
    //Curso
    val curso = Curso()
    println("=== Curso ===")
    println("ID: ${curso._id}")
    println("Nombre: ${curso.nombreCurso}")
    println("Descripción: ${curso.descripcionCurso}")
    println("Intensidad Horaria: ${curso.intensidadHorariaCurso}")
    println("Estado: ${curso.estadoCurso}")
    println("Fundación ID: ${curso.fundacionId}")
    println("Ediciones: ${curso.ediciones.joinToString()}")
    println()

    //Edicion
    val edicion = Edicion()
    println("=== Edicion ===")
    println("Título: ${edicion.tituloEdicion}")
    println("Fecha de Inicio: ${edicion.fechaInicioEdicion}")
    println("Fecha de Fin: ${edicion.fechaFinEdicion}")
    println("Estado: ${edicion.estadoEdicion}")
    println("Curso ID: ${edicion.cursoId}")
    println("Horarios: ${edicion.horarios.joinToString()}")
    println("Estudiantes: ${edicion.estudiantes.joinToString()}")
    println()

    //Estudiante
    val estudiante = Estudiante()
    println("=== Estudiante ===")
    println("ID: ${estudiante._id}")
    println("Nombre: ${estudiante.nombreEstudiante} ${estudiante.apellidoEstudiante}")
    println("Correo: ${estudiante.correoEstudiante}")
    println("Tipo de Documento: ${estudiante.tipoDocumento}")
    println("Número de Documento: ${estudiante.numeroDocumento}")
    println("Fecha de Nacimiento: ${estudiante.fechaNacimiento}")
    println("Género: ${estudiante.generoEstudiante}")
    println("Estado: ${estudiante.estadoEstudiante}")
    println("Unidad ID: ${estudiante.unidadId}")
    println("Colegio ID: ${estudiante.colegioId}")
    println("Ediciones: ${estudiante.ediciones.joinToString()}")
    println("Calificaciones: ${estudiante.calificaciones.joinToString()}")
    println("Asistencias: ${estudiante.asistencias.joinToString()}")
    println("Certificados: ${estudiante.certificados.joinToString()}")
    println()

    //Horario
    val horario = Horario()
    println("=== Horario ===")
    println("Título: ${horario.tituloHorario}")
    println("Hora de Inicio: ${horario.horaInicio}")
    println("Hora de Fin: ${horario.horaFin}")
    println("Estado: ${horario.estadoHorario}")
    println()

}