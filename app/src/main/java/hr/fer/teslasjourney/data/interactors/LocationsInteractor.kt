package hr.fer.teslasjourney.data.interactors

import hr.fer.teslasjourney.data.models.LocationData
import hr.fer.teslasjourney.data.network.ApiService
import io.reactivex.Single
import javax.inject.Inject

class LocationsInteractor @Inject constructor(
    private val apiService: ApiService
): Interactors.LocationsInteractor {
    override fun execute(cityId: String): Single<Map<String, LocationData>> {
        return apiService.locations(cityId)
    }
}