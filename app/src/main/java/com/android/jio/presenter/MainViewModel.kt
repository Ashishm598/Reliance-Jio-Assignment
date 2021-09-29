package com.android.jio.presenter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.jio.model.MainResponse
import com.android.jio.repository.MainRepository
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

    companion object {
        private const val DEFAULT_CURRENT_PAGE = 1
        private const val DEFAULT_PER_PAGE_RESULT = 10
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    fun getUserData(
        pageNo: Int = DEFAULT_CURRENT_PAGE,
        perPageResult: Int = DEFAULT_PER_PAGE_RESULT
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
                }

                override fun onError(e: Throwable) {
                    e.log()
                }
            })

    }

}