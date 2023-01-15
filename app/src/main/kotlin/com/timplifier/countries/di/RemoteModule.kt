package com.timplifier.countries.di

import com.timplifier.countries.data.remote.NetworkClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {
    @Singleton
    private val networkClient = NetworkClient()

    @Singleton
    @Provides
    fun generateCountriesApiService() = networkClient.generateCountriesApiService()
}