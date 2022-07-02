package com.example.reacaodeapoio.di

import android.content.Context
import com.example.reacaodeapoio.features.home.data.HomeRepositoryImpl
import com.example.reacaodeapoio.features.home.domain.HomeRepository
import com.example.reacaodeapoio.features.home.domain.useCases.HomeUseCases
import com.example.reacaodeapoio.features.home.domain.useCases.cases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.time.format.DateTimeFormatter
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesHomeRepository(
        resultFormatter: ResultFormatter
    ): HomeRepository = HomeRepositoryImpl(resultFormatter)

    @Provides
    @Singleton
    fun providesDownloadReport(
        @ApplicationContext context: Context,
        homeRepository: HomeRepository
    ) = DownloadReport(
        context = context,
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss-SSS"),
        homeRepository = homeRepository
    )

    @Provides
    @Singleton
    fun providesHomeUseCases(
        calculateForceReactionA: CalculateForceReactionA,
        calculateForceReactionB: CalculateForceReactionB,
        isValidText: IsValidFloatText,
        resultFormatter: ResultFormatter,
        downloadReport: DownloadReport
    ) = HomeUseCases(
        calculateForceReactionA = calculateForceReactionA,
        calculateForceReactionB = calculateForceReactionB,
        isValidText = isValidText,
        resultFormatter = resultFormatter,
        downloadReport = downloadReport
    )
}