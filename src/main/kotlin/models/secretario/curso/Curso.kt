package models.secretario.curso

class CursoModel (
    private val id: String,
    private var nombreCurso: String,
    private var descripcionCurso: String,
    private var intensidadHorariaCurso: String,
    private var estadoCurso: Boolean = true,
    private var fundacionId: String,
    private var ediciones: List<String>
    ) {
        // Getters
        fun getId(): String = id
        fun getNombreCurso(): String = nombreCurso
        fun getDescripcionCurso(): String = descripcionCurso
        fun getIntensidadHorariaCurso(): String = intensidadHorariaCurso
        fun getEstadoCurso(): Boolean = estadoCurso
        fun getFundacionId(): String = fundacionId
        fun getEdiciones(): List<String> = ediciones

        // Setters
        fun setNombreCurso(nombre: String) {
            this.nombreCurso = nombre
        }

        fun setDescripcionCurso(descripcion: String) {
            this.descripcionCurso = descripcion
        }

        fun setIntensidadHorariaCurso(intensidad: String) {
            this.intensidadHorariaCurso = intensidad
        }

        fun setEstadoCurso(estado: Boolean) {
            this.estadoCurso = estado
        }

        fun setFundacionId(fundacion: String) {
            this.fundacionId = fundacion
        }

        fun setEdiciones(ediciones: List<String>) {
            this.ediciones = ediciones
        }
}