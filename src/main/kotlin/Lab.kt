import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.Method
import com.github.kittinunf.result.Result
import com.github.kittinunf.result.getAs
import java.nio.charset.Charset

fun main(args: Array<String>) {

    val stringToSign = "d25dadd11ec94ae31c3735954b37ea86fbceb6608fd9fbe23be7fc44e6039d084db95e5ddc56b3653ac4505f88013231446416c84d5cff4ec2c86081a63f6747" + "/accountsnull"

    val headers = mapOf("XSwpApiKey" to "140d631950cd9f8cc964794228ea7ee56295e5a1860975f52e4a665815fee29b", "AcceptLanguage" to "en-us",
            "XSwpSignature" to "ZDI1ZGFkZDExZWM5NGFlMzFjMzczNTk1NGIzN2VhODZmYmNlYjY2MDhmZDlmYmUyM2JlN2ZjNDRlNjAzOWQwODRkYjk1ZTVkZGM1NmIzNjUzYWM0NTA1Zjg4MDEzMjMxNDQ2NDE2Yzg0ZDVjZmY0ZWMyYzg2MDgxYTYzZjY3NDcvYWNjb3VudHNudWxs")

    val response = post("https://wallet.sandbox.swipetech.io/accounts", headers, "", Charset.defaultCharset(), 500)


}


fun post(url: String, headers: Map<String, String>, payload: String, charset: Charset, timeout: Int): String {
    val request = FuelManager.instance.request(Method.POST, url)

    headers.forEach { k, v -> request.header(k to v) }

//    request.body(payload, charset)
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


//	r, _ := newRequest(w, "/accounts", POST, nil)
// https://wallet.sandbox.swipetech.io


//https://wallet.sandbox.swipetech.io/accounts


//signature := generateSignature(SignatureParams{
//    Secret: w.secret,
//    RequestPath: path.Clean(req.URL.Path),
//    BodyString: bodyString,
//})
