package xyz.coderes.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import xyz.coderes.cellpoints.domain.repository.StationRepository
import xyz.coderes.cellpoints.data.local.StationRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindStationRepository(
        impl: StationRepositoryImpl,
    ): StationRepository
}