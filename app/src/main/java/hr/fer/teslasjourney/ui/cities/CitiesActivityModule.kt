package hr.fer.teslasjourney.ui.cities

import dagger.Module
import dagger.Provides
import hr.fer.teslasjourney.di.InjectionViewModelProvider
import hr.fer.teslasjourney.di.ViewModelInjection

@Module
class CitiesActivityModule {
    @Provides
    @ViewModelInjection
    fun provideViewModel(
        activity: CitiesActivity,
        provider: InjectionViewModelProvider<CitiesViewModel>
    ): CitiesViewModel = provider.get(activity)
}