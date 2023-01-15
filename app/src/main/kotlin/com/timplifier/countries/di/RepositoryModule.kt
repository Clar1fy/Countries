package com.timplifier.countries.di

import com.timplifier.countries.data.repositories.CountriesRepositoryImpl
import com.timplifier.countries.domain.repositories.CountriesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindCountriesRepository(countriesRepositoryImpl: CountriesRepositoryImpl): CountriesRepository
}