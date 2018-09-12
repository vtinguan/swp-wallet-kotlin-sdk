import connector.FuelHttpConnectorImpl
import org.bouncycastle.jcajce.provider.digest.SHA3
import org.bouncycastle.util.encoders.Hex
import java.nio.charset.Charset

fun main(args: Array<String>) {
    val connector = FuelHttpConnectorImpl()

    val inputx = "12bba2c89baa3a770db410dfacdb70893e5b36a001bd4885c827bc48e96dbbab/accounts"
    val sha = SHA3.Digest512()
    val digest = sha.digest(inputx.toByteArray())
    Hex.toHexString(digest)

    val headers = mapOf("X-Swp-Api-Key" to "140d631950cd9f8cc964794228ea7ee56295e5a1860975f52e4a665815fee29b", "Accept-Language" to "pt-br",
            "X-Swp-Signature" to "qoLKWPAypejWIUbO9k7eDQfkn8gySjAO0pnq78r9LbEXBbXUkewWKpSl6zABTfnI")

    val response = connector.post("https://wallet.sandbox.swipetech.io/accounts", headers, "", Charset.defaultCharset(), 10000)


}
