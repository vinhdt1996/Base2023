package com.example.base2023.di

import com.example.base2023.data.repository.DownloadRepository
import com.example.base2023.data.repository.impl.DownloadRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindDownloadRepository(downloadRepositoryImpl: DownloadRepositoryImpl): DownloadRepository

}