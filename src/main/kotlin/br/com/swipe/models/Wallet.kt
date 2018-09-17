package br.com.swipe.models

data class Wallet (
    val apiKey: String,
    val secret: String,
    val lang: Lang,
    val baseUrl: String
)


enum class Lang (name: String) {
    PT_BR("pt-br"),
    EN_US("en-us")
}