package hr.fer.teslasjourney.di.modules

import android.content.Context
import android.content.res.Resources
import dagger.Module
import dagger.Provides
import hr.fer.teslasjourney.TeslasJourneyApp
import hr.fer.teslasjourney.data.storage.PreferenceStore
import hr.fer.teslasjourney.data.storage.SecurePreferenceStore
import hr.fer.teslasjourney.data.storage.SecurePrefsStore
import hr.fer.teslasjourney.data.storage.SharedPreferenceStore
import hr.fer.teslasjourney.di.AppContext
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    @AppContext
    fun appContext(): Context = TeslasJourneyApp.instance

    @Provides
    fun resources(@AppContext context: Context): Resources = context.resources

    @Provides
    @Singleton
    fun preferenceStore(preferenceStore: SharedPreferenceStore): PreferenceStore = preferenceStore

    @Provides
    @Singleton
    fun securePrefsStore(secureSharedPreferenceStore: SecurePreferenceStore): SecurePrefsStore = secureSharedPreferenceStore
}