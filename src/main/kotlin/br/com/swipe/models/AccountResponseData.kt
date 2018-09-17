package br.com.swipe.models

import com.google.gson.annotations.SerializedName

data class AccountResponse(
        val data: AccountResponseData
)

data class AccountListResponse(
        val data: List<AccountResponseData>
)

data class AssetResponse(
        val data: List<AssetReceipt>
)

data class AssetReceipt(
        val receipt: Receipt,
        val asset: Asset
)

data class AccountResponseData (
        val receipt: Receipt,
        val account: Account
)

data class Receipt (
        val id: String,
        @SerializedName("created_at")
        //TODO use LocalDateTime instead String
        val createdAt: String,
        @SerializedName("op_type")
        val opType: String
)

data class Account(
        val id: String
)

data class Asset(
        val id: String,
        val code: String,
        val limit: Int
)