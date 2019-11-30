package hr.fer.teslasjourney.ui.city_location

import dagger.Module
import dagger.Provides
import hr.fer.teslasjourney.di.InjectionViewModelProvider
import hr.fer.teslasjourney.di.ViewModelInjection

@Module
class CityLocationModule {
    @Provides
    @ViewModelInjection
    fun provideViewModel(
        activity: CityLocationActivity,
        provider: InjectionViewModelProvider<CityLocationViewModel>
    ): CityLocationViewModel = provider.get(activity)
}