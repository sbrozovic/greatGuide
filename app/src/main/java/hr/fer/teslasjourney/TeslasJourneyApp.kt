package hr.fer.teslasjourney

import android.app.Activity
import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import hr.fer.teslasjourney.di.AppComponent
import hr.fer.teslasjourney.di.DaggerAppComponent
import uk.co.chrisjenx.calligraphy.CalligraphyConfig
import javax.inject.Inject

class TeslasJourneyApp : Application(), HasActivityInjector {

    companion object {
        lateinit var instance: TeslasJourneyApp
    }

    lateinit var appComponent: AppComponent

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector

    override fun onCreate() {
        super.onCreate()
        instance = this

        initDagger()
        initCalligraphy()
    }

    private fun initDagger() {
        appComponent = DaggerAppComponent.create()
        appComponent.inject(this)
    }

    private fun initCalligraphy() {
        CalligraphyConfig.initDefault(
            CalligraphyConfig
                .Builder()
                .setFontAttrId(R.attr.fontPath)
                .setDefaultFontPath(getString(R.string.font_montserrat_alternates_regular))
                .build())
    }
}