package com.android.jio.repository

import com.android.jio.datastore.main.MainDataStoreFactory
import com.android.jio.model.MainResponse
import io.reactivex.Single
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(private val mainDataStoreFactory: MainDataStoreFactory) :
    MainRepository {

    override fun getUserList(pageNo: Int, perPageResult: Int): Single<MainResponse> {
        return mainDataStoreFactory.getRemoteDataStore().getUserList(pageNo, perPageResult)
    }

    /*
       *  getRecordFromDB // fetch page 1
       *   if empty
       *        Request from API
       *           if success
       *               cache in DB
       *                   if success
       *                       Display on UI
       *       else
       *          Display from UI
       * */
}