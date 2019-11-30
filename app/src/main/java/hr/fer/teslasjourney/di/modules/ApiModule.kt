package hr.fer.teslasjourney.di.modules

import com.readystatesoftware.chuck.ChuckInterceptor
import dagger.Module
import dagger.Provides
import hr.fer.teslasjourney.BuildConfig
import hr.fer.teslasjourney.TeslasJourneyApp
import hr.fer.teslasjourney.data.network.ApiService
import hr.fer.teslasjourney.data.network.GsonProvider
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Singleton
    fun okHttpClient(): OkHttpClient {
        val okHttpBuilder: OkHttpClient.Builder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG || BuildConfig.FLAVOR == "staging") {
            val loggingInterceptor = HttpLoggingInterceptor(
                HttpLoggingInterceptor.Logger { s -> Timber.d(s) })
                .setLevel(HttpLoggingInterceptor.Level.BODY)
            okHttpBuilder.addInterceptor(loggingInterceptor)
            okHttpBuilder.addInterceptor(ChuckInterceptor(TeslasJourneyApp.instance))
        }

        okHttpBuilder.connectTimeout(30, TimeUnit.SECONDS)

        return okHttpBuilder.build()
    }

    @Provides
    @Singleton
    fun apiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun retrofit(okHttpClient: OkHttpClient): Retrofit {
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            // converter order matters!
            // if we don't add this ScalarsConverterFactory, strings get
            // serialized with extra quotes!
            .addConverterFactory(ScalarsConverterFactory.create())

            .addConverterFactory(GsonConverterFactory.create(GsonProvider.provide()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
        return retrofitBuilder.build()
    }
}