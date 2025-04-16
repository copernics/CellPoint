package xyz.coderes.cellpoints.presentation.ui

import xyz.coderes.cellpoints.domain.model.Bounds
import xyz.coderes.cellpoints.domain.model.Station

sealed interface StationEvent {
    data class LoadStationsInBounds(
        val bounds: Bounds,
    ) : StationEvent

    data class OnMarkerClick(val station: Station?) : StationEvent
}