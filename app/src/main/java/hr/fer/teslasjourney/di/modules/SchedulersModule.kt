package hr.fer.teslasjourney.di.modules

import dagger.Module
import dagger.Provides
import hr.fer.teslasjourney.di.Callback
import hr.fer.teslasjourney.di.ComputationThreads
import hr.fer.teslasjourney.di.IoThreads
import hr.fer.teslasjourney.di.SingleBgThread
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

@Module
class SchedulersModule {
    @Provides
    @Singleton
    @SingleBgThread
    fun singleScheduler(): Scheduler = Schedulers.single()

    @Provides
    @Singleton
    @ComputationThreads
    fun computationScheduler(): Scheduler = Schedulers.computation()

    @Provides
    @Singleton
    @IoThreads
    fun ioScheduler(): Scheduler = Schedulers.io()

    @Provides
    @Singleton
    @Callback
    fun callbackScheduler(): Scheduler = AndroidSchedulers.mainThread()
}