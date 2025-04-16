package xyz.coderes.cellpoints.presentation.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mapbox.geojson.Point
import com.mapbox.maps.CameraChanged
import com.mapbox.maps.CameraChangedCallback
import com.mapbox.maps.CoordinateBounds
import com.mapbox.maps.MapLoaded
import com.mapbox.maps.MapLoadedCallback
import com.mapbox.maps.extension.compose.MapEffect
import com.mapbox.maps.extension.compose.MapboxMap
import com.mapbox.maps.extension.compose.animation.viewport.rememberMapViewportState
import com.mapbox.maps.extension.compose.annotation.generated.PointAnnotation
import com.mapbox.maps.extension.compose.annotation.rememberIconImage
import com.mapbox.maps.extension.compose.style.standard.MapboxStandardStyle
import com.mapbox.maps.toCameraOptions
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import xyz.coderes.cellpoints.R
import xyz.coderes.cellpoints.domain.toBounds
import xyz.coderes.cellpoints.presentation.ui.component.StationInfoCard


@Composable
fun MapScreen(
    viewModel: StationViewModel = hiltViewModel<StationViewModel>(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    var debounceJob by remember { mutableStateOf<Job?>(null) }

    val scope = rememberCoroutineScope()

    uiState.centerPoint?.let { centerPoint ->
        val mapViewportState = rememberMapViewportState {
            setCameraOptions {
                center(Point.fromLngLat(centerPoint.lng, centerPoint.lat))
                zoom(10.0)
                pitch(0.0)
                bearing(0.0)
            }
            this.mapViewportStatus
        }
        MapboxMap(
            Modifier.fillMaxSize(),
            mapViewportState = mapViewportState,
            style = { MapboxStandardStyle() },
        ) {
            RememberVisibleMapBounds { bounds ->
                debounceJob?.cancel()
                debounceJob = scope.launch {
                    delay(500) // debounce 500 mc
                    viewModel.onEvent(
                        StationEvent.LoadStationsInBounds(
                            bounds.toBounds()
                        )
                    )
                }
            }

            MapEffect(Unit) { mapView ->
                mapView.mapboxMap.subscribeMapLoaded(
                    object : MapLoadedCallback {
                        override fun run(mapLoaded: MapLoaded) {

                            val cameraBounds = mapView.mapboxMap.coordinateBoundsForCamera(
                                mapView.mapboxMap.cameraState.toCameraOptions()
                            )
                            viewModel.onEvent(
                                StationEvent.LoadStationsInBounds(
                                    cameraBounds.toBounds()
                                )
                            )
                        }
                    }
                )
            }

            var markerResourceId by remember { mutableStateOf(R.drawable.baseline_location_pin_24) }
            val marker =
                rememberIconImage(
                    key = markerResourceId,
                    painter = painterResource(markerResourceId)
                )

            uiState.stations.forEach { station ->
                PointAnnotation(
                    point = Point.fromLngLat(station.lon, station.lat),
                ) {
                    iconImage = marker
                    interactionsState.onClicked {
                        viewModel.onEvent(StationEvent.OnMarkerClick(station))
                        true
                    }
                }
            }
        }
    }

    uiState.selectedStation?.let { station ->
        StationInfoCard(station, {
            viewModel.onEvent(StationEvent.OnMarkerClick(null))
        })
    }
}

@Composable
fun RememberVisibleMapBounds(
    onBoundsChanged: (CoordinateBounds) -> Unit,
) {
    var lastBounds by remember { mutableStateOf<CoordinateBounds?>(null) }

    MapEffect { mapView ->
        val callBack =
            object : CameraChangedCallback {
                override fun run(cameraChanged: CameraChanged) {

                    val cameraBounds = mapView.mapboxMap.coordinateBoundsForCamera(
                        mapView.mapboxMap.cameraState.toCameraOptions()
                    )

                    if (cameraBounds != lastBounds) {
                        lastBounds = cameraBounds
                        onBoundsChanged(cameraBounds)
                    }
                }
            }
        mapView.mapboxMap.subscribeCameraChanged(callBack)
    }
}

