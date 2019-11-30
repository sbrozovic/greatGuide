package hr.fer.teslasjourney.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import hr.fer.teslasjourney.ui.acknowledgement.AcknowledgementModule
import hr.fer.teslasjourney.ui.acknowledgement.AcknowledgementsActivity
import hr.fer.teslasjourney.ui.cities.CitiesActivity
import hr.fer.teslasjourney.ui.cities.CitiesActivityModule
import hr.fer.teslasjourney.ui.city_location.CityLocationActivity
import hr.fer.teslasjourney.ui.city_location.CityLocationModule
import hr.fer.teslasjourney.ui.city_map.CityMapActivity
import hr.fer.teslasjourney.ui.city_map.CityMapModule
import hr.fer.teslasjourney.ui.onboarding.OnboardingActivity
import hr.fer.teslasjourney.ui.onboarding.OnboardingActivityModule
import hr.fer.teslasjourney.ui.secret.SecretActivity
import hr.fer.teslasjourney.ui.secret.SecretActivityModule
import hr.fer.teslasjourney.ui.splash.SplashActivity
import hr.fer.teslasjourney.ui.splash.SplashActivityModule

@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = [SplashActivityModule::class])
    abstract fun bindSplashActivity(): SplashActivity

    @ContributesAndroidInjector(modules = [CitiesActivityModule::class])
    abstract fun bindCitiesActivity(): CitiesActivity

    @ContributesAndroidInjector(modules = [OnboardingActivityModule::class])
    abstract fun bindOndoardingActivity(): OnboardingActivity

    @ContributesAndroidInjector(modules = [CityMapModule::class])
    abstract fun bindCityMapActivity(): CityMapActivity

    @ContributesAndroidInjector(modules = [CityLocationModule::class])
    abstract fun bindCityLocationActivity(): CityLocationActivity

    @ContributesAndroidInjector(modules = [SecretActivityModule::class])
    abstract fun bingSecretActivity(): SecretActivity

    @ContributesAndroidInjector(modules = [AcknowledgementModule::class])
    abstract fun bindAcknowledgmentActivity(): AcknowledgementsActivity
}