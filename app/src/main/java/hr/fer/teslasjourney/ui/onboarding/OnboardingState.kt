package hr.fer.teslasjourney.ui.onboarding

sealed class OnboardingState

class OpenCitiesScreen: OnboardingState()

class NewCurrentPage(var number: Int): OnboardingState()