package com.vjezba.androidjetpackgithub.data

import com.google.gson.annotations.SerializedName


data class RepositoryDetailsResponse(
    val id: Long = 0,
    @SerializedName("owner")
    val owner: RepositoryOwnerResponse = RepositoryOwnerResponse(""),
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("description")
    val description: String? = "",
    @SerializedName("html_url")
    val html_url: String? = ""
)