package hr.fer.teslasjourney.data.interactors

import hr.fer.teslasjourney.data.models.CityAndLocationId
import hr.fer.teslasjourney.data.models.LocationData
import hr.fer.teslasjourney.data.network.ApiService
import io.reactivex.Single
import javax.inject.Inject

class SingleLocationInteractor @Inject constructor(
    private val apiService: ApiService
) : Interactors.SingleLocationInteractor {

    override fun execute(req: CityAndLocationId): Single<LocationData> {
        return apiService.singleLocation(req.cityId, req.locationId)
    }
}