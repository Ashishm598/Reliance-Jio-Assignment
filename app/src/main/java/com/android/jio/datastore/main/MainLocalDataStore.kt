package com.android.jio.datastore.main

import com.android.jio.cache.MainDao
import com.android.jio.model.MainResponse
import io.reactivex.Single
import javax.inject.Inject

class MainLocalDataStore @Inject constructor(private val mainDao: MainDao) : MainDataStore {

    override fun getUserList(pageNo: Int, perPageResult: Int): Single<MainResponse> {
        return mainDao.getData().flatMap {
            Single.just(MainResponse(data = it.data, page = it.page))
        }
    }
}