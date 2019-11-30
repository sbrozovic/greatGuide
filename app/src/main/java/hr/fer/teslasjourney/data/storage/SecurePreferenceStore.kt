package hr.fer.teslasjourney.data.storage

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import hr.fer.teslasjourney.TeslasJourneyApp
import javax.inject.Inject

class SecurePreferenceStore @Inject constructor() : SecurePrefsStore {

    companion object {
        private const val SECURE_PREFERENCE = "SECURE_PREFERENCE"
        const val SECRET = "SECRET"
    }

    private lateinit var password: String

    private var securePreferences: SharedPreferences

    init {
        securePreferences = TeslasJourneyApp.instance.getSharedPreferences(SECURE_PREFERENCE, Context.MODE_PRIVATE)
    }

    override fun saveSecureString(value: String, key: String, keyStorePassword: String) {
        password = keyStorePassword
        if (securePreferences.getString(key, "") == "") {
            EncryptionServices().createMasterKey(password)
            saveString(encryptSecret(value), key)
        } else {
            editSecureString(encryptSecret(value), key)
        }
    }

    override fun getSecureString(key: String, keyStorePassword: String): String {
        password = keyStorePassword
        return decryptSecret(securePreferences.getString(key, ""))
    }

    override fun hasString(key: String): Boolean {
        return securePreferences.getString(key, "").isNotEmpty()
    }

    private fun editSecureString(value: String, key: String) {
        securePreferences.edit().remove(key).apply()
        securePreferences.edit().putString(key, value).apply()
    }

    private fun saveString(value: String, key: String) {
        securePreferences.edit().putString(key, value).apply()
    }

    private fun encryptSecret(secret: String): String {
        return EncryptionServices().encrypt(secret, password)
    }

    private fun decryptSecret(secret: String): String {
        return EncryptionServices().decrypt(secret, password)
    }
}