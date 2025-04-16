package xyz.coderes.cellpoints.domain.repository

import xyz.coderes.cellpoints.domain.model.Bounds
import xyz.coderes.cellpoints.domain.model.Point
import xyz.coderes.cellpoints.domain.model.Station

interface StationRepository {
    suspend fun getAllStations(): List<Station>
    suspend fun getStationsInBounds(bounds: Bounds): List<Station>
    suspend fun getMapCenter(): Point
}