package xyz.coderes.cellpoints.presentation.ui

import xyz.coderes.cellpoints.domain.model.Point
import xyz.coderes.cellpoints.domain.model.Station

data class StationUiState(
    val stations: List<Station> = emptyList(),
    val selectedStation: Station? = null,
    val centerPoint:Point? = null
)