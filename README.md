# CellPoint

**CellPoint** is an Android application designed to visualize cellular base stations on an interactive map using **Mapbox**. It reads data from a local SQLite database and displays each station as a marker with its coordinates and identifier.

## Features

- **Local SQLite database access**
  - Retrieves coordinates (latitude, longitude) and unique IDs of base stations.
- **Map display using Mapbox**
  - Each base station is shown as a marker on the map.
- **Interactive map features**
  - Zoom and pan
  - Clickable markers with station info
- **Modern architecture**
  - Built with the MVI (Model–View–Intent) architecture for high testability and separation of concerns.

## Tech Stack

- **Language**: Kotlin / Java
- **Database**: SQLite (local)
- **Map Framework**: [Mapbox Maps SDK for Android](https://docs.mapbox.com/android/maps/)
- **Architecture**: MVI (Model-View-Intent), Clean Architecture
- **UI Toolkit**: Jetpack Compose + Material Design

## Next steps
- Marker clustering (Mapbox Annotations)
- Search and filter stations by ID or region
- Favorite or bookmarked stations
- Multi-layer map support

