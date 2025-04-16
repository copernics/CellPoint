package xyz.coderes.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import xyz.coderes.cellpoints.data.local.AppDatabase
import xyz.coderes.cellpoints.data.local.StationDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "stations.db"
        ).createFromAsset("stations.db")
            .fallbackToDestructiveMigration(false)
            .build()
    }

    @Provides
    fun provideStationDao(db: AppDatabase): StationDao = db.stationDao()
}