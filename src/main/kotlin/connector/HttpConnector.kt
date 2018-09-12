package connector

import java.nio.charset.Charset

interface HttpConnector {

    fun post(url: String, headers: Map<String, String>, payload: String, charset: Charset, timeout: Int): String

}