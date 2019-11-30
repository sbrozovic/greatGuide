package hr.fer.teslasjourney.ui.secret

import dagger.Module
import dagger.Provides
import hr.fer.teslasjourney.di.InjectionViewModelProvider
import hr.fer.teslasjourney.di.ViewModelInjection

@Module
class SecretActivityModule {
    @Provides
    @ViewModelInjection
    fun provideViewModel(
        activity: SecretActivity,
        provider: InjectionViewModelProvider<SecretViewModel>
    ): SecretViewModel = provider.get(activity)
}