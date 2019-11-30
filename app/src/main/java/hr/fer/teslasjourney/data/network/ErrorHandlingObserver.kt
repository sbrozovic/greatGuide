package hr.fer.teslasjourney.data.network

import hr.fer.teslasjourney.R
import hr.fer.teslasjourney.TeslasJourneyApp

interface ErrorHandlingObserver {

    fun onServiceError(errorMessage: String)

    fun onUnknownProblem() = onServiceError(TeslasJourneyApp.instance.getString(R.string.generic_unknown_error))

    fun onNetworkProblem() = onServiceError(TeslasJourneyApp.instance.getString(R.string.generic_network_error))

    fun onErrorResponse(code: Int, errorMessage: String): Boolean = false
}