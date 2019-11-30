package hr.fer.teslasjourney.ui.cities

import android.content.Context
import hr.fer.teslasjourney.ui.shared.ActivityBundleArguments
import org.jetbrains.anko.intentFor

class CitiesActivityBuilder : ActivityBundleArguments {

    override fun intent(context: Context) = context.intentFor<CitiesActivity>()
}