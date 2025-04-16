package xyz.coderes.cellpoints.data.local

import androidx.room.Dao
import androidx.room.Query
import xyz.coderes.cellpoints.data.model.BaseStationEntity

@Dao
interface StationDao {
    @Query("SELECT * FROM cell_data LIMIT :limit")
    suspend fun getAllStations(limit: Int = 1000): List<BaseStationEntity>

    @Query("SELECT * FROM cell_data WHERE LAT BETWEEN :south AND :north AND LON BETWEEN :west AND :east LIMIT :limit")
    suspend fun getStationsInBounds(
        north: Double,
        south: Double,
        east: Double,
        west: Double,
        limit: Int = 30,
    ): List<BaseStationEntity>

    @Query("SELECT AVG(LAT) AS centerLat, AVG(LON) AS centerLng FROM cell_data")
    suspend fun getMapCenter(): CenterPoint
}