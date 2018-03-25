package com.mynotes.core.di

import android.app.Application
import android.content.Context
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.mynotes.utils.ToastManager
import dagger.Module
import dagger.Provides
import org.greenrobot.eventbus.EventBus
import javax.inject.Singleton

/**
 * Created by Anurag on 25-03-2018.
 */
@Module
class AppModule(val app: Application) {
    @Provides
    @Singleton
    internal fun provideApp(): Application = app

    @Provides
    @Singleton
    internal fun provideContext(): Context = app.applicationContext

    @Provides
    @Singleton
    internal fun provideEventBus(): EventBus = EventBus.getDefault()

    @Provides
    internal fun provideObjectMapper(): ObjectMapper {
        val objectMapper = ObjectMapper().registerKotlinModule()
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY)
        return objectMapper
    }

    @Provides
    @Singleton
    internal fun provideToastManager(context: Context): ToastManager {
        return ToastManager(context)
    }
}