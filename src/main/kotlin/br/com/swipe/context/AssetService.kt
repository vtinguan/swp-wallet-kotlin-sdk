package br.com.swipe.context

import br.com.swipe.connector.FuelHttpConnectorImpl
import br.com.swipe.models.AssetResponse
import br.com.swipe.models.Wallet
import com.google.gson.Gson

fun Wallet.getAssets(): AssetResponse {
    val headers = AssetService.connectorImpl.buildHeaders(AssetService.ASSETS_ENDPOINT, "", this)
    val response = AssetService.connectorImpl.get("${this.baseUrl}${AssetService.ASSETS_ENDPOINT}", headers)
    return AssetService.gson.fromJson(response, AssetResponse::class.java)
}


class AssetService {
    companion object {
        val connectorImpl = FuelHttpConnectorImpl()
        val ASSETS_ENDPOINT = "/assets"
        val gson = Gson()
    }
}