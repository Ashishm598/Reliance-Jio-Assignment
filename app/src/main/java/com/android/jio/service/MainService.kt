package com.android.jio.service

import com.android.jio.model.MainResponse
import com.android.jio.util.Constants
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MainService {

    @GET(Constants.USER_LIST)
    fun fetchUserList(
        @Query("page") pageNo: Int,
        @Query("per_page") perPageResult: Int
    ): Single<MainResponse>

}