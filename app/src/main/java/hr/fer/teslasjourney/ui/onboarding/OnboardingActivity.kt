package hr.fer.teslasjourney.ui.onboarding

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.viewpager.widget.ViewPager
import hr.fer.teslasjourney.R
import hr.fer.teslasjourney.di.ViewModelInjection
import hr.fer.teslasjourney.ui.BaseActivity
import hr.fer.teslasjourney.ui.BaseViewModel
import hr.fer.teslasjourney.ui.cities.CitiesActivityBuilder
import kotlinx.android.synthetic.main.activity_onboarding.*
import javax.inject.Inject

class OnboardingActivity : BaseActivity() {

    @Inject
    @field:ViewModelInjection
    lateinit var viewModel: OnboardingViewModel

    override fun provideCommonViewModel(): BaseViewModel<*>? = viewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        observeViewState()
        initViews()
    }

    private fun observeViewState() {
        viewModel.viewStateRender().observe(this, Observer { state ->
            when (state) {
                is OpenCitiesScreen -> openCities()
                is NewCurrentPage -> setCurrentPage(state.number)
            }
        })
    }

    private fun initViews() {
        initViewPager()
        initListeners()
        initNextButton()
    }

    private fun initNextButton(){
        onboardingNextButton.text = getString(R.string.onboarding_next)
        onboardingNextButton.setTextColor(ContextCompat.getColor(this, R.color.white))
        onboardingNextButton.setBackgroundResource(R.drawable.btn_rounded_blue)
    }

    private fun initListeners() {
        onboardingSkipButton.setOnClickListener {
            viewModel.skipOnboarding()
        }

        onboardingNextButton.setOnClickListener {
            viewModel.nextPage(currentPage(), lastPage())
        }
    }

    private fun initViewPager() {
        onboardingViewPager.adapter = OnboardingAdapter(supportFragmentManager, OnboardingItem.values().toList())

        onboardingViewPager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                setSkipButtonVisibility(position)
                setNextPageBtnText(position)
            }
        })
    }

    private fun lastPage() = onboardingViewPager.adapter?.let { it.count - 1 } ?: 0

    private fun currentPage() = onboardingViewPager.currentItem

    private fun setSkipButtonVisibility(position: Int) {
        onboardingSkipButton.visibility = if (position < lastPage()) View.VISIBLE else View.INVISIBLE
    }

    private fun setNextPageBtnText(position: Int) {
        if (position >= lastPage()) {
            onboardingNextButton.text = getString(R.string.onboarding_go)
            onboardingNextButton.setTextColor(ContextCompat.getColor(this, R.color.darkBlue))
            onboardingNextButton.setBackgroundResource(R.drawable.btn_rounded_yellow)
        } else {
            onboardingNextButton.text = getString(R.string.onboarding_next)
            onboardingNextButton.setTextColor(ContextCompat.getColor(this, R.color.white))
            onboardingNextButton.setBackgroundResource(R.drawable.btn_rounded_blue)
        }
    }

    private fun openCities() {
        startActivity(CitiesActivityBuilder().intent(this))
        finish()
    }

    private fun setCurrentPage(position: Int) {
        onboardingViewPager.currentItem = position
    }
}
