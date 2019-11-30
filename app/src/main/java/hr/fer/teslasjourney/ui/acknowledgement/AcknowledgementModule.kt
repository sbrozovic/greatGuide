package hr.fer.teslasjourney.ui.acknowledgement

import dagger.Module
import dagger.Provides
import hr.fer.teslasjourney.di.InjectionViewModelProvider
import hr.fer.teslasjourney.di.ViewModelInjection

@Module
class AcknowledgementModule {
    @Provides
    @ViewModelInjection
    fun provideViewModel(
        activity: AcknowledgementsActivity,
        provider: InjectionViewModelProvider<AcknowledgementViewModel>
    ): AcknowledgementViewModel = provider.get(activity)
}