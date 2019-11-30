package hr.fer.teslasjourney.ui.onboarding

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import hr.fer.teslasjourney.R

enum class OnboardingItem constructor(@StringRes var title: Int, @StringRes var description: Int, @DrawableRes var image: Int){
    WELCOME(R.string.welcome_to_guide, R.string.welcome_description, R.drawable.ic_onboarding_01),
    TRAVEL(R.string.travel_through_timeline, R.string.travel_description, R.drawable.ic_onboarding_02),
    EXPLORE(R.string.explore_the_city, R.string.explore_description, R.drawable.ic_onboarding_03_karta)
}