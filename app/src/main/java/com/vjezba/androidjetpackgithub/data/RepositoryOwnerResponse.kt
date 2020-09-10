package com.vjezba.androidjetpackgithub.data

import com.google.gson.annotations.SerializedName
import java.util.*


data class RepositoryOwnerResponse(
    @SerializedName("avatar_url")
    val avatarUrl: String = ""
)