package hr.fer.teslasjourney.data.interactors

import hr.fer.teslasjourney.data.models.Chapter
import hr.fer.teslasjourney.data.network.ApiService
import io.reactivex.Single
import javax.inject.Inject

class ChapterInteractor @Inject constructor(
    private val apiService: ApiService
) : Interactors.ChapterInteractor {
    override fun execute(req: String): Single<Map<String, Chapter>> {
        return apiService.chapters(req)
    }
}