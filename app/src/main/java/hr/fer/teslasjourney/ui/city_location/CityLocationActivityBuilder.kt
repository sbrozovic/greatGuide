package hr.fer.teslasjourney.ui.city_location

import android.content.Context
import android.content.Intent
import hr.fer.teslasjourney.data.models.CityAndLocationId
import hr.fer.teslasjourney.ui.shared.ActivityBundleArguments
import org.jetbrains.anko.intentFor

class CityLocationActivityBuilder(var cityAndLocationId: CityAndLocationId) : ActivityBundleArguments {

    companion object {
        const val CITY_AND_LOCATION_ID = "CITY_AND_LOCATION_ID"

        fun deserializeFrom(intent: Intent?): CityLocationActivityBuilder {
            return CityLocationActivityBuilder(
                intent?.getSerializableExtra(CITY_AND_LOCATION_ID) as CityAndLocationId
            )
        }
    }

    override fun intent(context: Context) = context.intentFor<CityLocationActivity>(CITY_AND_LOCATION_ID to cityAndLocationId)
}