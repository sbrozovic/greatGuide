package hr.fer.teslasjourney.ui.onboarding

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


class OnboardingAdapter constructor(
    fragmentManager: FragmentManager,
    var items: List<OnboardingItem>
) : FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return OnboardingFragment.newInstance(items[position])
    }

    override fun getCount(): Int = items.count()
}