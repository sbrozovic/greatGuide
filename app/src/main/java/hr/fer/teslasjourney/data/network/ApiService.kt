package hr.fer.teslasjourney.data.network

import hr.fer.teslasjourney.data.models.Chapter
import hr.fer.teslasjourney.data.models.CitiesModel
import hr.fer.teslasjourney.data.models.LocationData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

const val TESLA_PATH = "/persons/Tesla"

interface ApiService {

    @GET("$TESLA_PATH/cities.json")
    fun cities(): Single<Map<String, CitiesModel>>

    @GET("$TESLA_PATH/locations/{cityId}.json")
    fun locations(@Path(value = "cityId") string: String): Single<Map<String, LocationData>>

    @GET("$TESLA_PATH/locations/{cityId}/{locationId}.json")
    fun singleLocation(@Path(value = "cityId") cityId: String, @Path(value = "locationId") locationId: String): Single<LocationData>

    @GET("$TESLA_PATH/chapters/{locationId}.json")
    fun chapters(@Path(value = "locationId") string: String): Single<Map<String, Chapter>>
}