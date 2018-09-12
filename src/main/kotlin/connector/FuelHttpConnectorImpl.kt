package connector

import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.Method
import com.github.kittinunf.result.Result
import com.github.kittinunf.result.getAs
import java.nio.charset.Charset

class FuelHttpConnectorImpl: HttpConnector {
    override fun post(url: String, headers: Map<String, String>, payload: String, charset: Charset, timeout: Int): String {
        val request = FuelManager.instance.request(Method.POST, url)

        headers.forEach { k, v -> request.header(k to v) }

        request.body(payload, charset)
        request.timeout(timeout)
        request.timeoutRead(timeout)

        val (_, _, result) = request.responseString()

        when (result) {
            is Result.Success -> {
                return result.getAs<String>()!!
            }
            is Result.Failure -> {
                throw IllegalStateException("Timeout calling $url.")
            }
        }
    }
}