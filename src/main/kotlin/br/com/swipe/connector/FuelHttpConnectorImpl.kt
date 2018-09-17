package br.com.swipe.connector

import br.com.swipe.context.WalletService
import br.com.swipe.hash.HashServiceImpl
import br.com.swipe.models.Wallet
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.Method
import com.github.kittinunf.result.Result
import com.github.kittinunf.result.getAs
import java.nio.charset.Charset

class FuelHttpConnectorImpl: HttpConnector {
    companion object {
        val hashService= HashServiceImpl()
    }
    override fun post(url: String, headers: Map<String, String>, payload: String?): String {
        val request = FuelManager.instance.request(Method.POST, url)

        headers.forEach { k, v -> request.header(k to v) }
        payload?.let {
            request.body(payload, Charset.defaultCharset())
        }
        request.timeout(10000)
        request.timeoutRead(10000)

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

    override fun get(url: String, headers: Map<String, String>): String {
        val (_, _, result: Result<String, FuelError>) = Fuel.get(url)
                .header(headers)
                .responseString()
        when (result) {
            is Result.Success -> return result.value
            is Result.Failure -> throw Exception(String(result.error.response.data, Charsets.ISO_8859_1),
                    result.error.exception)
        }
    }

    fun buildHeaders(path: String, body: String?, wallet: Wallet): Map<String, String> {
        return mapOf("X-Swp-Signature" to hashService.generateSignature(path, body, wallet.secret),
                "X-Swp-Api-Key" to wallet.apiKey,
                "Accept-Language" to wallet.lang.name)
    }
}