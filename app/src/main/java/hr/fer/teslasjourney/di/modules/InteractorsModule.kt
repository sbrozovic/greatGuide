package hr.fer.teslasjourney.di.modules

import dagger.Binds
import dagger.Module
import hr.fer.teslasjourney.data.interactors.*

@Module
abstract class InteractorsModule{
    @Binds
    abstract fun citiesInteractor(interactor: CitiesInteractor): Interactors.CitiesInteractor

    @Binds
    abstract fun locationsInteractor(interactor: LocationsInteractor): Interactors.LocationsInteractor

    @Binds
    abstract fun singleLocationInteractor(interactor: SingleLocationInteractor): Interactors.SingleLocationInteractor

    @Binds
    abstract fun chaptersIntractors(interactor: ChapterInteractor): Interactors.ChapterInteractor
}