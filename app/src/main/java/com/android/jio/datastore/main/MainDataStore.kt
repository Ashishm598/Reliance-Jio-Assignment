package com.android.jio.datastore.main

import com.android.jio.model.MainResponse
import io.reactivex.Completable
import io.reactivex.Single

interface MainDataStore {

    fun getUserList(pageNo: Int, perPageResult: Int): Single<MainResponse>
    fun clearAndSaveUserList(mainResponse: MainResponse): Completable

}