package hr.fer.teslasjourney.ui.cities

import hr.fer.teslasjourney.data.interactors.Interactors
import hr.fer.teslasjourney.data.models.CitiesModel
import hr.fer.teslasjourney.data.rx.subscribe
import hr.fer.teslasjourney.di.Callback
import hr.fer.teslasjourney.di.IoThreads
import hr.fer.teslasjourney.ui.BaseViewModel
import hr.fer.teslasjourney.ui.ErrorMessage
import io.reactivex.Scheduler
import javax.inject.Inject

class CitiesViewModel @Inject constructor(
    private val citiesInteractor: Interactors.CitiesInteractor,
    @IoThreads private val ioScheduler: Scheduler,
    @Callback private val callbackScheduler: Scheduler
) : BaseViewModel<CitiesState>() {

    override fun started() {
        loadCities()
    }

    private fun loadCities() {
        citiesInteractor
            .execute(Unit)
            .subscribeOn(ioScheduler)
            .observeOn(callbackScheduler)
            .subscribe(this, onSuccess = { citiesResponse ->
                var list = mutableListOf<CitiesModel>()
                for(item in citiesResponse){
                    list.add(item.value)
                }
                viewStateLiveData.value = CitiesInit(list.sortedBy { it.position.toInt() })
            }, onErrorResponse = { code, errorMessage ->
                commonStateLiveData.value = ErrorMessage(errorMessage)
                true
            })
    }
}