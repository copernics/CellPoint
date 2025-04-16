package xyz.coderes.cellpoints.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import xyz.coderes.cellpoints.data.model.BaseStationEntity
import xyz.coderes.cellpoints.data.local.StationDao

@Database(entities = [BaseStationEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun stationDao(): StationDao
}