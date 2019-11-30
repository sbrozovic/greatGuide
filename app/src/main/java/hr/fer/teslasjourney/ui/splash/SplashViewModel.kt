package hr.fer.teslasjourney.ui.splash

import androidx.lifecycle.MutableLiveData
import hr.fer.teslasjourney.data.storage.PreferenceStore
import hr.fer.teslasjourney.ui.BaseViewModel
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val preferenceStore: PreferenceStore
): BaseViewModel<Any>(){
    val firstLaunch = MutableLiveData<Boolean>()

    fun checkIfFirstLaunch(){
        firstLaunch.value = preferenceStore.showOnboarding
    }
}