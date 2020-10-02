package com.zistus.basemvi.di

import android.content.Context
import androidx.room.Room
import com.zistus.basemvi.data.cache.room.AppDatabase
import com.zistus.basemvi.data.network.ApiService
import com.zistus.basemvi.home.di.HomeActivityModule
import com.zistus.basemvi.note.di.NoteActivityModule
import com.zistus.core.utils.LiveDataCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module(includes = [HomeActivityModule::class, NoteActivityModule::class])
object AppModule {

//    @Singleton
//    @Provides
//    fun provideTestString(): String = "This is a test injection"

    @Singleton
    @Provides
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl("https://randomapi.com/api/")
            .client(provideClient())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()
            .create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "app_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
        return httpClient.build()
    }

}