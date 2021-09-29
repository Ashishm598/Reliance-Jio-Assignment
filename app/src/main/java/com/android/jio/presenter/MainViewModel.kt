package com.android.jio.presenter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.jio.model.MainResponse
import com.android.jio.repository.MainRepository
import com.android.jio.util.Constants
import com.android.jio.util.SchedulerProvider
import com.android.jio.util.extension.log
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.SingleObserver
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val schedulerProvider: SchedulerProvider,
    private val mainRepository: MainRepository
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val _data = MutableLiveData<MainResponse>()
    val data = _data

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    fun getUserData(
        pageNo: Int = Constants.DEFAULT_CURRENT_PAGE,
        perPageResult: Int = Constants.DEFAULT_PER_PAGE_RESULT
    ) {
        mainRepository.getUserList(pageNo, perPageResult)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribe(object : SingleObserver<MainResponse> {
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onSuccess(reponse: MainResponse) {
                    _data.value = reponse
                    reponse.log()
                }

                override fun onError(e: Throwable) {
                    e.log()
                }
            })

    }

}