package hr.fer.teslasjourney.ui

// Basic setup
sealed class CommonState

sealed class LoadingState : CommonState()
sealed class ErrorState : CommonState()

open class Idle : CommonState()

// Error states
class ErrorMessage(val message: String) : ErrorState()