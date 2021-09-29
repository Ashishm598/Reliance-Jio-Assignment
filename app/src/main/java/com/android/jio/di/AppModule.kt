package com.android.jio.di

import android.content.Context
import androidx.room.Room
import com.android.jio.cache.MainDB
import com.android.jio.cache.MainDao
import com.android.jio.cache.converter.DataConverter
import com.android.jio.datastore.main.MainDataStore
import com.android.jio.datastore.main.MainDataStoreFactory
import com.android.jio.datastore.main.MainLocalDataStore
import com.android.jio.datastore.main.MainRemoteDataStore
import com.android.jio.repository.MainRepository
import com.android.jio.repository.MainRepositoryImpl
import com.android.jio.service.MainService
import com.android.jio.util.SchedulerProvider
import com.android.jio.util.SchedulerProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideMainDB(@ApplicationContext context: Context): MainDB {
        return Room.databaseBuilder(context, MainDB::class.java, MainDB.DB_NAME).build()
    }

    @Provides
    fun provideSchedulerProviderImpl(): SchedulerProvider {
        return SchedulerProviderImpl()
    }

    @Provides
    fun provideMainRepositoryImpl(mainDataStoreFactory: MainDataStoreFactory): MainRepository {
        return MainRepositoryImpl(mainDataStoreFactory)
    }

    @Provides
    fun provideMainDataStoreFactory(
        mainRemoteDataStore: MainRemoteDataStore,
        mainLocalDataStore: MainLocalDataStore
    ): MainDataStoreFactory {
        return MainDataStoreFactory(mainRemoteDataStore, mainLocalDataStore)
    }

    @Provides
    fun provideMainRemoteDataStore(mainService: MainService): MainDataStore {
        return MainRemoteDataStore(mainService)
    }

    @Provides
    fun provideMainLocalDataStore(mainDao: MainDao): MainDataStore {
        return MainLocalDataStore(mainDao)
    }

    @Provides
    fun provideMainService(retrofit: Retrofit): MainService {
        return retrofit.create(MainService::class.java)
    }

    @Provides
    fun provideUserDao(mainDB: MainDB): MainDao {
        return mainDB.mainDao()
    }
}