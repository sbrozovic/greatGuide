package hr.fer.teslasjourney.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import hr.fer.teslasjourney.R
import kotlinx.android.synthetic.main.fragment_onboarding.view.*

class OnboardingFragment : Fragment() {
    companion object {
        const val ONBOARDING_ITEM_KEY = "item"

        fun newInstance(onboardingItem: OnboardingItem): OnboardingFragment {
            val args = Bundle()
            args.putSerializable(ONBOARDING_ITEM_KEY, onboardingItem)
            return OnboardingFragment().apply { arguments = args }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_onboarding, container, false)
        (arguments?.getSerializable(ONBOARDING_ITEM_KEY) as? OnboardingItem)?.let { onboardingItem ->
            view.apply {
                onboardingTitle.setText(onboardingItem.title)
                onboardingDescription.setText(onboardingItem.description)
                onboardingImage.setImageDrawable(ContextCompat.getDrawable(inflater.context, onboardingItem.image))
            }
        }
        return view
    }
}