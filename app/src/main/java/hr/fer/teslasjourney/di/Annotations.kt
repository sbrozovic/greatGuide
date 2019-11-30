package hr.fer.teslasjourney.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class AppContext

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Callback

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class IoThreads

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ComputationThreads

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class SingleBgThread

@Qualifier
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD, AnnotationTarget.FUNCTION)
annotation class ViewModelInjection