package br.com.swipe.context

import br.com.swipe.models.Lang
import br.com.swipe.models.Wallet

class EnvironmentFactory {
    companion object {
        val BASE_URL = "https://wallet.sandbox.swipetech.io"
        val PROD_URL = "https://wallet.swipetech.io"
    }

    fun initSandbox(apiKey: String, secret: String, lang: Lang): Wallet {
        return Wallet(apiKey, secret, lang, BASE_URL)
    }

    fun initProd(apiKey: String, secret: String, lang: Lang): Wallet {
        return Wallet(apiKey, secret, lang, PROD_URL)
    }

    fun initCustom(customUrl: String, apiKey: String, secret: String, lang: Lang): Wallet {
        return Wallet(apiKey, secret, lang, customUrl)
    }
}