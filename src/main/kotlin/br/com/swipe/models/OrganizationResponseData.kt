package br.com.swipe.models

import com.google.gson.annotations.SerializedName

data class OrganizationResponse(
        val data: OrganizationResponseData
)

data class OrganizationResponseData(
        val id: String,
        val name: String,
        val balances: List<Balance>
)

data class Balance(
        val balance: Double,
        @SerializedName("asset_code")
        val assetCode: String,
        val assetId: String
)
