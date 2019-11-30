package hr.fer.teslasjourney.di

import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import hr.fer.teslasjourney.TeslasJourneyApp
import hr.fer.teslasjourney.di.modules.ApiModule
import hr.fer.teslasjourney.di.modules.AppModule
import hr.fer.teslasjourney.di.modules.InteractorsModule
import hr.fer.teslasjourney.di.modules.SchedulersModule
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(
    modules = arrayOf(
        AndroidSupportInjectionModule::class,
        ActivityBuilder::class,
        AppModule::class,
        SchedulersModule::class,
        ApiModule::class,
        InteractorsModule::class
    )
)
interface AppComponent {
    fun inject(teslasjourney: TeslasJourneyApp)

    fun okHttpClient(): OkHttpClient

    fun retrofit(): Retrofit
}