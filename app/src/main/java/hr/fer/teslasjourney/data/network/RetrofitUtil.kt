package hr.fer.teslasjourney.data.network

import hr.fer.teslasjourney.TeslasJourneyApp
import hr.fer.teslasjourney.data.models.ErrorResponse
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Retrofit
import timber.log.Timber
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

fun handleException(t: Throwable, errorListener: ErrorHandlingObserver) {
    try {
        when (t) {
            is HttpException -> {
                val retrofit = TeslasJourneyApp.instance.appComponent.retrofit()
                val error = extractErrorMessage(t, retrofit)

                // Try custom error handle
                if (error != null && errorListener.onErrorResponse(t.code(), error.errorMessage)) {
                    return
                }

                if (error != null) {
                    Timber.e(error.toString())
                    errorListener.onServiceError(error.errorMessage)
                } else {
                    Timber.e(t)
                    errorListener.onUnknownProblem()
                }
            }
            is SocketTimeoutException, is UnknownHostException -> {
                Timber.w(t)
                errorListener.onNetworkProblem()
            }
            else -> {
                Timber.e(t)
                errorListener.onUnknownProblem()
            }
        }
    } catch (secondThrowable: Throwable) {
        Timber.e(secondThrowable)
        errorListener.onUnknownProblem()
    }
}

@Throws(IOException::class)
private fun extractErrorMessage(httpException: HttpException, retrofit: Retrofit): ErrorResponse? {
    val errorBody = httpException.response().errorBody()
    return if (errorBody != null) {
        getErrorBodyAs(errorBody, retrofit, ErrorResponse::class.java)
    } else {
        null
    }
}

@Throws(IOException::class)
private fun <ErrorModel> getErrorBodyAs(
    errorBody: ResponseBody,
    retrofit: Retrofit,
    type: Class<ErrorModel>
): ErrorModel? {
    val converter = retrofit.responseBodyConverter<ErrorModel>(type, emptyArray())
    return converter.convert(errorBody)
}