package hr.fer.teslasjourney.ui.secret

import android.content.Context
import hr.fer.teslasjourney.ui.shared.ActivityBundleArguments
import org.jetbrains.anko.intentFor

class SecretActivityBuilder : ActivityBundleArguments {

    override fun intent(context: Context) = context.intentFor<SecretActivity>()
}