package com.android.jio.datastore.main

import javax.inject.Inject

class MainDataStoreFactory @Inject constructor(
    private val mainRemoteDataStore: MainRemoteDataStore,
    private val mainLocalDataStore: MainLocalDataStore
) {

    fun getLocalDataStore() = mainLocalDataStore
    fun getRemoteDataStore() = mainRemoteDataStore

}