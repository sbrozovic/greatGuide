package hr.fer.teslasjourney.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hr.fer.teslasjourney.ui.shared.BundleArguments
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel<State> : ViewModel() {
    val compositeDisposable = CompositeDisposable()
    val subscriptionDisposable = CompositeDisposable()
    protected val viewStateLiveData: MutableLiveData<State> = MutableLiveData()
    protected val commonStateLiveData: MutableLiveData<CommonState> = MutableLiveData()

    fun start(arguments: BundleArguments? = null) {
        if (viewStateLiveData.value == null) {
            init(arguments)
        }
        subscriptionDisposable.clear()
        started()
    }

    fun viewStateRender(): LiveData<State> = viewStateLiveData

    fun commonStateRender(): LiveData<CommonState> = commonStateLiveData

    fun clearCommonState() {
        commonStateLiveData.value = Idle()
    }

    open fun init(arguments: BundleArguments?) {}

    protected open fun started() {}

    override fun onCleared() {
        compositeDisposable.clear()
    }

    fun errorMessage(message: String) {
        commonStateLiveData.value = ErrorMessage(message)
    }
}