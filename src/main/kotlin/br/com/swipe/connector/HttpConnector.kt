package br.com.swipe.connector

interface HttpConnector {

    fun post(url: String, headers: Map<String, String>, payload: String?): String

    fun get(url: String, headers: Map<String, String>): String

}