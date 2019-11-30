package hr.fer.teslasjourney.ui.city_map

import hr.fer.teslasjourney.data.models.LocationData

sealed class CityMapState

class LocationsInit(var locations: List<LocationData>): CityMapState()
