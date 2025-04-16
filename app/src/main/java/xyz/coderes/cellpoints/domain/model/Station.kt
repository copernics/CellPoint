package xyz.coderes.cellpoints.domain.model

data class Station(
    val lat: Double,
    val lon: Double,
    val rat: String,
    val psc: Int,
    val cellId: Long,
    val lac: Long,
    val mnc: Int,
    val mcc: Int,
)