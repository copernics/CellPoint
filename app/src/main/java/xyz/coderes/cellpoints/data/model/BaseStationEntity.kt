package xyz.coderes.cellpoints.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "cell_data", indices = [Index(value = ["LAT", "LON"])])
data class BaseStationEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID") val id: Int,
    @ColumnInfo(name = "CELLID") val cellId: Long,
    @ColumnInfo(name = "LAT") val lat: Double,
    @ColumnInfo(name = "LON") val lon: Double,
    @ColumnInfo(name = "RAT") val rat: String,
    @ColumnInfo(name = "PSC") val psc: Int,
    @ColumnInfo(name = "LAC") val lac: Long,
    @ColumnInfo(name = "MNC") val mnc: Int,
    @ColumnInfo(name = "MCC") val mcc: Int,
)