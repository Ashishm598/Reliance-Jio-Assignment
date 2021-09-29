package com.android.jio.repository

import com.android.jio.model.MainResponse
import io.reactivex.Single

interface MainRepository {

    fun getUserList(pageNo: Int, perPageResult: Int): Single<MainResponse>

}