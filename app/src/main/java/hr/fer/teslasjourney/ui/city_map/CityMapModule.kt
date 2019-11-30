package hr.fer.teslasjourney.ui.city_map

import dagger.Module
import dagger.Provides
import hr.fer.teslasjourney.di.InjectionViewModelProvider
import hr.fer.teslasjourney.di.ViewModelInjection

@Module
class CityMapModule {
    @Provides
    @ViewModelInjection
    fun provideViewModel(
        activity: CityMapActivity,
        provider: InjectionViewModelProvider<CityMapViewModel>
    ): CityMapViewModel = provider.get(activity)
}