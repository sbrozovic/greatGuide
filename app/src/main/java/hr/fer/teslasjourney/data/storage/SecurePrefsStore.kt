package hr.fer.teslasjourney.data.storage

interface SecurePrefsStore {
    fun saveSecureString(value: String, key: String, keyStorePassword: String)
    fun getSecureString(key: String, keyStorePassword: String):String
    fun hasString(key: String): Boolean
}