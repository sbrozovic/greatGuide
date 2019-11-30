package hr.fer.teslasjourney.data.storage

import android.content.Context
import android.content.SharedPreferences
import hr.fer.teslasjourney.di.AppContext
import javax.inject.Inject

class SharedPreferenceStore @Inject constructor(
    @AppContext val context: Context
) : PreferenceStore {
    companion object {
        const val PREFS_FILE_NAME = "tesla_prefs"
        const val KEY_SHOW_ONBOARDING = "KEY_SHOW_ONBOARDING"
    }

    private val sharedPrefs: SharedPreferences

    init {
        this.sharedPrefs = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE)
    }

    override var showOnboarding: Boolean
        get() = sharedPrefs.getBoolean(KEY_SHOW_ONBOARDING, true)
        set(value) {
            saveBoolean(KEY_SHOW_ONBOARDING, value)
        }

    private fun SharedPreferences.saveBooleanEventually(key: String, value: Boolean?) {
        return if (value == null) {
            this.edit().remove(key).apply()
        } else {
            this.edit().putBoolean(key, value).apply()
        }
    }

    fun saveBoolean(key: String, value: Boolean) {
        sharedPrefs.saveBooleanEventually(key, value)
    }
}