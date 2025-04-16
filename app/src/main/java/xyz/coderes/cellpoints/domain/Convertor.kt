package xyz.coderes.cellpoints.domain

import com.mapbox.maps.CoordinateBounds
import xyz.coderes.cellpoints.data.local.CenterPoint
import xyz.coderes.cellpoints.data.model.BaseStationEntity
import xyz.coderes.cellpoints.domain.model.Bounds
import xyz.coderes.cellpoints.domain.model.Point
import xyz.coderes.cellpoints.domain.model.Station


fun CoordinateBounds.toBounds(): Bounds {
    return Bounds(
        north = this.northeast.latitude(),
        south = this.southwest.latitude(),
        east = this.northeast.longitude(),
        west = this.southwest.longitude()
    )
}

fun Double.fromDegreesToRadians(): Double = this / 180.0 * Math.PI
fun Double.fromRadiansToDegrees(): Double = this / Math.PI * 180


internal fun BaseStationEntity.toDomain(): Station = Station(
    lac = lac,
    lat = lat,
    lon = lon,
    mcc = mcc,
    mnc = mnc,
    psc = psc,
    rat = rat,
    cellId = cellId
)

internal fun CenterPoint.toPoint() = Point(centerLat, centerLng)
