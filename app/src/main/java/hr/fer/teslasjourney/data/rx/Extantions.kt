package hr.fer.teslasjourney.data.rx

import hr.fer.teslasjourney.ui.BaseViewModel
import io.reactivex.Single

fun <T : Any> Single<T>.subscribe(
    viewModel: BaseViewModel<*>,
    onSuccess: (T) -> Unit,
    onErrorResponse: (code: Int, errorMessage: String) -> Boolean = { _, _ -> false }
) {
    subscribe(object : ErrorHandlingSingleObserver<T>(viewModel) {
        override fun onSuccess(t: T) {
            onSuccess.invoke(t)
        }

        //error that is returned by API response
        override fun onErrorResponse(code: Int, errorMessage: String): Boolean {
            return onErrorResponse.invoke(code, errorMessage)
        }
    })
}