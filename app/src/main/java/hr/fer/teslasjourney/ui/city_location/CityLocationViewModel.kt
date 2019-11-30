package hr.fer.teslasjourney.ui.city_location

import hr.fer.teslasjourney.data.interactors.Interactors
import hr.fer.teslasjourney.data.models.Chapter
import hr.fer.teslasjourney.data.models.CityAndLocationId
import hr.fer.teslasjourney.data.models.LocationData
import hr.fer.teslasjourney.data.rx.subscribe
import hr.fer.teslasjourney.di.Callback
import hr.fer.teslasjourney.di.IoThreads
import hr.fer.teslasjourney.ui.BaseViewModel
import hr.fer.teslasjourney.ui.ErrorMessage
import hr.fer.teslasjourney.ui.shared.BundleArguments
import io.reactivex.Scheduler
import javax.inject.Inject

class CityLocationViewModel @Inject constructor(
    private val singleLocationInteractor: Interactors.SingleLocationInteractor,
    private val chapterInteractor: Interactors.ChapterInteractor,
    @IoThreads private val ioScheduler: Scheduler,
    @Callback private val callbackScheduler: Scheduler
) : BaseViewModel<CityLocationState>() {

    override fun init(arguments: BundleArguments?) {
        when (arguments) {
            is CityLocationActivityBuilder -> loadData(arguments.cityAndLocationId)
        }
    }

    fun loadData(cityAndLocationId: CityAndLocationId) {
        singleLocationInteractor
            .execute(cityAndLocationId)
            .subscribeOn(ioScheduler)
            .observeOn(callbackScheduler)
            .subscribe(this, onSuccess = {
                loadChapters(it, cityAndLocationId.locationId)
            },
                onErrorResponse = { code, errorMessage ->
                    commonStateLiveData.value = ErrorMessage(errorMessage)
                    true
                })
    }

    fun loadChapters(locationData: LocationData, locationId: String){
        chapterInteractor
            .execute(locationId)
            .subscribeOn(ioScheduler)
            .observeOn(callbackScheduler)
            .subscribe(this, onSuccess = { chaptersResponse ->
                var list = mutableListOf<Chapter>()
                for (item in chaptersResponse) {
                    list.add(item.value)
                }
                viewStateLiveData.value = UpdateUI(locationData, list)
            },
                onErrorResponse = { code, errorMessage ->
                    commonStateLiveData.value = ErrorMessage(errorMessage)
                    true
                })
    }
}
