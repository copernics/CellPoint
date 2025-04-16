package xyz.coderes.cellpoints.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import xyz.coderes.cellpoints.domain.GetCenterPointUseCase
import xyz.coderes.cellpoints.domain.GetStationsUseCase
import xyz.coderes.cellpoints.domain.model.Point
import javax.inject.Inject

@HiltViewModel
class StationViewModel @Inject constructor(
    private val getStationsUseCase: GetStationsUseCase,
    private val getMapCenterUseCase: GetCenterPointUseCase
) : ViewModel() {


    private val _uiState = MutableStateFlow(StationUiState())
    val uiState: StateFlow<StationUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.update { it.copy(centerPoint = getMapCenterUseCase.invoke()) }
        }
    }

    fun onEvent(event: StationEvent) {
        when (event) {
            is StationEvent.LoadStationsInBounds -> {
                viewModelScope.launch {
                    val stations = getStationsUseCase(event.bounds)
                    println("StationViewModel onEvent $stations")
                    _uiState.update { it.copy(stations = stations) }
                }
            }

            is StationEvent.OnMarkerClick -> {
                _uiState.update { it.copy(selectedStation = event.station) }
            }
        }
    }
}