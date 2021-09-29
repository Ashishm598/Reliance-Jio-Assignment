package com.android.jio.datastore.main

import androidx.room.EmptyResultSetException
import com.android.jio.cache.MainDao
import com.android.jio.model.MainResponse
import com.android.jio.model.toResponseEntity
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject


class MainLocalDataStore @Inject constructor(private val mainDao: MainDao) : MainDataStore {

    override fun getUserList(pageNo: Int, perPageResult: Int): Single<MainResponse> {
        return mainDao.getData().flatMap {
            Single.just(MainResponse(data = it.data, page = it.page))
        }.onErrorResumeNext { error ->
            if (error is EmptyResultSetException)
                Single.just(MainResponse())
            else Single.error(error)
        }
    }

    override fun clearAndSaveUserList(mainResponse: MainResponse): Completable {
        return Completable.defer {
            mainDao.clearData()
            mainDao.saveData(mainResponse.toResponseEntity())
        }
    }
}