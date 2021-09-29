package com.android.jio.datastore.main

import com.android.jio.model.MainResponse
import io.reactivex.Single

interface MainDataStore {

    fun getUserList(pageNo: Int, perPageResult: Int): Single<MainResponse>

}