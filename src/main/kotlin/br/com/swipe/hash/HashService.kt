package br.com.swipe.hash

interface HashService {
    fun generateSignature(path: String, body: String?, secret: String): String
}