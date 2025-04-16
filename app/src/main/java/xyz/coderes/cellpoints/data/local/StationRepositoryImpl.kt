package xyz.coderes.cellpoints.data.local

import xyz.coderes.cellpoints.domain.model.Bounds
import xyz.coderes.cellpoints.domain.model.Point
import xyz.coderes.cellpoints.domain.model.Station
import xyz.coderes.cellpoints.domain.repository.StationRepository
import xyz.coderes.cellpoints.domain.toDomain
import xyz.coderes.cellpoints.domain.toPoint
import javax.inject.Inject

class StationRepositoryImpl @Inject constructor(
    private val dao: StationDao,
) : StationRepository {
    override suspend fun getStationsInBounds(bounds: Bounds): List<Station> {
        return dao.getStationsInBounds(bounds.north, bounds.south, bounds.east, bounds.west)
            .map { it.toDomain() }
    }

    override suspend fun getAllStations(): List<Station> {
        return dao.getAllStations().map { it.toDomain() }
    }

    override suspend fun getMapCenter(): Point {
        return dao.getMapCenter().toPoint()
    }
}

