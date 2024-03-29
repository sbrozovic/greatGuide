package hr.fer.teslasjourney.data.storage

import android.util.Base64
import java.security.Key
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec

class CipherWrapper(transformation: String) {

    companion object {
        /**
         * For default created symmetric keys
         */
        var TRANSFORMATION_SYMMETRIC = "AES/CBC/PKCS7Padding"

        val IV_SEPARATOR = "]"
    }

    val cipher: Cipher = Cipher.getInstance(transformation)

    /**
     * Encrypts data using the key.
     *
     * @param data the data to encrypt
     * @param key the key to encrypt data with
     * @param useInitializationVector encrypt data using initialization vector generated by system.
     *
     */
    fun encrypt(data: String, key: Key?, useInitializationVector: Boolean = false): String {
        cipher.init(Cipher.ENCRYPT_MODE, key)

        var result = ""
        if (useInitializationVector) {
            val iv = cipher.iv
            val ivString = Base64.encodeToString(iv, Base64.DEFAULT)
            result = ivString + IV_SEPARATOR
        }

        val bytes = cipher.doFinal(data.toByteArray())
        result += Base64.encodeToString(bytes, Base64.DEFAULT)

        return result
    }

    /**
     * Decrypts data using the key.
     *
     * @param data the data to decrypt
     * @param key the key to decrypt data with
     * @param useInitializationVector decrypt data using initialization vector.
     */
    fun decrypt(data: String, key: Key?, useInitializationVector: Boolean = false): String {
        var encodedString: String

        if (useInitializationVector) {
            val split = data.split(IV_SEPARATOR.toRegex())
            if (split.size != 2) throw IllegalArgumentException("Passed data is incorrect. There was no IV specified with it.")

            val ivString = split[0]
            encodedString = split[1]
            val ivSpec = IvParameterSpec(Base64.decode(ivString, Base64.DEFAULT))
            cipher.init(Cipher.DECRYPT_MODE, key, ivSpec)
        } else {
            encodedString = data
            cipher.init(Cipher.DECRYPT_MODE, key)
        }

        val encryptedData = Base64.decode(encodedString, Base64.DEFAULT)
        val decodedData = cipher.doFinal(encryptedData)
        return String(decodedData)
    }
}

