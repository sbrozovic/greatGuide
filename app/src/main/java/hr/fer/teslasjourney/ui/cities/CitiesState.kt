package hr.fer.teslasjourney.ui.cities

import hr.fer.teslasjourney.data.models.CitiesModel

sealed class CitiesState

class CitiesInit(val list: List<CitiesModel>) : CitiesState()