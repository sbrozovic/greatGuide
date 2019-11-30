package hr.fer.teslasjourney.ui.secret

sealed class SecretState

class UpdateUI(val hasSecret: Boolean): SecretState()

class ShowSecret(val secret: String): SecretState()

class ShowAlert: SecretState()

class ShowPasswordSaveSecretAlert(val message: String): SecretState()

class ShowPasswordGetSecretAlert: SecretState()