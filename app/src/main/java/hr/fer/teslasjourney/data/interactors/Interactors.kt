package hr.fer.teslasjourney.data.interactors

import hr.fer.teslasjourney.data.models.Chapter
import hr.fer.teslasjourney.data.models.CitiesModel
import hr.fer.teslasjourney.data.models.CityAndLocationId
import hr.fer.teslasjourney.data.models.LocationData
import io.reactivex.Single

interface Interactors {
    interface CitiesInteractor : BaseInteractor<Unit, Single<Map<String, CitiesModel>>>

    interface LocationsInteractor : BaseInteractor<String, Single<Map<String, LocationData>>>

    interface SingleLocationInteractor: BaseInteractor<CityAndLocationId, Single<LocationData>>

    interface ChapterInteractor : BaseInteractor<String, Single<Map<String, Chapter>>>
}