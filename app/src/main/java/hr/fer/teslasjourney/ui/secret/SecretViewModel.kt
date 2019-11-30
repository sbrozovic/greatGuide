package hr.fer.teslasjourney.ui.secret

import android.os.Build
import hr.fer.teslasjourney.data.storage.SecurePreferenceStore
import hr.fer.teslasjourney.ui.BaseViewModel
import hr.fer.teslasjourney.ui.shared.BundleArguments
import javax.inject.Inject

class SecretViewModel @Inject constructor(
    private val securePreferenceStore: SecurePreferenceStore
) : BaseViewModel<SecretState>() {

    override fun init(arguments: BundleArguments?) {
        viewStateLiveData.value = UpdateUI(securePreferenceStore.hasString(SecurePreferenceStore.SECRET))
    }

    fun getSecret(password: String) {
        val secret = securePreferenceStore.getSecureString(SecurePreferenceStore.SECRET, password)
        viewStateLiveData.value = ShowSecret(secret)
    }

    fun saveSecret(secret: String, password: String) {
        securePreferenceStore.saveSecureString(secret, SecurePreferenceStore.SECRET, password)
    }

    fun onSaveClicked(secret: String) {
        if (secret.isNotEmpty()) viewStateLiveData.value = ShowAlert()
    }

    fun onYesClicked(secret: String, message: String = "") {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            saveSecret(secret, "")
        } else {
            viewStateLiveData.value = ShowPasswordSaveSecretAlert(message)
        }
    }

    fun onViewSecretClicked() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getSecret("")
        } else {
            viewStateLiveData.value = ShowPasswordGetSecretAlert()
        }
    }
}