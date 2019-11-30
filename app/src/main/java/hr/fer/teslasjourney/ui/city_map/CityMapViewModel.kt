package hr.fer.teslasjourney.ui.city_map

import hr.fer.teslasjourney.data.interactors.Interactors
import hr.fer.teslasjourney.data.models.CitiesModel
import hr.fer.teslasjourney.data.models.LocationData
import hr.fer.teslasjourney.data.rx.subscribe
import hr.fer.teslasjourney.di.Callback
import hr.fer.teslasjourney.di.IoThreads
import hr.fer.teslasjourney.ui.BaseViewModel
import hr.fer.teslasjourney.ui.ErrorMessage
import hr.fer.teslasjourney.ui.shared.BundleArguments
import io.reactivex.Scheduler
import javax.inject.Inject

class CityMapViewModel @Inject constructor(
    private val locationsInteractor: Interactors.LocationsInteractor,
    @IoThreads private val ioScheduler: Scheduler,
    @Callback private val callbackScheduler: Scheduler
) : BaseViewModel<CityMapState>() {

    override fun init(arguments: BundleArguments?) {
        when (arguments) {
            is CityMapActivityBuilder -> loadLocations(arguments.cityId)
        }
    }

    private fun loadLocations(cityId: String) {
        locationsInteractor
            .execute(cityId)
            .subscribeOn(ioScheduler)
            .observeOn(callbackScheduler)
            .subscribe(this, onSuccess = { locationsResponse ->
                var list = mutableListOf<LocationData>()
                for (item in locationsResponse) {
                    list.add(item.value)
                }
                viewStateLiveData.value = LocationsInit(list)

            }, onErrorResponse = { code, errorMessage ->
                commonStateLiveData.value = ErrorMessage(errorMessage)
                true
            })
    }
}