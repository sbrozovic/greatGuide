package hr.fer.teslasjourney.ui.city_map

import android.content.Context
import android.content.Intent
import hr.fer.teslasjourney.ui.shared.ActivityBundleArguments
import org.jetbrains.anko.intentFor

class CityMapActivityBuilder(var cityId: String) : ActivityBundleArguments {
    companion object {
        const val CITY_ID = "CITY_ID"

        fun deserializeFrom(intent: Intent?): CityMapActivityBuilder{
            return CityMapActivityBuilder(
                intent?.getStringExtra(CITY_ID) ?: ""
            )
        }
    }

    override fun intent(context: Context) = context.intentFor<CityMapActivity>(CITY_ID to cityId)
}