package br.com.swipe.context

import br.com.swipe.connector.FuelHttpConnectorImpl
import br.com.swipe.models.AccountListResponse
import br.com.swipe.models.AccountResponse
import br.com.swipe.models.Wallet
import com.google.gson.Gson


fun Wallet.createAccount(): AccountResponse {
    val headers = WalletService.connectorImpl.buildHeaders(WalletService.ACCOUNT_ENDPOINT, "", this)
    val response = WalletService.connectorImpl.post("${this.baseUrl}${WalletService.ACCOUNT_ENDPOINT}", headers, null)
    return WalletService.gson.fromJson(response, AccountResponse::class.java)
}

fun Wallet.getAccount(id: String): AccountResponse {
    val headers = WalletService.connectorImpl.buildHeaders("${WalletService.ACCOUNT_ENDPOINT}/$id", "", this)
    val response = WalletService.connectorImpl.get("${this.baseUrl}${WalletService.ACCOUNT_ENDPOINT}/$id", headers)
    return WalletService.gson.fromJson(response, AccountResponse::class.java)
}

fun Wallet.getAllAccounts(): AccountListResponse {
    val headers = WalletService.connectorImpl.buildHeaders(WalletService.ACCOUNT_ENDPOINT, "", this)
    val response = WalletService.connectorImpl.get("${this.baseUrl}${WalletService.ACCOUNT_ENDPOINT}", headers)
    return WalletService.gson.fromJson(response, AccountListResponse::class.java)
}

class WalletService {
    companion object {
        val connectorImpl = FuelHttpConnectorImpl()
        val ACCOUNT_ENDPOINT = "/accounts"
        val gson = Gson()
    }
}