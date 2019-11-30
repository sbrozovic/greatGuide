package hr.fer.teslasjourney.ui.splash

import dagger.Module
import dagger.Provides
import hr.fer.teslasjourney.di.InjectionViewModelProvider
import hr.fer.teslasjourney.di.ViewModelInjection

@Module
class SplashActivityModule {
    @Provides
    @ViewModelInjection
    fun provideViewModel(
        activity: SplashActivity,
        provider: InjectionViewModelProvider<SplashViewModel>
    ): SplashViewModel = provider.get(activity)
}