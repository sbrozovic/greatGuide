package hr.fer.teslasjourney.ui.acknowledgement

import android.content.Context
import android.content.Intent
import hr.fer.teslasjourney.ui.shared.ActivityBundleArguments
import org.jetbrains.anko.intentFor

class AcknowledgmentActivityBuilder : ActivityBundleArguments {
    override fun intent(context: Context): Intent = context.intentFor<AcknowledgementsActivity>()
}