package br.com.swipe.hash

import java.util.Base64
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

class HashServiceImpl: HashService {
    companion object {
        val HMAC_SHA384 = "HmacSHA384"
    }

    override fun generateSignature(path: String, body: String?, secret: String): String {
        val data = "$path$body"

        val secretSpec = SecretKeySpec(secret.toByteArray(), HMAC_SHA384)

        val mac = Mac.getInstance(HMAC_SHA384)
        mac.init(secretSpec)

        val bytes = mac.doFinal(data.toByteArray())

        return Base64.getEncoder().encodeToString(bytes)
    }
}