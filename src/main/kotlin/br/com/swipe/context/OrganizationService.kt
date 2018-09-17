package br.com.swipe.context

import br.com.swipe.connector.FuelHttpConnectorImpl
import br.com.swipe.models.OrganizationResponse
import br.com.swipe.models.Wallet
import com.google.gson.Gson

fun Wallet.getOrganization(): OrganizationResponse {
    val headers = OrganizationService.connectorImpl.buildHeaders(OrganizationService.ORGANIZATIONS_ENDPOINT, "", this)
    val response = OrganizationService.connectorImpl.get("${this.baseUrl}${OrganizationService.ORGANIZATIONS_ENDPOINT}", headers)
    return OrganizationService.gson.fromJson(response, OrganizationResponse::class.java)
}

class OrganizationService {
    companion object {
        val connectorImpl = FuelHttpConnectorImpl()
        val ORGANIZATIONS_ENDPOINT = "/organizations"
        val gson = Gson()
    }
}