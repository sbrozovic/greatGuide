package hr.fer.teslasjourney.ui.onboarding

import android.content.Context
import hr.fer.teslasjourney.ui.shared.ActivityBundleArguments
import org.jetbrains.anko.intentFor

class OnboardingActivityBuilder : ActivityBundleArguments {

    override fun intent(context: Context) =
        context.intentFor<OnboardingActivity>()
}