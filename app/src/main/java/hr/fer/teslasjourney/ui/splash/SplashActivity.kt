package hr.fer.teslasjourney.ui.splash

import android.os.Bundle
import androidx.lifecycle.Observer
import hr.fer.teslasjourney.R
import hr.fer.teslasjourney.di.ViewModelInjection
import hr.fer.teslasjourney.ui.BaseActivity
import hr.fer.teslasjourney.ui.BaseViewModel
import hr.fer.teslasjourney.ui.cities.CitiesActivityBuilder
import hr.fer.teslasjourney.ui.onboarding.OnboardingActivityBuilder
import javax.inject.Inject

class SplashActivity : BaseActivity() {
    @Inject
    @ViewModelInjection
    lateinit var viewModel: SplashViewModel

    override fun provideCommonViewModel(): BaseViewModel<*>? = viewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.firstLaunch.observe(this, Observer {
            if(it == true){
                startActivity(OnboardingActivityBuilder().intent(this))
                finish()
            } else {
                startActivity(CitiesActivityBuilder().intent(this))
                finish()
            }
        })

        viewModel.checkIfFirstLaunch()
    }
}
