package hr.fer.teslasjourney.data.interactors

import hr.fer.teslasjourney.data.models.CitiesModel
import hr.fer.teslasjourney.data.network.ApiService
import io.reactivex.Single
import javax.inject.Inject

class CitiesInteractor @Inject constructor(
    private val apiService: ApiService
) : Interactors.CitiesInteractor {
    override fun execute(req: Unit): Single<Map<String, CitiesModel>> {
        return apiService.cities()
    }
}