package hr.fer.teslasjourney.ui.onboarding

import dagger.Module
import dagger.Provides
import hr.fer.teslasjourney.di.InjectionViewModelProvider
import hr.fer.teslasjourney.di.ViewModelInjection

@Module
class OnboardingActivityModule{
    @Provides
    @ViewModelInjection
    fun provideViewModel(
        activity: OnboardingActivity,
        provider: InjectionViewModelProvider<OnboardingViewModel>
    ): OnboardingViewModel = provider.get(activity)
}