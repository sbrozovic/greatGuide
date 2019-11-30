package hr.fer.teslasjourney.ui.city_location

import hr.fer.teslasjourney.data.models.Chapter
import hr.fer.teslasjourney.data.models.LocationData

sealed class CityLocationState

class UpdateUI(val location: LocationData, var chapters: List<Chapter>) : CityLocationState()
