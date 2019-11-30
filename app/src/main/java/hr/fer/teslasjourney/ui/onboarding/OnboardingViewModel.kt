package hr.fer.teslasjourney.ui.onboarding

import hr.fer.teslasjourney.data.storage.PreferenceStore
import hr.fer.teslasjourney.ui.BaseViewModel
import javax.inject.Inject

class OnboardingViewModel @Inject constructor(
    private val preferenceStore: PreferenceStore
): BaseViewModel<OnboardingState>(){

    fun skipOnboarding(){
        viewStateLiveData.value = OpenCitiesScreen()
    }

    fun nextPage(currentPage: Int, lastPage: Int){
        if(currentPage >= lastPage){
            viewStateLiveData.value = OpenCitiesScreen()
            preferenceStore.showOnboarding = false
        } else {
            viewStateLiveData.value = NewCurrentPage(currentPage + 1)
        }
    }
}