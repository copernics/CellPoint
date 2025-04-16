package xyz.coderes.cellpoints.domain

import xyz.coderes.cellpoints.domain.model.Bounds
import xyz.coderes.cellpoints.domain.model.Point
import xyz.coderes.cellpoints.domain.model.Station
import xyz.coderes.cellpoints.domain.repository.StationRepository
import javax.inject.Inject

class GetStationsUseCase @Inject constructor(
    private val repository: StationRepository,
) {
    suspend operator fun invoke(bounds: Bounds): List<Station> {
        println("GetStationsUseCase invoke $bounds")
        return repository.getStationsInBounds(bounds)
    }
}

class GetCenterPointUseCase @Inject constructor(
    private val repository: StationRepository,
) {
    suspend operator fun invoke(): Point {
        return repository.getMapCenter()
    }
}