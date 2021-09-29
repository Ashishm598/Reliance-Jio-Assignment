package com.android.jio.repository

import com.android.jio.datastore.main.MainDataStoreFactory
import com.android.jio.model.MainResponse
import com.android.jio.util.Constants
import io.reactivex.Single
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(private val mainDataStoreFactory: MainDataStoreFactory) :
    MainRepository {

    override fun getUserList(pageNo: Int, perPageResult: Int): Single<MainResponse> {
        if (pageNo == Constants.DEFAULT_CURRENT_PAGE) {
            return mainDataStoreFactory.getLocalDataStore().getUserList(pageNo, perPageResult)
                .flatMap { localResponse ->
                    if (localResponse.data.isEmpty()) {
                        mainDataStoreFactory.getRemoteDataStore().getUserList(pageNo, perPageResult)
                            .flatMap { remoteResponse ->
                                mainDataStoreFactory.getLocalDataStore()
                                    .clearAndSaveUserList(remoteResponse)
                                    .andThen(Single.just(remoteResponse))
                            }
                    } else {
                        Single.just(localResponse)
                    }
                }
        } else {
            return mainDataStoreFactory.getRemoteDataStore().getUserList(pageNo, perPageResult)
        }
    }
}