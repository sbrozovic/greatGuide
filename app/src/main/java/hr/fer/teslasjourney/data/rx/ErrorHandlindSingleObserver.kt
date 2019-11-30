package hr.fer.teslasjourney.data.rx

import hr.fer.teslasjourney.data.network.ErrorHandlingObserver
import hr.fer.teslasjourney.data.network.handleException
import hr.fer.teslasjourney.ui.BaseViewModel
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable

abstract class ErrorHandlingSingleObserver<T>(val viewModel: BaseViewModel<*>) : SingleObserver<T>, ErrorHandlingObserver {

    override fun onSubscribe(d: Disposable) {
        viewModel.compositeDisposable.add(d)
    }

    override fun onError(e: Throwable) {
        handleException(e, this)
        viewModel.clearCommonState()
    }

    override fun onServiceError(errorMessage: String) {
        viewModel.errorMessage(errorMessage)
        viewModel.clearCommonState()
    }
}