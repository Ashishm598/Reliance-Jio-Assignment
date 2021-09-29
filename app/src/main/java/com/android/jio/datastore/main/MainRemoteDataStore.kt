package com.android.jio.datastore.main

import com.android.jio.model.MainResponse
import com.android.jio.service.MainService
import io.reactivex.Completable
import io.reactivex.Single
import java.lang.UnsupportedOperationException
import javax.inject.Inject

class MainRemoteDataStore @Inject constructor(private val mainService: MainService) :
    MainDataStore {

    override fun getUserList(pageNo: Int, perPageResult: Int): Single<MainResponse> {
        return mainService.fetchUserList(pageNo, perPageResult)
    }

    override fun clearAndSaveUserList(mainResponse: MainResponse): Completable {
        return Completable.error(UnsupportedOperationException("saving in local not applicable"))
    }

}